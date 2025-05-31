package Stream;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCPLeitor {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5001)) {
            System.out.println("Servidor esperando na porta 5001...");

            Socket socket = server.accept();
            System.out.println("Cliente conectado!");

            InputStream in = socket.getInputStream();
            PsiquiatraInputStream pis = new PsiquiatraInputStream(in);
            pis.lerDados();

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
