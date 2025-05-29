package q1;

import java.util.*;

public class ServicoClassificacao {
    private List<TimeFutsal> times;
    private List<Resultado> resultados;

    public ServicoClassificacao(List<TimeFutsal> times, List<Resultado> resultados) {
        this.times = times;
        this.resultados = resultados;
    }

    // Calcula pontos de cada time
    public Map<TimeFutsal, Integer> calcularPontos() {
        Map<TimeFutsal, Integer> pontos = new HashMap<>();
        for (TimeFutsal time : times) {
            pontos.put(time, 0);
        }
        for (Resultado res : resultados) {
            if (res.getGolsTimeA() > res.getGolsTimeB()) {
                pontos.put(res.getTimeA(), pontos.get(res.getTimeA()) + 3);
            } else if (res.getGolsTimeA() < res.getGolsTimeB()) {
                pontos.put(res.getTimeB(), pontos.get(res.getTimeB()) + 3);
            } else {
                pontos.put(res.getTimeA(), pontos.get(res.getTimeA()) + 1);
                pontos.put(res.getTimeB(), pontos.get(res.getTimeB()) + 1);
            }
        }
        return pontos;
    }

    // Calcula saldo de gols de cada time (gols pró - gols contra)
    public Map<TimeFutsal, Integer> calcularSaldoGols() {
        Map<TimeFutsal, Integer> saldoGols = new HashMap<>();
        for (TimeFutsal time : times) {
            saldoGols.put(time, 0);
        }
        for (Resultado res : resultados) {
            saldoGols.put(res.getTimeA(), saldoGols.get(res.getTimeA()) + res.getGolsTimeA() - res.getGolsTimeB());
            saldoGols.put(res.getTimeB(), saldoGols.get(res.getTimeB()) + res.getGolsTimeB() - res.getGolsTimeA());
        }
        return saldoGols;
    }

    // Calcula vitórias de cada time
    public Map<TimeFutsal, Integer> calcularVitorias() {
        Map<TimeFutsal, Integer> vitorias = new HashMap<>();
        for (TimeFutsal time : times) {
            vitorias.put(time, 0);
        }
        for (Resultado res : resultados) {
            if (res.getGolsTimeA() > res.getGolsTimeB()) {
                vitorias.put(res.getTimeA(), vitorias.get(res.getTimeA()) + 1);
            } else if (res.getGolsTimeB() > res.getGolsTimeA()) {
                vitorias.put(res.getTimeB(), vitorias.get(res.getTimeB()) + 1);
            }
        }
        return vitorias;
    }

    // Calcula empates de cada time
    public Map<TimeFutsal, Integer> calcularEmpates() {
        Map<TimeFutsal, Integer> empates = new HashMap<>();
        for (TimeFutsal time : times) {
            empates.put(time, 0);
        }
        for (Resultado res : resultados) {
            if (res.getGolsTimeA() == res.getGolsTimeB()) {
                empates.put(res.getTimeA(), empates.get(res.getTimeA()) + 1);
                empates.put(res.getTimeB(), empates.get(res.getTimeB()) + 1);
            }
        }
        return empates;
    }

    // Calcula derrotas de cada time
    public Map<TimeFutsal, Integer> calcularDerrotas() {
        Map<TimeFutsal, Integer> derrotas = new HashMap<>();
        for (TimeFutsal time : times) {
            derrotas.put(time, 0);
        }
        for (Resultado res : resultados) {
            if (res.getGolsTimeA() > res.getGolsTimeB()) {
                derrotas.put(res.getTimeB(), derrotas.get(res.getTimeB()) + 1);
            } else if (res.getGolsTimeB() > res.getGolsTimeA()) {
                derrotas.put(res.getTimeA(), derrotas.get(res.getTimeA()) + 1);
            }
        }
        return derrotas;
    }

    // Retorna lista de times ordenados pelo ranking (pontos, saldo de gols, vitórias)
    public List<TimeFutsal> ordenarRanking() {
        Map<TimeFutsal, Integer> pontos = calcularPontos();
        Map<TimeFutsal, Integer> saldoGols = calcularSaldoGols();
        Map<TimeFutsal, Integer> vitorias = calcularVitorias();

        List<TimeFutsal> lista = new ArrayList<>(times);
        lista.sort((t1, t2) -> {
            int cmp = pontos.get(t2).compareTo(pontos.get(t1)); // Pontos decrescente
            if (cmp != 0) return cmp;

            cmp = saldoGols.get(t2).compareTo(saldoGols.get(t1)); // Saldo de gols decrescente
            if (cmp != 0) return cmp;

            return vitorias.get(t2).compareTo(vitorias.get(t1));  // Vitórias decrescente
        });
        return lista;
    }

    // Método para imprimir a tabela de classificação
    public void imprimirClassificacao() {
        List<TimeFutsal> ranking = ordenarRanking();
        Map<TimeFutsal, Integer> pontos = calcularPontos();
        Map<TimeFutsal, Integer> saldoGols = calcularSaldoGols();
        Map<TimeFutsal, Integer> vitorias = calcularVitorias();
        Map<TimeFutsal, Integer> empates = calcularEmpates();
        Map<TimeFutsal, Integer> derrotas = calcularDerrotas();

        System.out.printf("%-20s %5s %5s %5s %5s %5s\n", "Time", "Pts", "V", "E", "D", "SG");
        for (TimeFutsal time : ranking) {
            System.out.printf("%-20s %5d %5d %5d %5d %5d\n",
                    time.getNome(),
                    pontos.get(time),
                    vitorias.get(time),
                    empates.get(time),
                    derrotas.get(time),
                    saldoGols.get(time));
        }
    }
}

