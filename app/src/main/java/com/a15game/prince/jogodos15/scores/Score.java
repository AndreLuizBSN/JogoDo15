package com.a15game.prince.jogodos15.scores;

/**
 * Created by prince on 14/10/17.
 */

public class Score {

    private long tempo;
    private long cliques;
    private int[][] jogo;
    private Status status;

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public long getCliques() {
        return cliques;
    }

    public void setCliques(long cliques) {
        this.cliques = cliques;
    }

    public int[][] getJogo() {
        return jogo;
    }

    public void setJogo(int[][] jogo) {
        this.jogo = jogo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
