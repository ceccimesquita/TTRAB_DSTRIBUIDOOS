package Stream;

import modelos.Psiquiatra;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class TesteSystemOut {
    public static void main(String[] args) {
        try {
            Psiquiatra[] lista = {
                    new Psiquiatra("Dra. Ana", "12345", "1000", true, 60),
                    new Psiquiatra("Dr. Pedro", "67890", "8888888", false, 45)
            };

            int[] bytes = new int[lista.length];
            for (int i = 0; i < lista.length; i++) {
                String base = lista[i].getNome() + lista[i].getCrm() + lista[i].getContato() + lista[i].isAtendeOnline() + lista[i].getDuracaoConsultaMin();
                bytes[i] = base.getBytes(StandardCharsets.UTF_8).length;
            }

            PsiquiatraOutputStream stream = new PsiquiatraOutputStream(lista, lista.length, bytes, System.out);
            stream.enviarDados();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
