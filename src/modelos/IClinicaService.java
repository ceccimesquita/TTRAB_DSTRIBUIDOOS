package modelos;

import java.util.List;

public interface IClinicaService {
    void adicionarMedico(Medico medico);
    List<Medico> listarMedicos();
    List<Medico> buscarPorNome(String nome);
    List<Medico> buscarPorEspecialidade(Class<? extends Medico> tipo);
}
