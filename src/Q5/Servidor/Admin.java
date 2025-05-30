package Q5.Servidor;

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

            System.out.println(entrada.readLine());
            saida.println("admin");
            System.out.println(entrada.readLine());
            saida.println("admin123");

            String linha = entrada.readLine();
            if (linha == null || !linha.contains("Bem-vindo")) {
                System.out.println("Falha no login como administrador.");
                return;
            }
            System.out.println(linha);


            while (true) {
                System.out.println("\n=== Painel do Administrador ===");
                System.out.println("1. Enviar nota informativa");
                System.out.println("2. Adicionar candidato");
                System.out.println("3. Remover candidato");
                System.out.println("4. Ver resultado parcial");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                String op = sc.nextLine();

                switch (op) {
                    case "1" -> enviarNota(sc);
                    case "2" -> {
                        System.out.print("Nome do candidato: ");
                        String nome = sc.nextLine();
                        saida.println("addCandidato:" + nome);
                        System.out.println(entrada.readLine());
                    }
                    case "3" -> {
                        System.out.print("Nome do candidato a remover: ");
                        String nome = sc.nextLine();
                        saida.println("removerCandidato:" + nome);
                        System.out.println(entrada.readLine());
                    }
                    case "4" -> {
                        saida.println("resultadoParcial");
                        String resposta;
                        while (!(resposta = entrada.readLine()).equals("FIM")) {
                            System.out.println(resposta);
                        }
                    }
                    case "0" -> {
                        saida.println("sair");
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
