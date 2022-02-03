
package WEB.MODEL;

import METIER.Login;


public class LoginModel {
    
    // un model pour stocker les donnes saisis et les resultas affichees
    
            Login user = new Login();
            private String resultatCnx;
    
    
    
    
    
    
    
    
    
    
    
    
    

    /*private String user;
    private String pass;
    private String resultatCnx;

    public LoginModel( ){}
    
    public LoginModel(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    
    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getResultatCnx() {
        return resultatCnx;
    }

    public void setResultatCnx(String resultatCnx) {
        this.resultatCnx = resultatCnx;
    }
    */

    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
        this.user = user;
    }

    public String getResultatCnx() {
        return resultatCnx;
    }

    public void setResultatCnx(String resultatCnx) {
        this.resultatCnx = resultatCnx;
    }
    
}
