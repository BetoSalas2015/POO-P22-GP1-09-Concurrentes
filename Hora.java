
/**
 * Write a description of class fqwerfqwe here.
 * /**
 * Objeto que representa la Abstracción del Objeto Hora.
 * 
 * @author Roberto Salazar Márquez
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;

public class Hora extends Frame implements Runnable
{
    // instance variables - Atributos de la Abstracción
    private int segundos;
    private Thread hilo;
    private TextField texto;

    /**
     * Constructor para objetos de la clase Hora sin Argumentos
     */

    public Hora()
    {
        // initialise instance variables
        setHoras(0);
        setMinutos(0);
        setSegundos(0);
    }
    
    /**
     * Constructor para objetos de la clase Hora recibiendo
     * horas.
     */

    public Hora(int h)
    {
        // initialise instance variables
        setHoras(h);
        setMinutos(0);
        setSegundos(0);
    }
    
    /**
     * Constructor para objetos de la clase Hora recibiendo
     * horas y minutos.
     */

    public Hora(int h, int m)
    {
        // initialise instance variables
        setHoras(h);
        setMinutos(m);
        setSegundos(0);
    }
    
    /**
     * Constructor para objetos de la clase Hora recibiendo
     * horas minutos y segundos.
     */

    public Hora(int h, int m, int s, String nombre)
    {
        // initialise instance variables
        super(nombre);  //  super es Frame
        setHoras(h);
        setMinutos(m);
        setSegundos(s);
        setLayout(new FlowLayout() );
        texto = new TextField(8);
        texto.setEditable(false);
        add(texto);
        addWindowListener(new CW());
        setSize(190,80);
        
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
    }
    
    private class CW extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
        }
    }
    
    /**
     * Constructor para objetos de la clase Hora a partir
     * de un objeto Hora existente
     */
    
    public Hora(Hora hr)
    {
        // initialise instance variables
        setHoras( hr.getHoras() );
        setMinutos( hr.getMinutos() );
        setSegundos( hr.getSegundos() );
    }
    
    
    public void run() {
        while(true) {
            tick();
            try {
                hilo.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            texto.setText( toString() );
        }
        
    }
    
    /**
     * Este método recibe una nueva hora para el objeto
     * La nueva hora deberá estar entre 0 y 23.
     * 
     * @param      h       Nueva hora para asignar
     * @return     Nada. 
     */
    public void setHoras(int h)
    {
        int seg;
        if(h >= 0 && h < 24) {
            seg = segundos / 3600;
            segundos -= seg * 3600;
            segundos += h * 3600;
        }
    }
    
    /**
     * Este método recibe un nuevo minuto para el objeto
     * El nuevo minuto deberá estar entre 0 y 59.
     * 
     * @param      m       Nuevo minuto para asignar
     * @return     Nada. 
     */
    public void setMinutos(int m)
    {
          int min;
          if(m >= 0 && m < 60) {
              min = ((segundos % 3600) / 60);
              segundos -= min * 60;
              segundos += m * 60;
          }
          else {
              min = ((segundos % 3600) / 60);
              segundos -= min * 60;
          }
    }
    
    /**
     * Este método recibe un nuevo minuto para el objeto
     * El nuevo minuto deberá estar entre 0 y 59.
     * 
     * @param      m       Nuevo minuto para asignar
     * @return     Nada. 
     */
    public void setSegundos(int s)
    {
        int seg;
        if(s >= 0 && s < 60) {
            seg = ((segundos % 3600) % 60);
            segundos -= seg;
            segundos += s;
        }
        else {
            seg = ((segundos % 3600) % 60);
            segundos -= seg;
    }
    }
    
    /**
     * Este método envía al exterior el valor del atributo Horas
     * 
     * @param      Ninguno
     * @return     Un int que representa las horas del objeto. 
     */
    public int getHoras()
    {
        return segundos / 3600;
    }
    
    /**
     * Este método envía al exterior el valor del atributo Minutos
     * 
     * @param      Ninguno
     * @return     Un int que representa los minutos del objeto. 
     */
    public int getMinutos()
    {
        return ((segundos % 3600) / 60);
    }
    
    /**
     * Este método envía al exterior el valor del atributo segundos
     * 
     * @param      Ninguno
     * @return     Un int que representa los segundos del objeto. 
     */
    public int getSegundos()
    {
        int i =((segundos % 3600) % 60);
        return i;
    }
    
    /**
     * Este método devuelve la hora actuan en forma de cadena
     * en formato AM-PM
     * 
     * @param      Ninguno
     * @return     Cadena con la hora en formato AM-PM. 
     */
    
    public String toString()
    {
        return ((getHoras() == 12  || getHoras() == 0) ? 12 : getHoras() % 12) +
                 ":" + (getMinutos() < 10 ? "0" : "") + getMinutos() +
                 ":" + (getSegundos() < 10 ? "0" : "") + getSegundos() +
                 (getHoras() < 12 ? " AM" : " PM");
    }
    
    /**
     * Este método devuelve la hora actuan en forma de cadena
     * en formato militar de 4 dígitos
     * 
     * @param      Ninguno
     * @return     Cadena con la hora en formato AM-PM. 
     */
    
    public String toMilitaryString()
    {
        return (getHoras() < 10 ? "0" : "") + getHoras() + (getMinutos() < 10 ? "0" : "") 
            + getMinutos() + " hrs";
    }
    
    /**
     * Este método incrementa en 1 los segundos actualizando la hora
     * correspondiente.
     * 
     * @param      Ninguno
     * @return     Nada 
     */
    
    public void tick() {
        segundos++;
        if(segundos == 86400) {
            segundos = 0;
        }
    }
    
    public static void main(String args[]) {

    }
    
}    
