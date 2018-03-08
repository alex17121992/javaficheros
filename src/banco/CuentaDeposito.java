package banco;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALEJANDRO JIMÉNEZ MOLINA
 * 1ºDAM
 */
public class CuentaDeposito extends Cuenta{
    private int meses;
    private double saldoDes;
    private boolean disponible;
    
    public CuentaDeposito(long num_cuenta, double saldo,String tit) throws IOException{
        super(num_cuenta, saldo,tit);
        saldoDes=saldo;
        disponible=true;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public double getSaldoDes() {
        return saldoDes;
    }

    public void setSaldoDes(double saldoDes) {
        this.saldoDes = saldoDes;
    }
       
    public synchronized void retirarSaldo(double cantidad,String nombre){
        if(meses >12){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        System.out.println("El cliente " + nombre + " esta retirando de la cuenta deposito " + cantidad + " euros");
        disponible=false;
        saldoDes=saldoDes-cantidad;
        System.out.println("El saldo actual de la cuenta deposito de "+nombre+" es " + saldoDes);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
        }
        else
            System.out.println("No han pasado 12 meses.No puedes retirarlo.Quedan " + (12-meses) + " meses");
            meses++;
    }
    
    public synchronized void consultarSaldo(String nombre){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        System.out.println("El cliente " + nombre + " esta consultando el saldo de la cuenta deposito");
        disponible=false;
        System.out.println("El saldo actual de la cuenta deposito de "+nombre+" es " + saldoDes);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
        
    } 
   
    public synchronized void cerrarCuenta(String nombre){
        System.out.println("El cliente " + nombre + " esta cerrando la cuenta deposito");
        disponible=true;
        notifyAll();
    }
    
    public synchronized void ingresarSaldo(double cantidad,String nombre){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        System.out.println("El cliente " + nombre + " esta ingresando en la cuenta deposito " + cantidad + " euros");
        disponible=false;
        saldoDes=saldoDes+cantidad;
        System.out.println("El saldo actual de la cuenta deposito de "+nombre+" es " + saldoDes);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
    }
    
    public void remuneracion(){
        if(meses>=12){
            saldoDes=getSaldoDes();
            saldoDes= saldoDes+(saldoDes*0.01);
        }
    }
}
