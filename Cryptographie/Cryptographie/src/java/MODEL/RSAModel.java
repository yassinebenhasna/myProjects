
package MODEL;

import java.util.ArrayList;
import java.util.List;


public class RSAModel {
    
    /****************************************attributs***********************************************************/
    char[] L = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    
    private String text;
    double code_crypte[] ;
    private String text_sortie = "";
    private long p;
    private long q;
    private long s;

    /****************************************fonctions***********************************************************/    
    

    long pgdc(long X,long Y)
    {
        long tmp;
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
    
    
    
    
    
        //Encryption  
    public String Encryption (RSAModel rsa ){
        
        String text = rsa.text.toUpperCase() ;
        long p = rsa.p ;
        long q = rsa.q ;
        long s = rsa.s ;
        
        int lenght_text = text.length();
        String code_txt[] = new String[lenght_text]; 
        
        for(int i=0; i<lenght_text; i++){
            
            for(int j=0; j<L.length; j++){
                
                if(text.charAt(i) == L[j])
                    code_txt[i] = String.valueOf(j);
            }
        }
        
        long n = p*q;
        long f = (p-1)*(q-1);
        
        //if(pgdc(s, f) == 1){
        
            List<String> code_txt1 = new ArrayList<String>() ;
            
            for(int i=0; i<code_txt.length; i++){
            
                if(code_txt[i].length() > 1){
                
                    for(int j=0; j<code_txt[i].length(); j++)
                        code_txt1.add(String.valueOf(code_txt[i].charAt(j)));i++;
                
                }else{
                    code_txt1.add(String.valueOf(code_txt[i]));
                }
            
            }
            
            
            
            List<Long> code_txt_divise = new ArrayList<Long>();            
            
            for( int o=0; o<code_txt1.size(); o++){
                if( code_txt1.get(o) != null ){
                long val = Long.parseLong(code_txt1.get(o));
                String va = code_txt1.get(o);
                int i = o+1, j = 0;
                long temp = val ;
                
                    while(val < n ){
                        temp = val;
                        va = va.concat(String.valueOf(code_txt1.get(i)));   
                        String test = "";
                        
                        for(int w=0; w<va.length(); w++){
                            if((va.charAt(w)=='0' ||va.charAt(w)=='1' ||va.charAt(w)=='2' ||va.charAt(w)=='3' ||va.charAt(w)=='4' ||va.charAt(w)=='5'
                                ||va.charAt(w)=='6' ||va.charAt(w)=='7' ||va.charAt(w)=='8' ||va.charAt(w)=='9'))
                                
                                    test = test + va.charAt(w);
                        }
                        
                        val = Long.parseLong(test);
                        i++;
                        o++;
                    }
                    
                    code_txt_divise.add(temp);j++;
                }
            }
            
            
            for(int i=0; i<code_txt_divise.size(); i++){
            
                code_crypte[i] = (Math.pow(code_txt_divise.get(i), s)) % n ;
                
                text_sortie = text_sortie + String.valueOf(code_crypte[i]) + " ";
                
            }
        //}
        
        
              
        
        
        return text_sortie;
   }

  

    /****************************************setters and getters***********************************************************/
  public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double[] getCode_crypte() {
        return code_crypte;
    }

    public void setCode_crypte(double[] code_crypte) {
        this.code_crypte = code_crypte;
    }

    public long getP() {
        return p;
    }

    public void setP(long p) {
        this.p = p;
    }

    public long getQ() {
        return q;
    }

    public void setQ(long q) {
        this.q = q;
    }

    public long getS() {
        return s;
    }

    public void setS(long s) {
        this.s = s;
    }

   

    public String getText_sortie() {
        return text_sortie;
    }

    public void setText_sortie(String text_sortie) {
        this.text_sortie = text_sortie;
    }
    
}
    
