package Stream.TCP_JSON;

public class Resposta {
    private String status;
    private String mensagem;

    public Resposta(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
