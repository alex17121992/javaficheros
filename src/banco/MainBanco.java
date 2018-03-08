

package banco;

import java.io.IOException;

/**
 *
 * @author ALEJANDRO JIMÉNEZ MOLINA
 * 1ºDAM
 */
public class MainBanco {
    
    public static void main(String[] args) throws InterruptedException, IOException{
        
        Cliente cliUno = new Cliente("Alex",10000,123);
        Cliente cliDos = new Cliente("Pepe",10000,124);
        Cliente cliTres = new Cliente("Cristian",15000,124);
        Cliente cliCuatro = new Cliente("Antonio",20000,125);
        
        CuentaDeposito cu3=new CuentaDeposito(15420,8000,cliTres.getNombre());
        Cuenta cu=new Cuenta(123456,10000,cliUno.getNombre());
        Cuenta cu2=new Cuenta(15423,15000,cliTres.getNombre());
        CuentaAhorro cu4=new CuentaAhorro(20154,20000,cliCuatro.getNombre());
        cu.setTitular(cliDos.getNombre());
        cliUno.setCuenta(cu);
        cliDos.setCuenta(cu);
        cliTres.setCuenta(cu2);
        cliUno.setCuentaDos(cu2);
        cliDos.setCuentaDos(cu2);
        cliTres.setCuentaDos(cu);
        cliTres.setCuentaDepo(cu3);
        cliCuatro.setCuentaAh(cu4);
        
        cliUno.start();
        cliDos.start();
        cliTres.start();
        cliCuatro.start();
        cliUno.join();
        cliDos.join();
        cliTres.join();
        cliCuatro.join();
    }    
    
    
}
