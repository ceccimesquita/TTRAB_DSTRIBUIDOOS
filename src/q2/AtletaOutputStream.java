package q2;

import q1.Atleta;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AtletaOutputStream extends OutputStream {
    private Atleta[] atletas;
    private int quantidade;
    private OutputStream output;

    public AtletaOutputStream(Atleta[] atletas, int quantidade, OutputStream output) throws IOException {
        this.atletas = atletas;
        this.quantidade = quantidade;
        this.output = output;
        enviarAtletas();
    }

    private void enviarAtletas() throws IOException {
        for (int i = 0; i < quantidade; i++) {
            Atleta atleta = atletas[i];
            // Envia 3 atributos: nome, numero, posicao

            // Nome: envia tamanho em bytes (int 4 bytes) + bytes do nome
            byte[] nomeBytes = atleta.getNome().getBytes(StandardCharsets.UTF_8);
            escreverInt(nomeBytes.length);
            output.write(nomeBytes);

            // Número: envia como int 4 bytes
            escreverInt(atleta.getNumero());

            // Posição: envia tamanho em bytes + bytes da string
            byte[] posicaoBytes = atleta.getPosicao().getBytes(StandardCharsets.UTF_8);
            escreverInt(posicaoBytes.length);
            output.write(posicaoBytes);
        }
        output.flush();
    }

    // Método auxiliar para escrever int (4 bytes) no OutputStream, big-endian
    private void escreverInt(int valor) throws IOException {
        output.write((valor >> 24) & 0xFF);
        output.write((valor >> 16) & 0xFF);
        output.write((valor >> 8) & 0xFF);
        output.write(valor & 0xFF);
    }

    @Override
    public void write(int b) throws IOException {
        // Implementação obrigatória, mas não será usada diretamente
        throw new UnsupportedOperationException("Use o construtor para enviar os dados");
    }
}
