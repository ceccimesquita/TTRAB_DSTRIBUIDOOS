package modelos;

public class Arbitragem extends TimeFutsal {
    private String nomeArbitro;
    private String nivel;           // Ex: Estadual, Nacional, Internacional
    private int partidasApitadas;

    // Construtor padrão
    public Arbitragem() {}

    // Construtor completo
    public Arbitragem(String nomeTime, String cidade, String estado, String categoria, int anoFundacao,
                      String nomeArbitro, String nivel, int partidasApitadas) {
        super(nomeTime, cidade, estado, categoria, anoFundacao);
        this.nomeArbitro = nomeArbitro;
        this.nivel = nivel;
        this.partidasApitadas = partidasApitadas;
    }

    // Getters e Setters
    public String getNomeArbitro() {
        return nomeArbitro;
    }

    public void setNomeArbitro(String nomeArbitro) {
        this.nomeArbitro = nomeArbitro;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getPartidasApitadas() {
        return partidasApitadas;
    }

    public void setPartidasApitadas(int partidasApitadas) {
        this.partidasApitadas = partidasApitadas;
    }

    @Override
    public String toString() {
        return "Arbitragem{" +
                "nomeArbitro='" + nomeArbitro + '\'' +
                ", nivel='" + nivel + '\'' +
                ", partidasApitadas=" + partidasApitadas +
                ", time='" + getNome() + '\'' +
                ", cidade='" + getCidade() + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", categoria='" + getCategoria() + '\'' +
                ", anoFundacao=" + getAnoFundacao() +
                '}';
    }
}
