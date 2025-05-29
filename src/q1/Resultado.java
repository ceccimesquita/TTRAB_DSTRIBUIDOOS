package q1;

import java.time.LocalDate;

public class Resultado {
    private TimeFutsal timeA;
    private TimeFutsal timeB;
    private int golsTimeA;
    private int golsTimeB;
    private LocalDate dataPartida;

    // Construtor completo
    public Resultado(TimeFutsal timeA, TimeFutsal timeB, int golsTimeA, int golsTimeB, LocalDate dataPartida) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
        this.dataPartida = dataPartida;
    }

    // Getters e setters
    public TimeFutsal getTimeA() {
        return timeA;
    }

    public void setTimeA(TimeFutsal timeA) {
        this.timeA = timeA;
    }

    public TimeFutsal getTimeB() {
        return timeB;
    }

    public void setTimeB(TimeFutsal timeB) {
        this.timeB = timeB;
    }

    public int getGolsTimeA() {
        return golsTimeA;
    }

    public void setGolsTimeA(int golsTimeA) {
        this.golsTimeA = golsTimeA;
    }

    public int getGolsTimeB() {
        return golsTimeB;
    }

    public void setGolsTimeB(int golsTimeB) {
        this.golsTimeB = golsTimeB;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }
}

