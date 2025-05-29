package q2;

import q1.Atleta;

import java.io.IOException;

public class TesteAtletaOutputStream {
    public static void main(String[] args) {
        Atleta[] atletas = {
                new Atleta("Carlos", 10, "Pivô", 25),
                new Atleta("João", 7, "Fixo", 28)
        };

        try (AtletaOutputStream aos = new AtletaOutputStream(atletas, atletas.length, System.out)) {
            // Dados enviados para a saída padrão (console)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

