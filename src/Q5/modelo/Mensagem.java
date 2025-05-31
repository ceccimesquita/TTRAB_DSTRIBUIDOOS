package Q5.modelo;

public class Mensagem {
    private String tipo;
    private String conteudo;

    public Mensagem() {}

    public Mensagem(String tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}

