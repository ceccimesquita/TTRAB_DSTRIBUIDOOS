package modelos;

import java.io.Serializable;

public class Psiquiatra extends Medico implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean atendeOnline;
    private int duracaoConsultaMin;

    public Psiquiatra(String nome, String crm, String contato,
                      boolean atendeOnline, int duracaoConsultaMin) {
        super(nome, crm, contato);
        this.atendeOnline = atendeOnline;
        this.duracaoConsultaMin = duracaoConsultaMin;
    }

    public boolean isAtendeOnline() {
        return atendeOnline;
    }

    public void setAtendeOnline(boolean atendeOnline) {
        this.atendeOnline = atendeOnline;
    }

    public int getDuracaoConsultaMin() {
        return duracaoConsultaMin;
    }

    public void setDuracaoConsultaMin(int duracaoConsultaMin) {
        this.duracaoConsultaMin = duracaoConsultaMin;
    }

    @Override
    public String toString() {
        return "Psiquiatra {" +
                "Nome: '" + getNome() + '\'' +
                ", CRM: '" + getCrm() + '\'' +
                ", Contato: '" + getContato() + '\'' +
                ", Atende Online: " + (atendeOnline ? "Sim" : "Não") +
                ", Duração da Consulta: " + duracaoConsultaMin + " min" +
                '}';
    }
}