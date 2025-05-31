package Q5.Servidor;

import Q5.modelo.Mensagem;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Admin {

    private static final String GRUPO = "230.0.0.0";
    private static final int PORTA_MULTICAST = 4446;
    private static final String HOST_SERVIDOR = "localhost";
    private static final int PORTA_SERVIDOR = 12345;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST_SERVIDOR, PORTA_SERVIDOR);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                Scanner sc = new Scanner(System.in)
        ) {
            Gson gson = new Gson();

            // Login
            saida.println(gson.toJson(new Mensagem("login", "admin")));
            saida.println(gson.toJson(new Mensagem("senha", "admin123")));

            Mensagem respostaLogin = gson.fromJson(entrada.readLine(), Mensagem.class);
            if (!respostaLogin.getConteudo().contains("Bem-vindo")) {
                System.out.println("❌ Falha no login como administrador.");
                return;
            }
            System.out.println(respostaLogin.getConteudo());

            while (true) {
                System.out.println("\n=== Painel do Administrador ===");
                System.out.println("1. Enviar nota informativa");
                System.out.println("2. Adicionar candidato");
                System.out.println("3. Remover candidato");
                System.out.println("4. Ver resultado parcial");
                System.out.println("5. Iniciar nova votação");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                String op = sc.nextLine();

                switch (op) {
                    case "1" -> enviarNota(sc);
                    case "2" -> {
                        System.out.print("Nome do candidato: ");
                        String nome = sc.nextLine();
                        saida.println(gson.toJson(new Mensagem("comando", "addCandidato:" + nome)));
                        System.out.println(gson.fromJson(entrada.readLine(), Mensagem.class).getConteudo());
                    }
                    case "3" -> {
                        System.out.print("Nome do candidato a remover: ");
                        String nome = sc.nextLine();
                        saida.println(gson.toJson(new Mensagem("comando", "removerCandidato:" + nome)));
                        System.out.println(gson.fromJson(entrada.readLine(), Mensagem.class).getConteudo());
                    }
                    case "4" -> {
                        saida.println(gson.toJson(new Mensagem("comando", "resultadoParcial")));
                        Mensagem resposta = gson.fromJson(entrada.readLine(), Mensagem.class);
                        System.out.println(resposta.getConteudo());
                    }
                    case "5" -> {
                        saida.println(gson.toJson(new Mensagem("comando", "novaVotacao")));
                        System.out.println(gson.fromJson(entrada.readLine(), Mensagem.class).getConteudo());
                    }
                    case "0" -> {
                        saida.println(gson.toJson(new Mensagem("comando", "sair")));
                        System.out.println("Encerrando console administrativo.");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Não foi possível conectar ao servidor.");
            e.printStackTrace();
        }
    }

    private static void enviarNota(Scanner sc) {
        try (DatagramSocket socket = new DatagramSocket()) {
            System.out.print("Digite a nota: ");
            String msg = sc.nextLine();
            byte[] buffer = msg.getBytes();

            DatagramPacket pacote = new DatagramPacket(
                    buffer, buffer.length,
                    InetAddress.getByName(GRUPO), PORTA_MULTICAST
            );
            socket.send(pacote);
            System.out.println("Nota enviada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao enviar nota multicast.");
            e.printStackTrace();
        }
    }
}
