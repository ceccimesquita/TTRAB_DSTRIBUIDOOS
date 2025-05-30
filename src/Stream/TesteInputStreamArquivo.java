package Stream;

import java.io.FileInputStream;
import java.io.IOException;

public class TesteInputStreamArquivo {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("psiquiatras.txt")) {
            PsiquiatraInputStream pis = new PsiquiatraInputStream(fis);
            pis.lerDados();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
