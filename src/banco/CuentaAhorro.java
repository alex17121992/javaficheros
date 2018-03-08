
package banco;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALEJANDRO JIMÉNEZ MOLINA
 * 1ºDAM
 */
public class CuentaAhorro extends Cuenta {
    private int meses;
    private double saldoAhorro;
    private boolean disponible;
    public CuentaAhorro(long num_cuenta, double saldo, String tit) throws IOException {
        super(num_cuenta, saldo, tit);
        meses=0;
        disponible=true;
        saldoAhorro=saldo;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public double getSaldoAhorro() {
        return saldoAhorro;
    }

    public void setSaldoAhorro(double saldoAhorro) {
        this.saldoAhorro = saldoAhorro;
    }
    
    public synchronized void interesMensual(){
        System.out.println("El saldo incrementara 0.25 por haber pasado un mes");
        saldoAhorro =saldoAhorro+(saldoAhorro*0.25);
        meses++;
    }
    
    public synchronized void retirarSaldo(double cantidad,String nombre){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        System.out.println("El cliente " + nombre + " esta retirando de la cuenta ahorro " + cantidad + " euros");
        disponible=false;
        saldoAhorro=saldoAhorro-cantidad;
        System.out.println("El saldo actual de la cuenta ahorro de "+nombre+" es " + saldoAhorro);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
        }
    
    public synchronized void consultarSaldo(String nombre){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        System.out.println("El cliente " + nombre + " esta consultando el saldo de la cuenta ahorro");
        disponible=false;
        System.out.println("El saldo actual de la cuenta ahorro de "+nombre+" es " + saldoAhorro);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
        
    } 
   
    public synchronized void cerrarCuenta(String nombre){
        System.out.println("El cliente " + nombre + " esta cerrando la cuenta ahorro");
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
        System.out.println("El cliente " + nombre + " esta ingresando en la cuenta ahorro " + cantidad + " euros");
        disponible=false;
        saldoAhorro=saldoAhorro+cantidad;
        System.out.println("El saldo actual de la cuenta ahorro de "+nombre+" es " + saldoAhorro);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
    }
    
    
}
