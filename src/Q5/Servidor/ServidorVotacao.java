package Q5.Servidor;

import Q5.modelo.Candidato;
import Q5.modelo.Eleitor;
import Q5.modelo.Admin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class ServidorVotacao {

    public static Map<String, Eleitor> eleitores = new HashMap<>();
    public static Map<String, Candidato> candidatos = new LinkedHashMap<>();
    public static Admin admin;
    public static long encerramentoVotacao;
    public static final long DURACAO_MS = 2 * 60 * 1000; // 2 minutos

    public static void main(String[] args) {
        iniciarVotacao();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor de votação iniciado...");
            while (true) {
                Socket cliente = serverSocket.accept();
                new Thread(new ClienteHandler(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void iniciarVotacao() {
        eleitores.clear();
        candidatos.clear();

        // cadastra usuários e candidatos fixos
        eleitores.put("ana", new Eleitor("ana", "123"));
        eleitores.put("joao", new Eleitor("joao", "abc"));
        eleitores.put("maria", new Eleitor("maria", "321"));

        admin = new Admin("admin", "admin123");

        candidatos.put("alice", new Candidato("alice"));
        candidatos.put("bob", new Candidato("bob"));
        candidatos.put("clara", new Candidato("clara"));

        encerramentoVotacao = System.currentTimeMillis() + DURACAO_MS;

        new Thread(() -> {
            try {
                while (System.currentTimeMillis() < encerramentoVotacao) {
                    Thread.sleep(1000);
                }
                System.out.println(resultadoFinal());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static synchronized boolean votacaoAtiva() {
        return System.currentTimeMillis() < encerramentoVotacao;
    }

    public static synchronized String resultadoFinal() {
        StringBuilder sb = new StringBuilder("\n--- RESULTADO FINAL ---\n");
        int total = candidatos.values().stream().mapToInt(Candidato::getVotos).sum();
        Candidato vencedor = null;
        for (Candidato c : candidatos.values()) {
            sb.append(c.getNome())
                    .append(": ").append(c.getVotos())
                    .append(" votos (")
                    .append(total > 0 ? (c.getVotos() * 100 / total) : 0)
                    .append("%)\n");

            if (vencedor == null || c.getVotos() > vencedor.getVotos()) {
                vencedor = c;
            }
        }
        if (vencedor != null) {
            sb.append("VENCEDOR: ").append(vencedor.getNome()).append("\n");
        }
        return sb.toString();
    }
}
