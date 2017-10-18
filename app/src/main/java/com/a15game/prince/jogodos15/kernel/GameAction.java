package com.a15game.prince.jogodos15.kernel;

import java.util.ArrayList;
import java.util.Random;

public class GameAction {

    private long contaMovimentos;

    public GameAction(){
        this.contaMovimentos = 0;
    }

    //funçao de jogar
    public int[][] jogar(){
        //inicia a contagem do tempo
        int[][] jogo = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        //playTimer();
        //Se for primeira vez ele desativa para nao apareceer a mensagem que venceu na primeira vez que abre o app
        //primeiraVez = false;
        //variavel que controla os numeros ja sorteados para o jogo
        ArrayList<Integer> numerosUsados = new ArrayList<>();
        //variavel que sorteara os numeros para cada botao
        Random rand = new Random();
        int randomNum;
        //for sobre as linhas
        for(int l = 0; l<4; l++){
            //for sobre as colunas
            for(int c = 0; c<4; c++){
                //sorteio
                randomNum = rand.nextInt((15) + 1);
                //caso o numero sorteado ja tenha saido anteriormente
                while(numerosUsados.contains(randomNum)){
                    //sorteia outra vez
                    randomNum = rand.nextInt((15) + 1);
                }
                //adiciona o numero aos numeros ja usados
                numerosUsados.add(randomNum);
                //adiciona o numero a posiçao correta
                jogo[l][c] = randomNum;
            }
        }
        return jogo;
    }


    /*Ira passar por parametro qual a linha e coluna clicado
    * O sistema ira verificar se e possivel mover, e para qual posicao*/
    public int[][] analisar(int[][] jogo, int l, int c){
        System.out.println("linha: "+l+", coluna: "+c);
        System.out.println("Valor do botao: "+jogo[l][c]);
        //analisa a linha
        if(l > 0){
            if(jogo[l-1][c] == 0){
                jogo[l-1][c] = jogo[l][c];
                jogo[l][c] = 0;
                this.contaMovimentos += 1;
            }
        }
        if(l < 3){
            if(jogo[l+1][c] == 0){
                jogo[l+1][c] = jogo[l][c];
                jogo[l][c] = 0;
                this.contaMovimentos += 1;
            }
        }

        //analisa a coluna
        if(c > 0){
            if(jogo[l][c-1] == 0){
                jogo[l][c-1] = jogo[l][c];
                jogo[l][c] = 0;
                this.contaMovimentos += 1;
            }
        }
        if(c < 3){
            if(jogo[l][c+1] == 0){
                jogo[l][c+1] = jogo[l][c];
                jogo[l][c] = 0;
                this.contaMovimentos += 1;
            }
        }
        return jogo;
    }

    public long getMovimentos(){
        return this.contaMovimentos;
    }

    public void setContaMovimentos(long contaMovimentos) {
        this.contaMovimentos = contaMovimentos;
    }
}
