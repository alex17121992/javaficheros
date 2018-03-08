package ficheros;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alejandro Jiménez Molina
 * 1ºDAM
 * 
 */
public class OrdenarCiudades {
    public static void main(String[] args){
        Scanner consola=new Scanner(System.in);
        File f=new File("Ciudades.txt");
        FileWriter fw = null;
        FileReader fr = null;
        try {
            fw=new FileWriter(f);
            fr=new FileReader(f);
        BufferedWriter bw=new BufferedWriter(fw);
        BufferedReader br=new BufferedReader(fr);
        int numCiudades=0;
        
        System.out.println("Introduce el numero de ciudades que vas a introducir:");
        numCiudades=consola.nextInt();
        String ciudades[]=new String[numCiudades];
        
        for(int i=0;i<numCiudades;i++){
            ciudades[i]=consola.nextLine();
        }
        
        for(int i=0;i<ciudades.length;i++){
            for(int j=0;j<ciudades.length-1;j++){
                if(ciudades[j].length() == ciudades[j+1].length()){
                    char letraUno=ciudades[j].charAt(0);
                    char letraDos=ciudades[j+1].charAt(0);
                    if(letraUno >letraDos){
                        String aux = ciudades[j];
                        ciudades[j] = ciudades[j+1];
                        ciudades[j+1] = aux;
                    }
                }
                if(ciudades[j].length() > ciudades[j+1].length()){
                    String aux = ciudades[j];
                    ciudades[j] = ciudades[j+1];
                    ciudades[j+1] = aux;
		}
                
            }
        }
        
        for(int i=0;i<ciudades.length;i++){
            bw.write(ciudades[i]);
            bw.newLine();
        }
        bw.close();
        
        String linea;
        while((linea=br.readLine())!=null){
            System.out.println(linea);
        }
        
        } catch (IOException ex) {
            ex.getMessage();
        }
        
    }
}
