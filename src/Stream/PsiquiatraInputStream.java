package Stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PsiquiatraInputStream extends InputStream {

    private final InputStream origem;

    public PsiquiatraInputStream(InputStream origem) {
        this.origem = origem;
    }

    @Override
    public int read() throws IOException {
        return origem.read(); // leitura básica de um byte
    }

    public void lerDados() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesLidos;

        while ((bytesLidos = origem.read(buffer)) != -1) {
            String recebido = new String(buffer, 0, bytesLidos, StandardCharsets.UTF_8);
            System.out.print(recebido);
        }
    }
}
