package modelos;

public class TimeFutsal {
    private String nome;
    private String cidade;
    private String estado;
    private String categoria; // Ex: Sub-15, Adulto, Feminino
    private int anoFundacao;

    // Construtor padrão
    public TimeFutsal() {}

    // Construtor completo
    public TimeFutsal(String nome, String cidade, String estado, String categoria, int anoFundacao) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.categoria = categoria;
        this.anoFundacao = anoFundacao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    @Override
    public String toString() {
        return "TimeDeFutsal{" +
                "nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", categoria='" + categoria + '\'' +
                ", anoFundacao=" + anoFundacao +
                '}';
    }
}
