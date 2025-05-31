package Stream.TCP_JSON.Cliente;

import Stream.TCP_JSON.Resposta;
import com.google.gson.Gson;
import modelos.Psiquiatra;

import modelos.Psiquiatra;

import java.io.*;
import java.net.Socket;

public class ClientePsiquiatraJSON {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            // Criar objeto Psiquiatra
            Psiquiatra psiquiatra = new Psiquiatra("Dra. Ana", "12345", "99999-9999", true, 60);

            // Serializa para JSON
            Gson gson = new Gson();
            String json = gson.toJson(psiquiatra);

            // Envia para o servidor
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println(json);  // Enviar JSON

            String jsonResposta = reader.readLine();
            Resposta resposta = gson.fromJson(jsonResposta, Resposta.class);
            System.out.println(resposta.getStatus() + " - " + resposta.getMensagem());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
