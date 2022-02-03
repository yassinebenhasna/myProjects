package MODEL;

/**
 *
 * @author hp
 */
public class VigenereModel {

//************************************** attributs **********************************
    private String text;
    private String cle;

    private String text_sortie;

//************************************** fonctions **********************************
    int m = 26;
    int[] N = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    char[] L = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    int[] CodN(String mot) {//Cn() function
        int d = mot.length();
        int[] Num = new int[d];
        //etape 1 codification numerique
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < m; j++) {
                if (L[j] == mot.charAt(i)) {
                    Num[i] = N[j];
                }
            }
        }
        return Num;
    }

    String CodL(int[] mot, int dim) {//Cn^-1
        int d = dim;
        String Let = new String();
        //etape 1 codification numerique
        for (int i = 0; i < d; i++) {

            for (int j = 0; j < m; j++) {
                if (N[j] == mot[i]) {
                    Let = Let + L[j];
                }
            }
        }
        return Let;
    }
    //Fonction du Chiffement par sub vigenere

    public String CryptT(VigenereModel vig) {
        
        String Ptext = vig.text.toUpperCase();
        String Ktext = vig.cle.toUpperCase();
        
        int p = Ptext.length();
        int k = Ktext.length();
        
        int[] NP = new int[p];// tableau codes numerique du plaintext
        int[] NC = new int[p];// tableau code numérique  du cyphertext
        int[] NK = new int[k];// tableau code numérique  de  Secretekey

        //codage numérique 
        NP = CodN(Ptext);
        NK = CodN(Ktext);
        //chiffrement 
        for (int i = 0; i < p; i++) {
            NC[i] = (NP[i] + NK[i % k] ) % m;
        }
        //décodage numérique
        text_sortie = CodL(NC, p);
        return text_sortie;
    }
    //Fonction du Déchiffement par sub vigenere

    public String DecryptT(VigenereModel vig) {
        String Ptext = vig.text.toUpperCase();
        String Ktext = vig.cle.toUpperCase();
        
        int p = Ptext.length();
        int k = Ktext.length();
        
        int[] NP = new int[p];// tableau codes numerique du plaintext
        int[] NC = new int[p];// tableau code numérique  du cyphertext
        int[] NK = new int[k];// tableau code numérique  de  Secretekey

        //codage numérique 
        NP = CodN(Ptext);
        NK = CodN(Ktext);
        //chiffrement 
        for (int i = 0; i < p; i++) {
            if((NP[i] - NK[i % k]) >= 0)
                NC[i] = (NP[i] - NK[i % k] ) % m;
            
            else{                
                int pos = Math.abs((NP[i] - NK[i % k]) % m);
                 NC[i] = 26 - pos;              
            }
                
        }
        //décodage numérique
        text_sortie = CodL(NC, p);
        return text_sortie;
    }
    

    
    
//************************************** getters && setters **********************************

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getText_sortie() {
        return text_sortie;
    }

    public void setText_sortie(String text_sortie) {
        this.text_sortie = text_sortie;
    }

}
