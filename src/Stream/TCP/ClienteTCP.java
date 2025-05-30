package Stream.TCP;

import modelos.Psiquiatra;
import Stream.PsiquiatraOutputStream;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClienteTCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.0.95", 5001)) {
            OutputStream out = socket.getOutputStream();

            Psiquiatra[] lista = {
                    new Psiquiatra("Dra. Ana", "12345", "1000", true, 60),
                    new Psiquiatra("Dr. Pedro", "67890", "8888888", false, 45)
            };

            int[] bytes = new int[lista.length];
            for (int i = 0; i < lista.length; i++) {
                String base = lista[i].getNome() + lista[i].getCrm() + lista[i].getContato()
                        + lista[i].isAtendeOnline() + lista[i].getDuracaoConsultaMin();
                bytes[i] = base.getBytes(StandardCharsets.UTF_8).length;
            }

            PsiquiatraOutputStream stream = new PsiquiatraOutputStream(lista, lista.length, bytes, out);
            stream.enviarDados();

            System.out.println("✅ Dados enviados ao servidor via socket TCP.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
