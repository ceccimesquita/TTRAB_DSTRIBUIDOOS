package Q5.Cliente;

import Q5.modelo.Mensagem;
import com.google.gson.Gson;

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
            Gson gson = new Gson();

            new Thread(new ClienteMulticast()).start();

            boolean loginSucesso = false;
            while (!loginSucesso) {
                // Enviar login
                System.out.print("LOGIN: Usuário: ");
                String usuario = teclado.nextLine();
                saida.println(gson.toJson(new Mensagem("login", usuario)));

                System.out.print("Senha: ");
                String senha = teclado.nextLine();
                saida.println(gson.toJson(new Mensagem("senha", senha)));

                Mensagem resposta = gson.fromJson(entrada.readLine(), Mensagem.class);
                System.out.println(resposta.getConteudo());

                if (resposta.getConteudo().toLowerCase().contains("falha")) {
                    System.out.println("Deseja tentar novamente? (s/n)");
                    if (teclado.nextLine().trim().equalsIgnoreCase("n")) return;
                } else {
                    loginSucesso = true;
                    if (resposta.getConteudo().toLowerCase().contains("já votou") ||
                            resposta.getConteudo().toLowerCase().contains("encerrada")) {

                        while (entrada.ready()) {
                            Mensagem msg = gson.fromJson(entrada.readLine(), Mensagem.class);
                            System.out.println(msg.getConteudo());
                        }
                        return;
                    }
                }
            }

            // Receber lista de candidatos
            Mensagem listaMsg = gson.fromJson(entrada.readLine(), Mensagem.class);
            System.out.println(listaMsg.getConteudo());

            // Loop para votar
            while (true) {
                System.out.print("> ");
                String voto = teclado.nextLine();
                saida.println(gson.toJson(new Mensagem("voto", voto)));

                Mensagem resposta = gson.fromJson(entrada.readLine(), Mensagem.class);
                System.out.println(resposta.getConteudo());

                if (resposta.getConteudo().contains("Voto")) break;
                if (resposta.getConteudo().contains("Candidato")) {
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
