package modelos;

import Interfaces.IClinicaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClinicaService implements IClinicaService {
    private String nome;
    private List<Medico> medicos;

    public ClinicaService(String nome) {
        this.nome = nome;
        this.medicos = new ArrayList<>();
    }

    @Override
    public void adicionarMedico(Medico medico) {
        medicos.add(medico);
    }

    @Override
    public List<Medico> listarMedicos() {
        return medicos;
    }

    @Override
    public List<Medico> buscarPorNome(String nome) {
        return medicos.stream()
                .filter(m -> m.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medico> buscarPorEspecialidade(Class<? extends Medico> tipo) {
        return medicos.stream()
                .filter(tipo::isInstance)
                .collect(Collectors.toList());
    }
}