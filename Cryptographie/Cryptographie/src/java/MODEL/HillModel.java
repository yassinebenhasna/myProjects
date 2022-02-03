/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.Random;

/**
 *
 * @author Minfo
 */
public class HillModel {

    //************************************** attributs **********************************
    private String text;
    private int a;
    private int b;
    private int c;
    private int d;
    

    private String text_sortie;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public String getText_sortie() {
        return text_sortie;
    }

    public void setText_sortie(String text_sortie) {
        this.text_sortie = text_sortie;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int[] getN() {
        return N;
    }

    public void setN(int[] N) {
        this.N = N;
    }

    public char[] getL() {
        return L;
    }

    public void setL(char[] L) {
        this.L = L;
    }

   

    
    
    
    
   //************************************** fonctions **********************************

    // calcul du plus grand diviseur commun
    int m=26;
    int[]N =new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    char [] L=new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    
    int pgdc(int X,int Y)
    {
        int tmp;
        try        
        {
            while (Y!=0) {
            tmp=X;
            X=Y;
            Y=tmp % Y;
                 }
            if (X==0) {System.out.println("Pas de PDGC pour les deux nombres");}
           // else {}
        }
        catch(Exception err){
			System.out.println("Erreur: \n"+err);
	}
      return X;
    }
   
    // calcul de l'inverse d'un nombre modulos ALGORITH EUCLID
    int iv_euclide (int n,int a){
       
        int k=0;
        int iv_a=0;
        int r=1;
       try{
            if (pgdc(n,a)==1) {
            while (r!=0){
                k++;
                r=((k*n)+1)%a;
            }
            if (a>0)
            {iv_a=((k*n)+1)/a;} else {iv_a=n+((k*n)+1)/a ;}
            //System.out.println("  k := "+k+".l'inverse de a := "+a+"modulo n:= "+n+" est inv_a:="+iv_a); 
             }
            else  { System.out.println(" le nombre a:= "+a+" n' a pas d'inverse"); return 0;}
            }
        catch(Exception err){
			System.out.println("Erreur: \n"+err);
            }
        return iv_a;
    }
   //return l'inverse d'un entier a modulo n
    int iv_premiers (int n, int a){
        
        int k=0;
        int iv_a=0;
        int r=1;
      try{
            if (pgdc(n,a)==1) {
            while (r!=0){
                k++;
                r=((k*n)+1)%a;
           
             //System.out.println("  k := "+k+ " le reste r:= "+r+".l'inverse de a := "+a+"modulo n:= "+n+" est inv_a:= "+iv_a); 
            }
            if (a>0)
            {iv_a=((k*n)+1)/a;} else {iv_a=n+((k*n)+1)/a ;}
            System.out.println(iv_a ); 
          }
         else  { System.out.println(" le nombre a:= "+a+" n' a pas d'inverse");}
       }
        
      catch(Exception err){
			System.out.println("Erreur: \n"+err);
	}
     return iv_a;
    }
    // return  le resultat de a modulo q
     int modul(int q,int a){
         
         int mod=0;
         try { 
                a %=q;
                if (a < 0) {mod=a+q;}  else  {mod=a;}
           }
          catch(Exception err){
			System.out.println("Erreur: \n"+err);
	  }  
        return mod;
     }
     
   int keygen(int n){
       int key=0;
       while(true){
         Random rand = new Random();
         key = rand.nextInt(n) ;
         //System.out.println("The secrete Key: \n"+key);
         if(pgdc(key,n)==1 & key>1) break;
        }
      // System.out.println("The secrete Key: \n"+key);
   return key;
   }
 int [] CodN(String mot){//Cn() function
       int d=mot.length();
       int []Num=new int[d];
       //etape 1 codification numerique
      for(int i=0;i<d;i++){
         mot.charAt(i);
         for(int j=0;j<m;j++){
             if(L[j]==mot.charAt(i)){Num[i]=N[j];}
         }
      }
      return Num;
   }
 String CodL(int [] mot, int dim){//Cn^-1
       int d=dim;
       String Let=new String();
       //etape 1 codification numerique
      for(int i=0;i<d;i++){
         
         for(int j=0;j<m;j++){
             if(N[j]==mot[i]){Let=Let+L[j];}
         }
      }
      return Let;
   }

    
   
  //Fonction du Chiffement par sub hill

    public String CryptT(HillModel hill) {

        String ptext = hill.text.toUpperCase();
        int len=ptext.length();//longueur du plaintext

        int a = hill.a;
        int b = hill.b;
        int c = hill.c;
        int d = hill.d;
        
        int det=(a*d-b*c);
        if(pgdc(det, m) == 1){
            
            int []NP=new int[len];// tableau codes numerique du plaintext
        
            //codage numérique 
            NP=CodN(ptext);
           int []NC=new int[len];// tableau code numérique  du cyphertext
           int x,y;// pour les code numerique du plaintext
           int u,v;// pour les code numerique du ciphetext
           for(int i=0;i<len;i=i+2){
               
               x=NP[i];  
                if(i == len-1){
                    y=0;
                    u=(x*a+y*b)%26;
                    NC[i]=u; 
                   
               }else{
                    y=NP[i+1];
                    u=(x*a+y*b)%26;
                    v= (x*c+y*d)%26;
                    NC[i]=u; NC[i+1]=v;
               }
               //System.out.println("ENCRYPTION ? code numerique du message"+NP[i]+ " et : "+NP[i+1]+ " message crypte "+NC[i]+" et "+NC[i+1] );
           }

           text_sortie=CodL(NC,len);
        }
        else 
            text_sortie = "(det est non premier avec 26)/(det est non inversible)";
        
     return text_sortie;
    }
    
    
    
    public String DecryptT(HillModel hill){
       //generation de la matrice en function 
               String ptext = hill.text.toUpperCase();

       int len=ptext.length();//longueur du plaintext

        int a = hill.a;
        int b = hill.b;
        int c = hill.c;
        int d = hill.d;

        
       int []NC=new int[len];// tableau codes numerique du cyphertext
       //codage numérique 
        NC=CodN(ptext);
       int []NP=new int[len];// tableau code numérique du plaintext 
       int x,y;// pour les code numerique du plaintext
       int u,v;// pour les code numerique du ciphetext
       // calcul determiant et la matrice inverse
      // a=9;b=4;c=5;d=7;
       int det=(a*d-b*c);
       if(pgdc(det, m) == 1){
            det=modul(26,det);

            int temp=a;a=d;d=temp;
            c=26-c; b=26-b;// mod 26
            int invdet=iv_euclide(m, det);

            a=(a*invdet)%26; b=(b*invdet)%26; c=(c*invdet)%26;d=(d*invdet)%26;

            for(int i=0;i<len;i=i+2){
                
                x=NC[i];  
                if(i == len-1){
                    y=0;
                    u=(x*a+y*b)%26;
                    NP[i]=u-2; 
                   
               }else{
                    y=NC[i+1];
                    u=(x*a+y*b)%26;
                    v= (x*c+y*d)%26;
                    NP[i]=u; NP[i+1]=v;
               }
                
                
                text_sortie =CodL(NP,len);            }
        }else 
            text_sortie = "(det est non premier avec 26)/(det est non inversible)";
       
     
     return text_sortie;
    }
}
