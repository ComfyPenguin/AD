package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joange
 */
public class Data {

    private String dia;
    private String mes;
    private String any;

    /**
     * Constructor de la data
     * @param dia Dia en particular
     * @param mes Mes en particular
     * @param any Any en particular
     */
    public Data(String dia, String mes, String any) {
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    /**
     * Contructor de la Data al dia actual
     */
    public Data() {
        Calendar c = new GregorianCalendar();
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        any = Integer.toString(c.get(Calendar.YEAR));
    }
    
    /**
     * 
     * @return El valor de la data com a text
     */
    
    @Override
    public String toString()
    {
            return dia + "/" + mes + "/"+any;
    }
    
    /**
     * Retorna la data
     * @return en un format que enten els SGBD (yyyy-mm-dd)
     */
    public String sql()
    {
            return any + "-" + mes + "-"+dia;
    }
    
    
    /**
     * Calcula els dies entre dos Dates
     * @param d1 la primera data
     * @param d2 la segona data
     * @return La diferència de dies des de d1 fins a d2
     */
    public static int diesEntre(Data d1, Data d2){
        long diff=0;
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date date1 = myFormat.parse(d1.toString());
            Date date2 = myFormat.parse(d2.toString());
            diff = date2.getTime() - date1.getTime();            
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Calcula la diferència de dies 
     * @param d Data per a calcular la diferència
     * @return els dies compresos entre la data que invoca i la data d
     */
    public int diesEntre(Data d){
        return diesEntre(d, this);
    }
    
    
    
    /**
     * Calcula els dies entre la Data d i el dia de hui
     * @param d la data en el passat
     * @return el numero de dies
     */
    public static int quantsDiesFa(Data d){
        Data ara=new Data();
        return diesEntre(d,ara);
    }
    
}   
