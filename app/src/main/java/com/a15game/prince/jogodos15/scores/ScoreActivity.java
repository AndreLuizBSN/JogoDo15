package com.a15game.prince.jogodos15.scores;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.a15game.prince.jogodos15.R;
import com.a15game.prince.jogodos15.kernel.Util;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    private ListView listPontuacao;

    private Scores scores;
    private Util util;
    private MediaPlayer gamemusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        //app fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listPontuacao = (ListView) findViewById(R.id.listPontuacao);
        Button btnLimparPontuacao = (Button) findViewById(R.id.btnLimparPontuacao);


        //Inicializar as classes de controle do game e scores
        scores = new Scores(getApplicationContext());
        util = new Util();

        /*Montar a lista de pontuaçao*/
        montarListaPontuacao();

        //Botao de Limpar a poutuação do game
        btnLimparPontuacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparPontuacao();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //define o som do game em si
        gamemusic = MediaPlayer.create(this, R.raw.gamemusic);
        //Start no som do game e com loop infinito
        gamemusic.start();
        gamemusic.setLooping(true);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gamemusic.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //define o som do click
        gamemusic.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //define o som do click
        gamemusic.stop();
    }

    private void montarListaPontuacao() {
        ArrayList<Score> scoresList = scores.getScores();
        ArrayList<String> opcoes = new ArrayList<>();
        ArrayAdapter<String> adaptador;
        String dado;

        for(Score score : scoresList){
            dado = "Cliques: "+String.valueOf(score.getCliques());
            dado += " | Tempo: "+(util.segInStrTime(score.getTempo()));
            dado += " | "+String.valueOf(score.getStatus().name());
            opcoes.add(dado);
        }

        adaptador = new ArrayAdapter<>(ScoreActivity.this, android.R.layout.simple_list_item_1, opcoes);
        listPontuacao.setAdapter(adaptador);
    }

    public void limparPontuacao(){
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Limpar pontuações antigas");
        //define a mensagem
        builder.setMessage("Voce tem certeza que deseja limpar as pontuações antigas? Contara com perda!")
                //define um botão como positivo
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        scores.limparScores();
                        montarListaPontuacao();
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

}
