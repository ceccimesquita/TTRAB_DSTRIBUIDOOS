package modelos;

public class Pediatra extends Medico {
    private String faixaEtariaAtendida;
    private boolean possuiEspecializacaoNeonatal;

    public Pediatra(String nome, String crm, String contato,
                    String faixaEtariaAtendida, boolean possuiEspecializacaoNeonatal) {
        super(nome, crm, contato);
        this.faixaEtariaAtendida = faixaEtariaAtendida;
        this.possuiEspecializacaoNeonatal = possuiEspecializacaoNeonatal;
    }

    public String getFaixaEtariaAtendida() {
        return faixaEtariaAtendida;
    }

    public void setFaixaEtariaAtendida(String faixaEtariaAtendida) {
        this.faixaEtariaAtendida = faixaEtariaAtendida;
    }

    public boolean isPossuiEspecializacaoNeonatal() {
        return possuiEspecializacaoNeonatal;
    }

    public void setPossuiEspecializacaoNeonatal(boolean possuiEspecializacaoNeonatal) {
        this.possuiEspecializacaoNeonatal = possuiEspecializacaoNeonatal;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Faixa Etária Atendida: " + faixaEtariaAtendida
                + ", Especialização Neonatal: " + (possuiEspecializacaoNeonatal ? "Sim" : "Não");
    }
}
