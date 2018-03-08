package banco;

import java.io.*;

/**
 *
 * @author ALEJANDRO JIMÉNEZ MOLINA 1ºDAM
 */
public class LibroCuentas {

    private File f;
    private FileWriter fw;
    private FileReader fr;
    private BufferedReader bf;
    private BufferedWriter bw;

    public LibroCuentas() throws IOException {
        f = new File("LibroCuentas.txt");
        fw = new FileWriter(f, true);
        fr = new FileReader(f);
        bf = new BufferedReader(fr);
        bw = new BufferedWriter(fw);
    }

    public void escribirLibro(String cadena) throws IOException {
        bw.write(cadena);
        bw.newLine();
    }
    
    public void cerrarLibro() throws IOException{
        bw.close();
    }
    public void leerLibro() throws IOException {
        String linea;
        while ((linea = bf.readLine()) != null) {
              System.out.println(linea);
        }        
    }
}

