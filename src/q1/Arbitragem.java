package q1;

public class Arbitragem {
    private String nome;
    private String categoria;      // Ex: Regional, Nacional, Internacional
    private int experienciaAnos;   // Anos de experiência como árbitro

    // Construtor completo
    public Arbitragem(String nome, String categoria, int experienciaAnos) {
        this.nome = nome;
        this.categoria = categoria;
        this.experienciaAnos = experienciaAnos;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getExperienciaAnos() {
        return experienciaAnos;
    }

    public void setExperienciaAnos(int experienciaAnos) {
        this.experienciaAnos = experienciaAnos;
    }
}

