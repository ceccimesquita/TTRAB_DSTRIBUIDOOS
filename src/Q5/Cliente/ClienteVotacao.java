package Q5.Cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteVotacao {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 12345);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                Scanner teclado = new Scanner(System.in)
        ) {

            new Thread(new ClienteMulticast()).start();

            boolean loginSucesso = false;
            while (!loginSucesso) {
                for (int i = 0; i < 2; i++) {
                    String linha = entrada.readLine();
                    System.out.print(linha + " ");
                    String entradaUsuario = teclado.nextLine();
                    saida.println(entradaUsuario);
                }

                String resposta = entrada.readLine();
                System.out.println(resposta);

                if (resposta == null || resposta.toLowerCase().contains("falha")) {
                    System.out.println("Deseja tentar novamente? (s/n)");
                    if (teclado.nextLine().trim().equalsIgnoreCase("n")) return;
                } else {
                    loginSucesso = true;
                    if (resposta.toLowerCase().contains("já votou") || resposta.toLowerCase().contains("encerrada")) {
                        // Leitura do restante, se houver
                        String linha;
                        while ((linha = entrada.readLine()) != null) {
                            System.out.println(linha);
                        }
                        return;
                    }
                }
            }

            String linha;
            while ((linha = entrada.readLine()) != null && !linha.contains("Digite o nome")) {
                System.out.println(linha);
            }

            while (true) {
                System.out.print("> ");
                String voto = teclado.nextLine();
                saida.println(voto);

                String resposta = entrada.readLine();
                System.out.println(resposta);

                if (resposta != null && resposta.contains("✅")) {
                    break;
                } else if (resposta != null && resposta.contains("❌")) {
                    System.out.println("Deseja tentar votar novamente? (s/n)");
                    if (teclado.nextLine().trim().equalsIgnoreCase("n")) return;
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Erro de conexão com o servidor.");
            e.printStackTrace();
        }
    }
}

class ClienteMulticast implements Runnable {
    @Override
    public void run() {
        try (MulticastSocket socket = new MulticastSocket(4446)) {
            InetAddress grupo = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(grupo);

            //System.out.println("Aguardando notas informativas do administrador...");

            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote);
                String msg = new String(pacote.getData(), 0, pacote.getLength());
                System.out.println("\nNOTA ADMIN: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

