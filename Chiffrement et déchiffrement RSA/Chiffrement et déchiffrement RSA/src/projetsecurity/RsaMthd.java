/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsecurity;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author yassine
 */
public class RsaMthd {
    
    BigInteger p, q, n, fi, e, d;
   
//--------------------------------------------------------- generer les parametres de RSA -------------------------------------------------------------------    
    public void genererParams(String type, BigInteger pDonne, BigInteger qDonne ){

        if( type.equals("gen") ){
            p = BigInteger.probablePrime(7,new Random());
            q = BigInteger.probablePrime(7,new Random());
        }else{
            p = pDonne;
            q = qDonne;
        }
        n = p.multiply(q);
        fi = p.subtract(BigInteger.valueOf(1)) . multiply( q.subtract(BigInteger.valueOf(1)) );

        do
        {
            e = new BigInteger(14 , new Random());
            
        }
        while ( 
                e.compareTo( BigInteger.valueOf(1) ) == -1 || e.compareTo( BigInteger.valueOf(1) ) == 0    // e <= 1
              ||
                e.compareTo( fi ) == 1 || e.compareTo( fi ) == 0                                           // e >= fi
              ||
                e.gcd( fi ).compareTo( BigInteger.valueOf(1) ) != 0                                        // pgcd(e,fi) != 1
            ); 
        
        d = e.modInverse( fi );
    }

//--------------------------------------------------------- crypter -------------------------------------------------------------------        
    public String crypter(String message, BigInteger e, BigInteger n){
                
        //get bytes of message
        byte[] bytesMessage = message.getBytes(); //exemlpe: 147 152 30 69 88 
        
        //transformer les bytes en bigInteger
        BigInteger[] bytesMsgToBigIntgr = new BigInteger[bytesMessage.length];                 
        for(int i=0; i<bytesMsgToBigIntgr.length; i++){
            
            byte[] elemsBytesMessage = { bytesMessage[i] };
            bytesMsgToBigIntgr[i] = new BigInteger( elemsBytesMessage );   
            
        }// 147 152 30 69 88 
        
        
        //crypter les bigInteger du message qui se trouvent dans bytesMsgToBigIntgr
        BigInteger[] bytesMsgToBigIntgrCypted= new BigInteger[bytesMsgToBigIntgr.length];
        String messageCrypted = "";
        
        for(int i = 0; i < bytesMsgToBigIntgr.length; i++){
             
            messageCrypted += String.valueOf( bytesMsgToBigIntgr[i].modPow(e, n) ) + " ";
             
        }// 178 012 388 66879 6994 
                
        return messageCrypted;
         
    }
    
    
//--------------------------------------------------------- decrypter -------------------------------------------------------------------            
    public String decrypter(String messageCrypted, BigInteger d, BigInteger n, String type){
        
        String msgDecrypted = "";
        
        if( type .equals("local") ){
            //get les bigIntegers
            String bigIntgCrypted[] = messageCrypted.split(" ");// 178 012 388 66879 6994 

            //decrypter les bigIntegers
            BigInteger[] bigIntgDecrypted = new BigInteger [bigIntgCrypted.length];           
            for( int i=0;i<bigIntgCrypted.length;i++){
                bigIntgDecrypted[i]= new BigInteger( String.valueOf( bigIntgCrypted[i] ) ).modPow(d, n);
            }// 147 152 30 69 88

            //transformer les bigIntegers en bytes
            byte[] bytes = new byte[ bigIntgDecrypted.length ];        
            for( int i=0;i<bytes.length;i++){
                bytes[i] = (byte) (bigIntgDecrypted[i].intValue());
            }// 147 152 30 69 88

            //recuperer le message from bytes
            msgDecrypted = new String( bytes );
            
        }else{
            
            BigInteger messageCrypted2 = new BigInteger(messageCrypted.replaceAll("\\s+", "").trim());
            msgDecrypted = String.valueOf( messageCrypted2.modPow(d, n) );
        }
        
        return msgDecrypted;
        
    }

//--------------------------------------------------------- GETTERS -------------------------------------------------------------------            
    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getFi() {
        return fi;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
 
}