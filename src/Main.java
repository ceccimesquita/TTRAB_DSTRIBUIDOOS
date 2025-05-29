import q1.Atleta;
import q2.AtletaOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // Imprime a mensagem de inicialização
        System.out.println("Iniciando escritura...");

        // Criando um array de Atletas
        Atleta[] atletas = {
                new Atleta("Carlos", 10, "Pivô", 25),
                new Atleta("João", 7, "Fixo", 28)
        };

        // Teste com a saída padrão (System.out)
        try (AtletaOutputStream aos = new AtletaOutputStream(atletas, atletas.length, System.out)) {
            // Dados enviados para o console (em bytes)
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Teste com o arquivo (FileOutputStream)
        try (FileOutputStream fos = new FileOutputStream("atletas.dat");
             AtletaOutputStream aos = new AtletaOutputStream(atletas, atletas.length, fos)) {
            // Dados enviados para o arquivo atletas.dat
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Teste com o servidor remoto (TCP)
        try (Socket socket = new Socket("localhost", 23456)) {
            // Envia os dados do atleta para o servidor
            AtletaOutputStream aos = new AtletaOutputStream(atletas, atletas.length, socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
