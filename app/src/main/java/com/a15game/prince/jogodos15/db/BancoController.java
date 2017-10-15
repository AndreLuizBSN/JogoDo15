package com.a15game.prince.jogodos15.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a15game.prince.jogodos15.scores.Score;

/**
 * Created by prince on 14/10/17.
 */

public class BancoController {

    private SQLiteDatabase db;
    private DB banco;

    public BancoController(Context context){
        banco = new DB(context);
    }

    public String insereDado(Score score){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DB.getTEMPO(), score.getTempo());
        valores.put(DB.getMOVIMENTOS(), score.getCliques());
        valores.put(DB.getSTATUS(), score.getStatus().name());
        valores.put(DB.getL0C0(), score.getJogo()[0][0]);
        valores.put(DB.getL0C1(), score.getJogo()[0][1]);
        valores.put(DB.getL0C2(), score.getJogo()[0][2]);
        valores.put(DB.getL0C3(), score.getJogo()[0][3]);
        valores.put(DB.getL1C0(), score.getJogo()[1][0]);
        valores.put(DB.getL1C1(), score.getJogo()[1][1]);
        valores.put(DB.getL1C2(), score.getJogo()[1][2]);
        valores.put(DB.getL1C3(), score.getJogo()[1][3]);
        valores.put(DB.getL2C0(), score.getJogo()[2][0]);
        valores.put(DB.getL2C1(), score.getJogo()[2][1]);
        valores.put(DB.getL2C2(), score.getJogo()[2][2]);
        valores.put(DB.getL2C3(), score.getJogo()[2][3]);
        valores.put(DB.getL3C0(), score.getJogo()[3][0]);
        valores.put(DB.getL3C1(), score.getJogo()[3][1]);
        valores.put(DB.getL3C2(), score.getJogo()[3][2]);
        valores.put(DB.getL3C3(), score.getJogo()[3][3]);

        resultado = db.insert(DB.getTABELA(), null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {DB.getTEMPO(),DB.getMOVIMENTOS(),DB.getSTATUS()};
        db = banco.getReadableDatabase();
        cursor = db.query(DB.getTABELA(), campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void deletaRegistro(int id){
        String where = DB.getID() + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(DB.getTABELA(),where,null);
        db.close();
    }

    public void deletaTodosRegistro(){
        db = banco.getReadableDatabase();
        db.delete(DB.getTABELA(),null,null);
        db.close();
    }
}