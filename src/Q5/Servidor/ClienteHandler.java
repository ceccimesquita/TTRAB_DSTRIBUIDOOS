package Q5.Servidor;

import Q5.modelo.Candidato;
import Q5.modelo.Eleitor;
import Q5.modelo.Mensagem;
import Q5.modelo.Admin;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class ClienteHandler implements Runnable {
    private final Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            Gson gson = new Gson();
            Eleitor eleitor = null;
            boolean autenticado = false;

            while (!autenticado) {
                Mensagem loginMsg = gson.fromJson(entrada.readLine(), Mensagem.class);
                Mensagem senhaMsg = gson.fromJson(entrada.readLine(), Mensagem.class);

                String login = loginMsg.getConteudo();
                String senha = senhaMsg.getConteudo();

                // Admin
                if (login.equals(ServidorVotacao.admin.getLogin()) &&
                        senha.equals(ServidorVotacao.admin.getSenha())) {
                    saida.println(gson.toJson(new Mensagem("resposta", "Bem-vindo, administrador.")));
                    tratarComandosAdmin(entrada, saida, gson);
                    return;
                }

                eleitor = ServidorVotacao.eleitores.get(login);
                if (eleitor != null && eleitor.getSenha().equals(senha)) {
                    autenticado = true;
                    saida.println(gson.toJson(new Mensagem("resposta", "Login bem-sucedido!")));
                } else {
                    saida.println(gson.toJson(new Mensagem("resposta", "Falha no login.")));
                }
            }

            if (!ServidorVotacao.votacaoAtiva()) {
                saida.println(gson.toJson(new Mensagem("resposta", "Votação encerrada.")));
                saida.println(gson.toJson(new Mensagem("resultado", ServidorVotacao.resultadoFinal())));
                return;
            }

            if (eleitor.isVotou()) {
                saida.println(gson.toJson(new Mensagem("resposta", "Você já votou.")));
                return;
            }

            // Lista de candidatos
            StringBuilder lista = new StringBuilder();
            for (String nome : ServidorVotacao.candidatos.keySet()) {
                lista.append("- ").append(nome).append("\n");
            }
            lista.append("Digite o nome do candidato:");

            saida.println(gson.toJson(new Mensagem("lista", lista.toString())));

            // Voto
            Mensagem votoMsg = gson.fromJson(entrada.readLine(), Mensagem.class);
            Candidato c = ServidorVotacao.candidatos.get(votoMsg.getConteudo());

            if (c != null) {
                c.adicionarVoto();
                eleitor.setVotou(true);
                System.out.println("VOTO REGISTRADO → " + eleitor.getLogin() + " votou em " + c.getNome());
                saida.println(gson.toJson(new Mensagem("resposta", "Voto registrado com sucesso!")));
            } else {
                saida.println(gson.toJson(new Mensagem("resposta", "Candidato inválido.")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tratarComandosAdmin(BufferedReader entrada, PrintWriter saida, Gson gson) throws IOException {
        String linha;
        while ((linha = entrada.readLine()) != null) {
            Mensagem comando = gson.fromJson(linha, Mensagem.class);
            String conteudo = comando.getConteudo();

            if (conteudo.equalsIgnoreCase("sair")) break;

            if (conteudo.startsWith("addCandidato:")) {
                String nome = conteudo.split(":", 2)[1];
                if (!ServidorVotacao.candidatos.containsKey(nome)) {
                    ServidorVotacao.candidatos.put(nome, new Candidato(nome));
                    saida.println(gson.toJson(new Mensagem("resposta", "Candidato adicionado: " + nome)));
                } else {
                    saida.println(gson.toJson(new Mensagem("resposta", "Candidato já existe.")));
                }
            } else if (conteudo.startsWith("removerCandidato:")) {
                String nome = conteudo.split(":", 2)[1];
                if (ServidorVotacao.candidatos.remove(nome) != null) {
                    saida.println(gson.toJson(new Mensagem("resposta", "Candidato removido: " + nome)));
                } else {
                    saida.println(gson.toJson(new Mensagem("resposta", "Candidato não encontrado.")));
                }
            } else if (conteudo.equalsIgnoreCase("resultadoParcial")) {
                saida.println(gson.toJson(new Mensagem("resultado", ServidorVotacao.resultadoFinal())));
            }  else {
                saida.println(gson.toJson(new Mensagem("resposta", "Comando inválido.")));
            }
        }
    }
}
