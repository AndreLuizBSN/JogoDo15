package com.a15game.prince.jogodos15.scores;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

import com.a15game.prince.jogodos15.R;
import com.a15game.prince.jogodos15.db.BancoController;
import com.a15game.prince.jogodos15.db.DB;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by prince on 10/10/17.
 */

public class Scores {

    private Context context;

    public Scores(Context context){
        this.context = context;
    }

    public void novoScore(Score s){
        BancoController b = new BancoController(this.context);
        b.insereDado(s);
    }

    public ArrayList<Score> getScores(){


        ArrayList<Score> exportedScores = new ArrayList<>();
        Score s;

        BancoController crud = new BancoController(this.context);
        Cursor cursor = crud.carregaDados();

        try {
            while (cursor.moveToNext()) {
                s = new Score();
                s.setTempo(cursor.getLong(cursor.getColumnIndex("tempo")));
                s.setCliques(cursor.getLong(cursor.getColumnIndex("movimentos")));
                s.setStatus(Status.valueOf(cursor.getString(cursor.getColumnIndex("status"))));
                s.setJogo(null);
                exportedScores.add(s);
            }
        } finally {
            cursor.close();
        }
        return exportedScores;
    }

    public void limparScores(){
        new BancoController(this.context).deletaTodosRegistro();
    }
}
