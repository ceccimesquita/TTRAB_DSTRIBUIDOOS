package Stream;

import modelos.Psiquiatra;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PsiquiatraOutputStream extends OutputStream {

    private Psiquiatra[] psiquiatras;
    private int quantidade;
    private int[] bytesPorObjeto;
    private OutputStream destino;

    public PsiquiatraOutputStream(Psiquiatra[] psiquiatras, int quantidade, int[] bytesPorObjeto, OutputStream destino) {
        if (psiquiatras.length != bytesPorObjeto.length) {
            throw new IllegalArgumentException("O array de bytes deve ter o mesmo tamanho do array de objetos.");
        }
        this.psiquiatras = psiquiatras;
        this.quantidade = quantidade;
        this.bytesPorObjeto = bytesPorObjeto;
        this.destino = destino;
    }

    @Override
    public void write(int b) throws IOException {
        destino.write(b);
    }

    public void enviarDados() throws IOException {
        for (int i = 0; i < quantidade && i < psiquiatras.length; i++) {
            Psiquiatra p = psiquiatras[i];

            // Pegando os 3 atributos mínimos para o cálculo de bytes
            // CERTO – usa todos os 5 atributos!
            String dados = "Nome: " + p.getNome()
                    + ", CRM: " + p.getCrm()
                    + ", Contato: " + p.getContato()
                    + ", Atende Online: " + (p.isAtendeOnline() ? "Sim" : "Não")
                    + ", Duração da Consulta: " + p.getDuracaoConsultaMin() + " min";


            // Convertendo para bytes
            byte[] dadosBytes = dados.getBytes(StandardCharsets.UTF_8);

            // Enviando os dados e o número de bytes registrados
            String envio = dados + " [Bytes: " + bytesPorObjeto[i] + "]\n";
            destino.write(envio.getBytes(StandardCharsets.UTF_8));
        }

        destino.flush();
    }
}
