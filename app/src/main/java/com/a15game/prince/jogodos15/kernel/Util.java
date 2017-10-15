package com.a15game.prince.jogodos15.kernel;


public class Util {

    public String segInStrTime(long seg){

        String retorno;

        if(seg > 60){//minuto
            if(seg > 3600){//hora
                int hr = Integer.parseInt(String.valueOf(seg)) / 3600;
                int min = Integer.parseInt(String.valueOf(seg)) - (3600 * hr) / 60;
                int intSeg = ((Integer.parseInt(String.valueOf(seg)) - (3600 * hr)) - (60 * min)) / 60;
                retorno = String.valueOf(hr)+" hr";
                if(min != 0){
                    retorno += " e "+String.valueOf(min)+" min";
                }
                if(intSeg != 0){
                    retorno += " e "+String.valueOf(intSeg)+" seg";
                }
            }else{
                int min = Integer.parseInt(String.valueOf(seg)) / 60;
                int intSeg = Integer.parseInt(String.valueOf(seg)) - (60 * min);
                retorno = String.valueOf(min)+" min";
                if(intSeg != 0){
                    retorno += " e "+String.valueOf(intSeg)+" seg";
                }
            }
        }else{
            retorno = String.valueOf(seg)+" seg";
        }



        return retorno;
    }

}
