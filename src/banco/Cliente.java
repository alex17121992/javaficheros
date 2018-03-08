
package banco;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALEJANDRO JIMÉNEZ MOLINA
 * 1ºDAM
 */
public class Cliente extends Thread{
    private int id_cliente;
    private Cuenta cuenta;
    private Cuenta cuentaDos;
    private String nombre;
    private double saldo;
    private Random rand;
    private CuentaDeposito cuentaDepo;
    private CuentaAhorro cuentaAh;
    
    public Cliente(String nombre,double saldo,int id_cliente){
        this.nombre=nombre;
        this.saldo=saldo;
        this.id_cliente=id_cliente;
        cuenta=null;
        cuentaDos=null;
        cuentaDepo=null;
        cuentaAh=null;
    }

    public CuentaAhorro getCuentaAh() {
        return cuentaAh;
    }

    public void setCuentaAh(CuentaAhorro cuentaAh) {
        this.cuentaAh = cuentaAh;
    }
    
    public CuentaDeposito getCuentaDepo() {
        return cuentaDepo;
    }

    public void setCuentaDepo(CuentaDeposito cuentaDepo) {
        this.cuentaDepo = cuentaDepo;
    }
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Cuenta getCuentaDos() {
        return cuentaDos;
    }

    public void setCuentaDos(Cuenta cuentaDos) {
        this.cuentaDos = cuentaDos;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void run(){
        int i=0;
        while(true || i <=10){
            System.out.println(Thread.currentThread());
            if(cuenta!=null){
                try {
                    cuenta.consultarSaldo(nombre);
                    cuenta.ingresarSaldo(1000,nombre);
                    cuenta.retirarSaldo(10,nombre);
                    cuenta.realizarTransferencia(nombre,saldo,getCuenta(),getCuentaDos());
                    cuenta.terminado(i);
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            if(cuentaDepo!=null){
                cuentaDepo.ingresarSaldo(5000, nombre);
                cuentaDepo.retirarSaldo(10, nombre);
                cuentaDepo.remuneracion();
            }
            if(cuentaAh!=null){
                cuentaAh.interesMensual();
                cuentaAh.ingresarSaldo(saldo, nombre);
                cuentaAh.retirarSaldo(saldo, nombre);
                
            }
            i++;
        }
    }
    
}
