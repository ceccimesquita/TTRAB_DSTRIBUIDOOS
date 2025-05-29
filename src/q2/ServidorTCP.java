package q2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServidorTCP {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(23456)) {
            System.out.println("Servidor esperando conexão...");
            try (Socket socket = serverSocket.accept();
                 DataInputStream dataInput = new DataInputStream(socket.getInputStream())) {
                System.out.println("Cliente conectado!");

                while (true) {
                    // Ler tamanho do nome (int 4 bytes)
                    int nomeLen;
                    try {
                        nomeLen = dataInput.readInt();
                    } catch (IOException e) {
                        break; // fim do stream
                    }
                    // Ler nome
                    byte[] nomeBytes = new byte[nomeLen];
                    dataInput.readFully(nomeBytes);
                    String nome = new String(nomeBytes, StandardCharsets.UTF_8);

                    // Ler número (int)
                    int numero = dataInput.readInt();

                    // Ler tamanho da posição
                    int posLen = dataInput.readInt();

                    // Ler posição
                    byte[] posBytes = new byte[posLen];
                    dataInput.readFully(posBytes);
                    String posicao = new String(posBytes, StandardCharsets.UTF_8);

                    // Imprime os dados legíveis do atleta
                    System.out.printf("Atleta: %s, Número: %d, Posição: %s%n", nome, numero, posicao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
