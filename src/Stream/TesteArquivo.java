package Stream;

import modelos.Psiquiatra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TesteArquivo {
    public static void main(String[] args) {
        try {
            Psiquiatra[] lista = {
                    new Psiquiatra("Dra. Ana", "12345", "1000", true, 60),
                    new Psiquiatra("Dr. Pedro", "67890", "8888888", false, 45),
                    new Psiquiatra("Dr. Livia", "424244", "dra@gmail.com", false, 45),
                    new Psiquiatra("Dr. Livia", "424244", "dra@gmail.com", false, 45),
                    new Psiquiatra("Dr. Livia", "424244", "dra@gmail.com", false, 45),
                    new Psiquiatra("Dra. Ana", "12345", "1000", true, 60),
                    new Psiquiatra("Dr. Pedro", "67890", "8888888", false, 45),
                    new Psiquiatra("Dr. Livia", "424244", "dra@gmail.com", false, 45),
                    new Psiquiatra("Dr. Livia", "424244", "dra@gmail.com", false, 45),
                    new Psiquiatra("Dr. Livia", "424244", "dra@gmail.com", false, 45)

            };

            int[] bytes = new int[lista.length];
            for (int i = 0; i < lista.length; i++) {
                String base = lista[i].getNome() + lista[i].getCrm() + lista[i].getContato()
                        + lista[i].isAtendeOnline() + lista[i].getDuracaoConsultaMin();
                bytes[i] = base.getBytes(StandardCharsets.UTF_8).length;
            }

            FileOutputStream fos = new FileOutputStream("psiquiatrasarquivo.txt");
            PsiquiatraOutputStream stream = new PsiquiatraOutputStream(lista, lista.length, bytes, fos);
            stream.enviarDados();
            fos.close();

            System.out.println("Dados salvos em psiquiatras.txt com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
