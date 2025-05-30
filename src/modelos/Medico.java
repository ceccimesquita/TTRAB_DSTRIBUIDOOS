package modelos;

import java.io.Serializable;

public class Medico implements Serializable {
    private String nome;
    private String crm;
    private String contato;

    public Medico() {}

    public Medico(String nome, String crm, String contato) {
        this.nome = nome;
        this.crm = crm;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}

