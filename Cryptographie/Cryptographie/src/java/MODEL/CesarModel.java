package MODEL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class CesarModel {

//************************************** attributs **********************************
    private String text;
    private int cle;

    private String text_sortie;
    private List<String> list_text_sortie = new ArrayList<>();

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
    //Fonction du Chiffement par translation ou de Cesar

    public String CryptT(CesarModel cesarmodel) {
        String ptext = cesarmodel.text.replaceAll("\\s", "").toUpperCase();
        int key = cesarmodel.cle;
        
        int d = ptext.length();
        int[] NT = new int[d];
        int[] NC = new int[d];

        //codage numérique 
        NT = CodN(ptext);
        //chiffrement 
        for (int i = 0; i < d; i++) {
            NC[i] = (key + NT[i]) % m;
            if (NC[i] == 0) {
                NC[i] = m;
            }
        }
        //décodage numérique
        text_sortie = CodL(NC, d);
        return text_sortie;
    }
    //Fonction du Déchiffement par translation ou de Cesar

    public String DecryptT(CesarModel cesarmodel) {
        
        String ctext = cesarmodel.text.toUpperCase();
        int key = cesarmodel.cle;
        
        int d = ctext.length();
        int[] NT = new int[d];
        int[] NC = new int[d];

        //codage numérique 
        NC = CodN(ctext);
        //Dechiffrement 
        for (int i = 0; i < d; i++) {
            NT[i] = (NC[i] + m - key) % m;
        }
        //décodage numérique
        text_sortie = CodL(NT, d);
        return text_sortie;
    }
    
    // attaque force brute
    
    public List<String> attaque(CesarModel cesarmodel){
    
        String ctext = cesarmodel.text.toUpperCase();
        int key;
        
        int d = ctext.length();
        int[] NT = new int[d];
        int[] NC = new int[d];

        //codage numérique 
        NC = CodN(ctext);
        //Dechiffrement 
        for(key=1; key<26; key++){
            
            for (int i = 0; i < d; i++) {
                NT[i] = (NC[i] + m - key) % m;
            }
            //décodage numérique
            text_sortie = (String) CodL(NT, d);
            list_text_sortie.add(text_sortie);
        }   
        return list_text_sortie;
    }

//************************************** getters && setters **********************************

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }
 

    public String getText_sortie() {
        return text_sortie;
    }

    public void setText_sortie(String text_sortie) {
        this.text_sortie = text_sortie;
    }

    public List<String> getList_text_sortie() {
        return list_text_sortie;
    }

    public void setList_text_sortie(List<String> list_text_sortie) {
        this.list_text_sortie = list_text_sortie;
    }

}
