/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author yassine
 */
public class ArabeToASCII {
    
    public String covertAscii( String phrase ){
        
        String motEnAscii = "";
        
                phrase = phrase.trim();

                for(int i=0; i<phrase.length();i++){
                    if(phrase.charAt(i) != ' ')
                        
                        motEnAscii += "\\u0"+Integer.toHexString(phrase.charAt(i));
                    
                    else
                        
                        motEnAscii += " ";

                }

        motEnAscii += "\\";
        return motEnAscii; 
    }
    

    
    /*public static void main(String[] args){
        
        ArabeToASCII ar = new ArabeToASCII();
        
            System.out.println( ar.covertAscii("أَشْغَالُ ٱلْبَـيْـتِ") );
        
    }*/
    
}
