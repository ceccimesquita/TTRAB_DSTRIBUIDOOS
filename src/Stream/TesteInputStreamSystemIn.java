package Stream;

import java.io.IOException;

public class TesteInputStreamSystemIn {
    public static void main(String[] args) {
        try {
            System.out.println("Digite os dados e pressione Ctrl+D (Linux/macOS) ou Ctrl+Z (Windows) para encerrar:");

            PsiquiatraInputStream pis = new PsiquiatraInputStream(System.in);
            pis.lerDados();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
