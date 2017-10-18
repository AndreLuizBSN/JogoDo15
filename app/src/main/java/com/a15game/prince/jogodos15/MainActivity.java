package com.a15game.prince.jogodos15;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.a15game.prince.jogodos15.kernel.GameAction;
import com.a15game.prince.jogodos15.kernel.Util;
import com.a15game.prince.jogodos15.scores.Score;
import com.a15game.prince.jogodos15.scores.ScoreActivity;
import com.a15game.prince.jogodos15.scores.Scores;
import com.a15game.prince.jogodos15.scores.Status;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn00;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn20;
    private Button btn21;
    private Button btn22;
    private Button btn23;
    private Button btn30;
    private Button btn31;
    private Button btn32;
    private Button btn33;
    private Button btnJogar;
    private Button btnCancelar;
    private Button btnPontuacao;
    private int[][] jogo;
    private AlertDialog alerta;
    private boolean primeiraVez = true;
    private boolean jogando = false;
    private MediaPlayer mp, gamemusic;
    private long timeGame;
    private TextView txtTimer;

    private Handler m_handler;
    private Runnable m_handlerTask;

    private GameAction gameAction;
    private Scores scores;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //app fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*Definiçao de todos as variaveis com os elementos*/
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        btn00 = (Button) findViewById(R.id.btn00);
        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        btn03 = (Button) findViewById(R.id.btn03);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);
        btn13 = (Button) findViewById(R.id.btn13);
        btn20 = (Button) findViewById(R.id.btn20);
        btn21 = (Button) findViewById(R.id.btn21);
        btn22 = (Button) findViewById(R.id.btn22);
        btn23 = (Button) findViewById(R.id.btn23);
        btn30 = (Button) findViewById(R.id.btn30);
        btn31 = (Button) findViewById(R.id.btn31);
        btn32 = (Button) findViewById(R.id.btn32);
        btn33 = (Button) findViewById(R.id.btn33);
        btnJogar = (Button) findViewById(R.id.btnJogar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnPontuacao = (Button) findViewById(R.id.btnScores);


        //Inicializar as classes de controle do game e scores
        gameAction = new GameAction();
        scores = new Scores(getApplicationContext());
        util = new Util();

        //inicializar a variavel Jogo
        jogo = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
        };
        //Atribui a matriz de jogo para os botoes
        atribuir(jogo);

        //Inicia o jogo com tempo 0(zero)
        timeGame = 0;

        //definir o botão de cancelar como false para não cancelar um jogo não começado
        btnCancelar.setClickable(false);

        //Botao de inicio do game
        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnJogar.getText().toString().equals("Jogar")){
                    primeiraVez = false;
                    jogando = true;
                    //definir o botão de cancelar como true para possa cancelar um jogo começado
                    btnCancelar.setClickable(true);
                    jogo = gameAction.jogar();
                    atribuir(jogo);
                    playTimer();
                }else if(btnJogar.getText().toString().equals("Pausar")){
                    pauseTimer();
                    jogando = false;
                    System.out.println(gameAction.getMovimentos());
                }else{
                    atribuir(jogo);
                    jogando = true;
                    playTimer();
                }
            }
        });

        //Botao de cancelamento do Game
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        //Botao de Limpar a poutuação do game
        btnPontuacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
                startActivity(intent);
            }
        });

        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,0,0);
                atribuir(jogo);
                mp.start();
            }
        });
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,0,1);
                atribuir(jogo);
                mp.start();
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,0,2);
                atribuir(jogo);
                mp.start();
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,0,3);
                atribuir(jogo);
                mp.start();
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,1,0);
                atribuir(jogo);
                mp.start();
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,1,1);
                atribuir(jogo);
                mp.start();
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,1,2);
                atribuir(jogo);
                mp.start();
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,1,3);
                atribuir(jogo);
                mp.start();
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,2,0);
                atribuir(jogo);
                mp.start();
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,2,1);
                atribuir(jogo);
                mp.start();
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,2,2);
                atribuir(jogo);
                mp.start();
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,2,3);
                atribuir(jogo);
                mp.start();
            }
        });
        btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,3,0);
                atribuir(jogo);
                mp.start();
            }
        });
        btn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,3,1);
                atribuir(jogo);
                mp.start();
            }
        });
        btn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,3,2);
                atribuir(jogo);
                mp.start();
            }
        });
        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogo = gameAction.analisar(jogo,3,3);
                atribuir(jogo);
                mp.start();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");

        //define o som do click
        mp = MediaPlayer.create(this, R.raw.clickbuttom);
        //define o som do game em si
        gamemusic = MediaPlayer.create(this, R.raw.gamemusic);
        //Start no som do game e com loop infinito
        gamemusic.start();
        gamemusic.setLooping(true);

    }

    @Override
    protected void onRestart() {
        System.out.println("onRestart");
        super.onRestart();
        gamemusic.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
        //define o som do click
        gamemusic.stop();
        if(jogando){
            try{
                pauseTimer();
            }catch (Exception ignored){

            }
            btnJogar.setText(R.string.continuar);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");

        //define o som do click
        gamemusic.stop();
        if(jogando){
            try{
                pauseTimer();
            }catch (Exception ignored){

            }
            btnJogar.setText(R.string.continuar);
        }
    }

    public void cancelar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Cancelar Jogada");
        //define a mensagem
        builder.setMessage("Voce tem certeza que deseja cancelar a jogada? Contara com perda!")
        //define um botão como positivo
        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                Score s = new Score();
                s.setJogo(jogo);
                s.setTempo(timeGame);
                s.setCliques(gameAction.getMovimentos());
                s.setStatus(Status.DESISTIU);

                scores.novoScore(s);
                try{
                    stopTimer();
                }catch (Exception ignored){

                }
                //Jogar novamente
                jogo = gameAction.jogar();
                atribuir(jogo);
            }
        })
        //define um botão como negativo.
        .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //jogar();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
        alerta.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.RED);
        alerta.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK);
    }

    /*Funçao para atribuir o que existe na matriz jogo aos botoes*/
    public void atribuir(int[][] jogo){

        //torna os botoes clicaveis
        btn00.setClickable(true);
        btn01.setClickable(true);
        btn02.setClickable(true);
        btn03.setClickable(true);
        btn10.setClickable(true);
        btn11.setClickable(true);
        btn12.setClickable(true);
        btn13.setClickable(true);
        btn20.setClickable(true);
        btn21.setClickable(true);
        btn22.setClickable(true);
        btn23.setClickable(true);
        btn30.setClickable(true);
        btn31.setClickable(true);
        btn32.setClickable(true);
        btn33.setClickable(true);

        //Pega a informaçao que esta na matriz de jogo e aplica aos botoes
        btn00.setText(String.valueOf(jogo[0][0]));
        btn01.setText(String.valueOf(jogo[0][1]));
        btn02.setText(String.valueOf(jogo[0][2]));
        btn03.setText(String.valueOf(jogo[0][3]));
        btn10.setText(String.valueOf(jogo[1][0]));
        btn11.setText(String.valueOf(jogo[1][1]));
        btn12.setText(String.valueOf(jogo[1][2]));
        btn13.setText(String.valueOf(jogo[1][3]));
        btn20.setText(String.valueOf(jogo[2][0]));
        btn21.setText(String.valueOf(jogo[2][1]));
        btn22.setText(String.valueOf(jogo[2][2]));
        btn23.setText(String.valueOf(jogo[2][3]));
        btn30.setText(String.valueOf(jogo[3][0]));
        btn31.setText(String.valueOf(jogo[3][1]));
        btn32.setText(String.valueOf(jogo[3][2]));
        btn33.setText(String.valueOf(jogo[3][3]));

        /*Valida para o numero 0*/
        if(btn00.getText().equals("0")){btn00.setText("");btn00.setActivated(false);btn00.setEnabled(false);btn00.setClickable(false);}else{
            btn00.setActivated(true);btn00.setEnabled(true);}
        if(btn01.getText().equals("0")){btn01.setText("");btn01.setActivated(false);btn01.setEnabled(false);btn01.setClickable(false);}else{
            btn01.setActivated(true);btn01.setEnabled(true);}
        if(btn02.getText().equals("0")){btn02.setText("");btn02.setActivated(false);btn02.setEnabled(false);btn02.setClickable(false);}else{
            btn02.setActivated(true);btn02.setEnabled(true);}
        if(btn03.getText().equals("0")){btn03.setText("");btn03.setActivated(false);btn03.setEnabled(false);btn03.setClickable(false);}else{
            btn03.setActivated(true);btn03.setEnabled(true);}
        if(btn10.getText().equals("0")){btn10.setText("");btn10.setActivated(false);btn10.setEnabled(false);btn10.setClickable(false);}else{
            btn10.setActivated(true);btn10.setEnabled(true);}
        if(btn11.getText().equals("0")){btn11.setText("");btn11.setActivated(false);btn11.setEnabled(false);btn11.setClickable(false);}else{
            btn11.setActivated(true);btn11.setEnabled(true);}
        if(btn12.getText().equals("0")){btn12.setText("");btn12.setActivated(false);btn12.setEnabled(false);btn12.setClickable(false);}else{
            btn12.setActivated(true);btn12.setEnabled(true);}
        if(btn13.getText().equals("0")){btn13.setText("");btn13.setActivated(false);btn13.setEnabled(false);btn13.setClickable(false);}else{
            btn13.setActivated(true);btn13.setEnabled(true);}
        if(btn20.getText().equals("0")){btn20.setText("");btn20.setActivated(false);btn20.setEnabled(false);btn20.setClickable(false);}else{
            btn20.setActivated(true);btn20.setEnabled(true);}
        if(btn21.getText().equals("0")){btn21.setText("");btn21.setActivated(false);btn21.setEnabled(false);btn21.setClickable(false);}else{
            btn21.setActivated(true);btn21.setEnabled(true);}
        if(btn22.getText().equals("0")){btn22.setText("");btn22.setActivated(false);btn22.setEnabled(false);btn22.setClickable(false);}else{
            btn22.setActivated(true);btn22.setEnabled(true);}
        if(btn23.getText().equals("0")){btn23.setText("");btn23.setActivated(false);btn23.setEnabled(false);btn23.setClickable(false);}else{
            btn23.setActivated(true);btn23.setEnabled(true);}
        if(btn30.getText().equals("0")){btn30.setText("");btn30.setActivated(false);btn30.setEnabled(false);btn30.setClickable(false);}else{
            btn30.setActivated(true);btn30.setEnabled(true);}
        if(btn31.getText().equals("0")){btn31.setText("");btn31.setActivated(false);btn31.setEnabled(false);btn31.setClickable(false);}else{
            btn31.setActivated(true);btn31.setEnabled(true);}
        if(btn32.getText().equals("0")){btn32.setText("");btn32.setActivated(false);btn32.setEnabled(false);btn32.setClickable(false);}else{
            btn32.setActivated(true);btn32.setEnabled(true);}
        if(btn33.getText().equals("0")){btn33.setText("");btn33.setActivated(false);btn33.setEnabled(false);btn33.setClickable(false);}else{
            btn33.setActivated(true);btn33.setEnabled(true);}

        //valida se ganhou ou nao
        if(jogo[0][0] == 1
                && jogo[0][1] == 2
                && jogo[0][2] == 3
                && jogo[0][3] == 4
                && jogo[1][0] == 5
                && jogo[1][1] == 6
                && jogo[1][2] == 7
                && jogo[1][3] == 8
                && jogo[2][0] == 9
                && jogo[2][1] == 10
                && jogo[2][2] == 11
                && jogo[2][3] == 12
                && jogo[3][0] == 13
                && jogo[3][1] == 14
                && jogo[3][2] == 15
                && jogo[3][3] == 0 && !primeiraVez){



            Score s = new Score();
            s.setJogo(jogo);
            s.setTempo(timeGame);
            s.setCliques(gameAction.getMovimentos());
            s.setStatus(Status.DESISTIU);

            scores.novoScore(s);

            stopTimer();

            //Cria o gerador do AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Vencedor");
            //define a mensagem
            builder.setMessage("Voce venceu!!!")
            //define um botão como positivo
            .setPositiveButton("Ver ranking", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    //implementar
                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
            alerta.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.RED);
            alerta.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK);

        }

    }


    //Funcao para iniciar o tempo de game
    public void playTimer(){
        btnJogar.setText(R.string.pausar);

        m_handler = new Handler();
        m_handlerTask = new Runnable()
        {
            @Override
            public void run() {
                m_handler.postDelayed(m_handlerTask, 1000);
                timeGame += 1;
                //System.out.println("timeGame: "+timeGame);
                CharSequence text = "Tempo: "+String.valueOf(timeGame)+" Seg";
                txtTimer.setText(text);
            }
        };
        m_handlerTask.run();
    }
    //Funcao para pausar o tempo de game
    public void pauseTimer(){
        m_handler.removeCallbacks(m_handlerTask);
        btnJogar.setText(R.string.continuar);

        btn00.setText("");
        btn01.setText("");
        btn02.setText("");
        btn03.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn13.setText("");
        btn20.setText("");
        btn21.setText("");
        btn22.setText("");
        btn23.setText("");
        btn30.setText("");
        btn31.setText("");
        btn32.setText("");
        btn33.setText("");

        btn00.setClickable(false);
        btn01.setClickable(false);
        btn02.setClickable(false);
        btn03.setClickable(false);
        btn10.setClickable(false);
        btn11.setClickable(false);
        btn12.setClickable(false);
        btn13.setClickable(false);
        btn20.setClickable(false);
        btn21.setClickable(false);
        btn22.setClickable(false);
        btn23.setClickable(false);
        btn30.setClickable(false);
        btn31.setClickable(false);
        btn32.setClickable(false);
        btn33.setClickable(false);

    }
    //Funcao para parar o tempo de game
    public void stopTimer(){
        m_handler.removeCallbacks(m_handlerTask);
        btnJogar.setText(R.string.jogar);

        //seta os movimentos e tempo como zero
        gameAction.setContaMovimentos(0);
        timeGame = 0;


        CharSequence text = "Tempo: 0 Seg";
        txtTimer.setText(text);

        btn00.setText(R.string.b01);
        btn01.setText(R.string.b02);
        btn02.setText(R.string.b03);
        btn03.setText(R.string.b04);
        btn10.setText(R.string.b05);
        btn11.setText(R.string.b06);
        btn12.setText(R.string.b07);
        btn13.setText(R.string.b08);
        btn20.setText(R.string.b09);
        btn21.setText(R.string.b10);
        btn22.setText(R.string.b11);
        btn23.setText(R.string.b12);
        btn30.setText(R.string.b13);
        btn31.setText(R.string.b14);
        btn32.setText(R.string.b15);
        btn33.setText("");

        btn00.setClickable(false);
        btn01.setClickable(false);
        btn02.setClickable(false);
        btn03.setClickable(false);
        btn10.setClickable(false);
        btn11.setClickable(false);
        btn12.setClickable(false);
        btn13.setClickable(false);
        btn20.setClickable(false);
        btn21.setClickable(false);
        btn22.setClickable(false);
        btn23.setClickable(false);
        btn30.setClickable(false);
        btn31.setClickable(false);
        btn32.setClickable(false);
        btn33.setClickable(false);

    }

}
