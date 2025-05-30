package Q5.Servidor;

import Q5.modelo.Eleitor;
import Q5.modelo.Candidato;

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
            Eleitor eleitor = null;
            boolean autenticado = false;

            while (!autenticado) {
                saida.println("LOGIN: Usuário:");
                String login = entrada.readLine();
                saida.println("Senha:");
                String senha = entrada.readLine();


                if (login.equals(ServidorVotacao.admin.getLogin()) && senha.equals(ServidorVotacao.admin.getSenha())) {
                    saida.println("Bem-vindo, administrador.");
                    tratarComandosAdmin(entrada, saida);
                    return;
                }

                eleitor = ServidorVotacao.eleitores.get(login);
                if (eleitor != null && eleitor.getSenha().equals(senha)) {
                    autenticado = true;
                } else {
                    saida.println("Falha no login.");
                }
            }


            // Se votação já acabou
            if (!ServidorVotacao.votacaoAtiva()) {
                saida.println("Votação encerrada.");
                saida.println(ServidorVotacao.resultadoFinal());
                return;
            }

            // Se já votou
            if (eleitor.isVotou()) {
                saida.println("Você já votou.");
                return;
            }

            // Envia lista de candidatos
            saida.println("\n--- CANDIDATOS ---");
            for (String nome : ServidorVotacao.candidatos.keySet()) {
                saida.println("- " + nome);
            }
            saida.println("Digite o nome do candidato em quem deseja votar:");
            String voto = entrada.readLine();

            Candidato candidato = ServidorVotacao.candidatos.get(voto);
            if (candidato != null) {
                candidato.adicionarVoto();
                eleitor.setVotou(true);
                System.out.println("VOTO REGISTRADO → " + eleitor.getLogin() + " votou em " + candidato.getNome());
                saida.println("✅ Voto registrado com sucesso!");
            } else {
                saida.println("❌ Candidato inválido.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tratarComandosAdmin(BufferedReader entrada, PrintWriter saida) throws IOException {
        String comando;
        while ((comando = entrada.readLine()) != null) {
            if (comando.equalsIgnoreCase("sair")) {
                break;
            } else if (comando.startsWith("addCandidato:")) {
                String nome = comando.split(":", 2)[1];
                synchronized (ServidorVotacao.class) {
                    if (!ServidorVotacao.candidatos.containsKey(nome)) {
                        ServidorVotacao.candidatos.put(nome, new Candidato(nome));
                        saida.println("Candidato adicionado: " + nome);
                    } else {
                        saida.println("Candidato já existe.");
                    }
                }
            } else if (comando.startsWith("removerCandidato:")) {
                String nome = comando.split(":", 2)[1];
                synchronized (ServidorVotacao.class) {
                    if (ServidorVotacao.candidatos.remove(nome) != null) {
                        saida.println("Candidato removido: " + nome);
                    } else {
                        saida.println("Candidato não encontrado.");
                    }
                }
            } else if (comando.equalsIgnoreCase("resultadoParcial")) {
                String[] linhas = ServidorVotacao.resultadoFinal().split("\n");
                for (String linha : linhas) {
                    saida.println(linha);
                }
                saida.println("FIM");
            } else {
                saida.println("Comando inválido.");
            }
        }
    }
}
