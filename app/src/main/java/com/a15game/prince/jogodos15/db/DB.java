package com.a15game.prince.jogodos15.db;

/**
 * Created by prince on 14/10/17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "jogo.db";
    private static final String TABELA = "score";
    private static final String ID = "_id";
    private static final String TEMPO = "tempo";
    private static final String MOVIMENTOS = "movimentos";
    private static final String STATUS = "status";
    private static final String L0C0 = "l0c0";
    private static final String L0C1 = "l0c1";
    private static final String L0C2 = "l0c2";
    private static final String L0C3 = "l0c3";
    private static final String L1C0 = "l1c0";
    private static final String L1C1 = "l1c1";
    private static final String L1C2 = "l1c2";
    private static final String L1C3 = "l1c3";
    private static final String L2C0 = "l2c0";
    private static final String L2C1 = "l2c1";
    private static final String L2C2 = "l2c2";
    private static final String L2C3 = "l2c3";
    private static final String L3C0 = "l3c0";
    private static final String L3C1 = "l3c1";
    private static final String L3C2 = "l3c2";
    private static final String L3C3 = "l3c3";
    private static final int VERSAO = 1;

    public DB(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + TEMPO + " integer,"
                + MOVIMENTOS + " integer,"
                + STATUS + " text,"
                + L0C0 + " integer,"
                + L0C1 + " integer,"
                + L0C2 + " integer,"
                + L0C3 + " integer,"
                + L1C0 + " integer,"
                + L1C1 + " integer,"
                + L1C2 + " integer,"
                + L1C3 + " integer,"
                + L2C0 + " integer,"
                + L2C1 + " integer,"
                + L2C2 + " integer,"
                + L2C3 + " integer,"
                + L3C0 + " integer,"
                + L3C1 + " integer,"
                + L3C2 + " integer,"
                + L3C3 + " integer"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getID() {
        return ID;
    }

    public static String getTEMPO() {
        return TEMPO;
    }

    public static String getMOVIMENTOS() {
        return MOVIMENTOS;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public static String getL0C0() {
        return L0C0;
    }

    public static String getL0C1() {
        return L0C1;
    }

    public static String getL0C2() {
        return L0C2;
    }

    public static String getL0C3() {
        return L0C3;
    }

    public static String getL1C0() {
        return L1C0;
    }

    public static String getL1C1() {
        return L1C1;
    }

    public static String getL1C2() {
        return L1C2;
    }

    public static String getL1C3() {
        return L1C3;
    }

    public static String getL2C0() {
        return L2C0;
    }

    public static String getL2C1() {
        return L2C1;
    }

    public static String getL2C2() {
        return L2C2;
    }

    public static String getL2C3() {
        return L2C3;
    }

    public static String getL3C0() {
        return L3C0;
    }

    public static String getL3C1() {
        return L3C1;
    }

    public static String getL3C2() {
        return L3C2;
    }

    public static String getL3C3() {
        return L3C3;
    }
}
