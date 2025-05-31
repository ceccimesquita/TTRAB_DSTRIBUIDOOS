package Stream.TCP_JSON.Servidor;

import com.google.gson.Gson;
import modelos.Psiquiatra;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPsiquiatraJSON {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(12345)) {
            System.out.println("Servidor aguardando conexão...");

            Socket cliente = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter writer = new PrintWriter(cliente.getOutputStream(), true);
            Gson gson = new Gson();

            String json = reader.readLine();  // Recebe o JSON
            Psiquiatra recebido = gson.fromJson(json, Psiquiatra.class);  // Desserializa

            System.out.println("Psiquiatra recebido:");
            System.out.println(recebido);  // Usa toString()

            writer.println("{\"status\": \"OK\", \"mensagem\": \"Psiquiatra recebido com sucesso!\"}");

            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
