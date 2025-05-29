package q1;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorCampeonato {
    private List<TimeFutsal> times;
    private List<Resultado> resultados;

    public GerenciadorCampeonato() {
        this.times = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    // Adiciona um time ao campeonato
    public void adicionarTime(TimeFutsal time) {
        times.add(time);
    }

    // Registra resultado de uma partida
    public void registrarResultado(Resultado resultado) {
        resultados.add(resultado);
    }

    // Lista os times cadastrados
    public List<TimeFutsal> listarTimes() {
        return times;
    }

    // Lista os resultados registrados
    public List<Resultado> listarResultados() {
        return resultados;
    }

    // Outros métodos que desejar: calcular classificação, buscar time, etc.
}

