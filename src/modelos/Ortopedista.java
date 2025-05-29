package modelos;

public class Ortopedista extends Medico {
    private String areaEspecializada;
    private boolean realizaCirurgia;

    public Ortopedista(String nome, String crm, String contato,
                       String areaEspecializada, boolean realizaCirurgia) {
        super(nome, crm, contato);
        this.areaEspecializada = areaEspecializada;
        this.realizaCirurgia = realizaCirurgia;
    }

    public String getAreaEspecializada() {
        return areaEspecializada;
    }

    public void setAreaEspecializada(String areaEspecializada) {
        this.areaEspecializada = areaEspecializada;
    }

    public boolean isRealizaCirurgia() {
        return realizaCirurgia;
    }

    public void setRealizaCirurgia(boolean realizaCirurgia) {
        this.realizaCirurgia = realizaCirurgia;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Área Especializada: " + areaEspecializada
                + ", Realiza Cirurgia: " + (realizaCirurgia ? "Sim" : "Não");
    }
}
