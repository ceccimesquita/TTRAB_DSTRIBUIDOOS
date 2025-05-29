package modelos;

public class ComissaoTecnica extends TimeFutsal {
    private String nome;
    private String funcao;         // Ex: Técnico, Auxiliar, Preparador Físico
    private int experienciaAnos;   // Anos de experiência

    // Construtor padrão
    public ComissaoTecnica() {}

    // Construtor completo
    public ComissaoTecnica(String nomeTime, String cidade, String estado, String categoria, int anoFundacao,
                           String nome, String funcao, int experienciaAnos) {
        super(nomeTime, cidade, estado, categoria, anoFundacao);
        this.nome = nome;
        this.funcao = funcao;
        this.experienciaAnos = experienciaAnos;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getExperienciaAnos() {
        return experienciaAnos;
    }

    public void setExperienciaAnos(int experienciaAnos) {
        this.experienciaAnos = experienciaAnos;
    }

    @Override
    public String toString() {
        return "ComissaoTecnica{" +
                "nome='" + nome + '\'' +
                ", funcao='" + funcao + '\'' +
                ", experienciaAnos=" + experienciaAnos +
                ", time='" + getNome() + '\'' +
                ", cidade='" + getCidade() + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", categoria='" + getCategoria() + '\'' +
                ", anoFundacao=" + getAnoFundacao() +
                '}';
    }
}
