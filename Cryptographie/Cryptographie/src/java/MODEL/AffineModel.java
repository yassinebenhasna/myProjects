package MODEL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class AffineModel {

//************************************** attributs **********************************
    private String text;
    private int a;
    private int cle;

    private String text_sortie;
    private List<String> list_sortie = new ArrayList<>();

//************************************** fonctions **********************************
    int m = 26;
    int[] N = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    char[] L = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public int pgdc(int X, int Y) {
        int tmp;

        while (Y != 0) {
            tmp = X;
            X = Y;
            Y = tmp % Y;
        }

        return X;
    }

    //return l'inverse d'un entier a modulo n
    public int iv_premiers(int n, int a) {

        int k = 0;
        int iv_a = 0;
        int r = 1;

        if (pgdc(n, a) == 1) {
            while (r != 0) {
                k++;
                r = ((k * n) + 1) % a;
            }
            if (a > 0) {
                iv_a = ((k * n) + 1) / a;
            } else {
                iv_a = n + ((k * n) + 1) / a;
            }
        } else {
            // System.out.println(" le nombre a:= " + a + " n' a pas d'inverse");
        }

        return iv_a;
    }

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
    //Fonction du Chiffement par sub affine

    public String CryptT(AffineModel affine) {

        String ptext = affine.text.toUpperCase();
        int a = affine.a;
        int key = affine.cle;

        int d = ptext.length();
        int[] NT = new int[d];
        int[] NC = new int[d];

        //codage numérique 
        NT = CodN(ptext);
        //chiffrement 
        for (int i = 0; i < d; i++) {
            NC[i] = (a * NT[i] + key) % m;
            if (NC[i] == 0) {
                NC[i] = m;
            }
        }
        //décodage numérique
        text_sortie = CodL(NC, d);
        return text_sortie;
    }
    //Fonction du Déchiffement par sub multiplication

    public String DecryptT(AffineModel affine) {

        String ctext = affine.text.toUpperCase();
        int a = affine.a;
        int key = affine.cle;

        int inv_a = iv_premiers(m, a);

        int d = ctext.length();
        int[] NT = new int[d];
        int[] NC = new int[d];

        //codage numérique 
        NT = CodN(ctext);
        //Dechiffrement 
        for (int i = 0; i < d; i++) {
            NC[i] = ( inv_a * (NT[i] - key + m) ) % m;
        }
        //décodage numérique
        text_sortie = CodL(NC, d);
        return text_sortie;
    }
    
        public List<String> attaquer(AffineModel affine) {

        String ctext = affine.text.toUpperCase();
        int a = affine.a;
        
        int key;

        int inv_a = iv_premiers(m, a);

        int d = ctext.length();
        int[] NT = new int[d];
        int[] NC = new int[d];

            //codage numérique 
        for(key=1; key<26; key++){
            NT = CodN(ctext);
            //Dechiffrement 
            for (int i = 0; i < d; i++) {
                NC[i] = ( inv_a * (NT[i] - key + m) ) % m;
            }
            //décodage numérique
            text_sortie = CodL(NC, d);
            list_sortie.add(text_sortie);
        }
        return list_sortie;
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public List<String> getList_sortie() {
        return list_sortie;
    }

    public void setList_sortie(List<String> list_sortie) {
        this.list_sortie = list_sortie;
    }

}
