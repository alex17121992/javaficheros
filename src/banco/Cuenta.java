
package banco;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALEJANDRO JIMÉNEZ MOLINA
 * 1ºDAM
 */
public class Cuenta extends Thread{
    private long num_cuenta;
    private double saldo;
    private ArrayList <String> titular=new ArrayList<>();
    boolean disponible=true;
    private LibroCuentas cartilla;
    
    public Cuenta(long num_cuenta,double saldo,String tit) throws IOException {
        this.num_cuenta = num_cuenta;
        this.saldo = saldo;
        titular.add(tit);
        cartilla=new LibroCuentas();
    }

    public LibroCuentas getCartilla() {
        return cartilla;
    }

    public void setCartilla(LibroCuentas cartilla) {
        this.cartilla = cartilla;
    }
    
    public String getTitular(int pos) {
        return titular.get(pos);
    }

    public void setTitular(String tit) {
        titular.add(tit);
    }

    public long getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(int num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void consultarSaldo(String nombre) throws IOException{
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        cartilla.escribirLibro("El cliente " + nombre + " esta consultando el saldo");
        System.out.println("El cliente " + nombre + " esta consultando el saldo");
        disponible=false;
        cartilla.escribirLibro("Saldo: " + getSaldo() + " " + " Titular: " + nombre);
        System.out.println("Saldo: " + getSaldo() + " " + " Titular: " + nombre);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
        
    } 
   
    public synchronized void cerrarCuenta(String nombre) throws IOException{
        cartilla.escribirLibro("El cliente " + nombre + " esta cerrando la cuenta");
        System.out.println("El cliente " + nombre + " esta cerrando la cuenta");
        disponible=true;
        notifyAll();
    }
    
    
    public synchronized void ingresarSaldo(double cantidad,String nombre) throws IOException{
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        cartilla.escribirLibro("El cliente " + nombre + " esta ingresando " + cantidad + " euros");
        System.out.println("El cliente " + nombre + " esta ingresando " + cantidad + " euros");
        disponible=false;
        saldo=saldo+cantidad;
        cartilla.escribirLibro("El saldo actual de "+ nombre+" es " + saldo);
        System.out.println("El saldo actual de "+ nombre+" es " + saldo);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
    }
    
    public synchronized void retirarSaldo(double cantidad,String nombre) throws IOException{
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        cartilla.escribirLibro("El cliente " + nombre + " esta retirando " + cantidad + " euros");
        System.out.println("El cliente " + nombre + " esta retirando " + cantidad + " euros");
        disponible=false;
        saldo=saldo-cantidad;
        cartilla.escribirLibro("El saldo actual de "+ nombre+" es " + saldo);
        System.out.println("El saldo actual de "+ nombre+" es " + saldo);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarCuenta(nombre);
    }
    
    public synchronized void realizarTransferencia(String nombre,double cantidad,Cuenta cu,Cuenta cu2) throws IOException{
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
        cartilla.escribirLibro("El cliente " + nombre + " esta realizando una tranferencia por el importe de " + cantidad + " euros");
        System.out.println("El cliente " + nombre + " esta realizando una tranferencia por el importe de " + cantidad + " euros");
        disponible=false;
        double sald=cu2.getSaldo();
        sald=sald+cantidad;
        cu2.setSaldo(sald);
        cartilla.escribirLibro("El saldo actual de la cuenta de la cuenta de es " + cu2.getSaldo());
        System.out.println("El saldo actual de la cuenta de la cuenta de es " + cu2.getSaldo());
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
        cerrarCuenta(nombre);
    }
    
    public void terminado(int i) throws IOException{
        if(i==10){
            cartilla.cerrarLibro();
            exit(1);
        }
    }
}
