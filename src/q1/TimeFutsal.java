package q1;
import java.util.List;

public class TimeFutsal {
    private String nome;
    private String cidade;
    private String tecnico;
    private List<Atleta> atletas;      // Lista de jogadores
    private List<Arbitragem> arbitros; // Lista de árbitros do time

    // Construtor completo
    public TimeFutsal(String nome, String cidade, String tecnico, List<Atleta> atletas, List<Arbitragem> arbitros) {
        this.nome = nome;
        this.cidade = cidade;
        this.tecnico = tecnico;
        this.atletas = atletas;
        this.arbitros = arbitros;
    }

    // Getters e setters
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

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public List<Arbitragem> getArbitros() {
        return arbitros;
    }

    public void setArbitros(List<Arbitragem> arbitros) {
        this.arbitros = arbitros;
    }
}
