package DAO;

import METIER.Login;
import WEB.MODEL.LoginModel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TraiLogin {
                                    
                                                            private Connection connexion;

                                                            private void loadDatabase() {
                                                                // Chargement du driver
                                                                try {
                                                                    Class.forName("com.mysql.jdbc.Driver");
                                                                } catch (ClassNotFoundException e) {
                                                                }

                                                                try {
                                                                    connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet1", "root", "");

                                                                } catch (SQLException e) {
                                                                    e.printStackTrace();

                                                                }
                                                            }

    public String verif(Login userLog) {

       String msg = "";

        String user = (String) userLog.getUser();
        String pass = (String) userLog.getPass();

        //loadDatabase();
        loadDatabase();
        PreparedStatement prestat = null;
        ResultSet resultat = null;

        try {
            //stat = connexion.createStatement();

            // Exécution de la requête
            prestat = (PreparedStatement) connexion.prepareStatement("SELECT role,id_etat FROM personne WHERE nom=? AND password=MD5(?)");
            //prestat = (PreparedStatement) connexion.prepareStatement("SELECT role FROM personne WHERE nom=? AND password=MD5(?)");
            prestat.setString(1, user);
            prestat.setString(2, pass);

            resultat = prestat.executeQuery();

            if (resultat.next()) {
                String role = (String) resultat.getString("role");
                Long etat = resultat.getLong("id_etat");
                if(etat == 1){
                
                    if(role.equals("admin")){
                        msg = "admin";
                    }else{
                        msg = "user";
                    }
                }else{
                    msg = "Desolé! vous etes désactivé pour le moment";
                }
                
            } else {
                msg = "login ou pass incorrect";
            }

        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
            try {
                if (resultat != null) {
                    resultat.close();
                }
                if (prestat != null)
                    prestat.close();
                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ignore) {
            }
        }

        return msg;

    }
  
}
