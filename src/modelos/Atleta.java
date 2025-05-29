package modelos;

public class Atleta extends TimeFutsal {
    private String nomeCompleto;
    private int idade;
    private String posicao;      // Ex: Goleiro, Fixo, Ala, Pivô
    private int numeroCamisa;

    // Construtor padrão
    public Atleta() {}

    // Construtor completo
    public Atleta(String nome, String cidade, String estado, String categoria, int anoFundacao,
                  String nomeCompleto, int idade, String posicao, int numeroCamisa) {
        super(nome, cidade, estado, categoria, anoFundacao);
        this.nomeCompleto = nomeCompleto;
        this.idade = idade;
        this.posicao = posicao;
        this.numeroCamisa = numeroCamisa;
    }

    // Getters e Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    @Override
    public String toString() {
        return "Atleta{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", idade=" + idade +
                ", posicao='" + posicao + '\'' +
                ", numeroCamisa=" + numeroCamisa +
                ", time='" + getNome() + '\'' +
                ", cidade='" + getCidade() + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", categoria='" + getCategoria() + '\'' +
                ", anoFundacao=" + getAnoFundacao() +
                '}';
    }
}
