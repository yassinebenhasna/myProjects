/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsecurity;

import java.awt.Color;
import java.math.BigInteger;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author yassine
 */
public class ProjetSecurity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Rsa j = new Rsa();
        j.getContentPane().setBackground(Color.DARK_GRAY);
        j.setVisible(true);
        /*
        BigInteger p = BigInteger.probablePrime(7, new Random());
        BigInteger q = BigInteger.probablePrime(7, new Random());
        
        BigInteger fi = p.subtract(BigInteger.valueOf(1)) . multiply( q.subtract(BigInteger.valueOf(1)) );
        
            System.out.println("projetsecurity.ProjetSecurity.main() =  " + fi.bitLength() );
            
        String x = "yassine";
        for(byte b : x.getBytes())
            System.out.println("byte = " + b);
        
        System.out.println("projetsecurity.ProjetSecurity.main() = " +  new String(x.getBytes()) );
        
        byte[] bytesMessage = x.getBytes();
        
        BigInteger[] bigchiffre= new BigInteger[bytesMessage.length];
        
        for(int i=0; i<bigchiffre.length; i++){
            
            byte[] elemsBytesMessage = { bytesMessage[i] };
            bigchiffre[i] = new BigInteger( elemsBytesMessage );   
            
        }
        
        for(BigInteger b : bigchiffre)
            System.out.println("big = " + b);
        */
    }
    
}
