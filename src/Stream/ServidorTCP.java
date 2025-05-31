package Stream;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(5001)) {
            System.out.println("Servidor esperando conexão na porta 5000...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado!");

            InputStream in = cliente.getInputStream();
            int byteRecebido;
            while ((byteRecebido = in.read()) != -1) {
                System.out.print((char) byteRecebido);
            }

            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
