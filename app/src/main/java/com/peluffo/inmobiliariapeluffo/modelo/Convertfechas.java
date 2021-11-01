package com.peluffo.inmobiliariapeluffo.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convertfechas {

    public Convertfechas() {
    }

    public String convertFecha(String fecha){
        String dia ="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try{
            Date d = dateFormat.parse(fecha);
            dia = formato.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }
}
