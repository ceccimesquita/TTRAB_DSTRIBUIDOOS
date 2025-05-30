package Q5.modelo;

public class Candidato {
    private String nome;
    private int votos;

    public Candidato(String nome) {
        this.nome = nome;
        this.votos = 0;
    }

    public String getNome() {
        return nome;
    }

    public synchronized void adicionarVoto() {
        votos++;
    }

    public int getVotos() {
        return votos;
    }

    @Override
    public String toString() {
        return nome + " - Votos: " + votos;
    }
}
