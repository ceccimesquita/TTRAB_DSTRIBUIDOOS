package Q5.modelo;

public class Eleitor {
    private String login;
    private String senha;
    private boolean votou;

    public Eleitor(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.votou = false;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }

    @Override
    public String toString() {
        return "Eleitor: " + login + " | Votou: " + (votou ? "Sim" : "Não");
    }
}
