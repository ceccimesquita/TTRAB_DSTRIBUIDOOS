package q1;

public class Atleta {
    private String nome;
    private int numero;
    private String posicao;
    private int idade;

    // Construtor completo
    public Atleta(String nome, int numero, String posicao, int idade) {
        this.nome = nome;
        this.numero = numero;
        this.posicao = posicao;
        this.idade = idade;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
