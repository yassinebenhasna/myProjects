
package DAO;

import METIER.Personne;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TraitPersonne {
    //********************************************* CNX BD *************************************************************************
    
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
    
//**************************************mthd Ajoutr des ensaingnat**********************************************************************************

        public String ajouterPerson(Personne p){
            // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;

            String msg = ""; 

                try {

                    prestat = (PreparedStatement) connexion.prepareStatement("INSERT INTO personne(nom,prenom,CIN,email,tel,password,ID_fct,ID_GRADE) VALUES (?,?,?,?,?,MD5(?),?,?);");
                    prestat.setString(1, p.getNom());
                    prestat.setString(2, p.getPrenom());
                    prestat.setString(3, p.getCin());            
                    prestat.setString(4, p.getEmail());
                    prestat.setString(5, p.getTel());
                    prestat.setString(6, p.getPassword());  
                    prestat.setLong(7, p.getId_fct());
                    prestat.setLong(8, p.getId_grade());
                    
                    prestat.executeUpdate();  

                    msg = "ajout succes";    

                } catch (SQLException ex) {
                    msg = "ajout failed";

                } finally {
                    // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
                    try {
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
        
    //**************************************mthd afficher la liste des ensaingnat**********************************************************************************
        
        public List<Personne> listPersonne(String mc){
            
            List<Personne> listPerso = new ArrayList<Personne>();
            
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            try {
                
                if(mc == null || mc.equals("") || mc.equals("allNames") ){


                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM personne  ");
                        
                        resultat = prestat.executeQuery();
                        
                        while(resultat.next()){
                        
                            Personne p = new Personne ();
                                p.setId_pers(resultat.getLong("ID_PERS"));
                                p.setNom(resultat.getString("nom"));
                                p.setPrenom(resultat.getString("prenom"));
                                p.setCin(resultat.getString("CIN"));
                                p.setEmail(resultat.getString("email"));
                                p.setTel(resultat.getString("tel"));
                                p.setId_fct(resultat.getLong("ID_fct"));
                                p.setId_grade(resultat.getLong("ID_GRADE"));
                                p.setId_etat(resultat.getLong("id_etat"));
                            listPerso.add(p);
                            
                        }

                }else{
                
                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM personne WHERE nom LIKE ? ");
                        prestat.setString(1, "%"+mc+"%");
                        resultat = prestat.executeQuery();
                        
                        while(resultat.next()){
                        
                            Personne p = new Personne ();
                                p.setId_pers(resultat.getLong("ID_PERS"));
                                p.setNom(resultat.getString("nom"));
                                p.setPrenom(resultat.getString("prenom"));
                                p.setCin(resultat.getString("CIN"));
                                p.setEmail(resultat.getString("email"));
                                p.setTel(resultat.getString("tel"));
                                p.setId_fct(resultat.getLong("ID_fct"));
                                p.setId_grade(resultat.getLong("ID_GRADE"));
                                p.setId_etat(resultat.getLong("id_etat"));
                            listPerso.add(p);
                            
                        }
                
                }
                
            }catch (SQLException ex) {
            
            }finally{
                try {   
                        if(resultat != null)
                            resultat.close();
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                        
                    }            
            }
            
        
        
        
        return listPerso;
        }
    //************************************** desactiver un compte **********************************************************************************
        
        public void desactiver(Long id){
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            try {
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE personne SET id_etat=2 WHERE ID_PERS=?");
                        prestat.setLong(1, id);
                        prestat.executeUpdate();
            
            }catch (SQLException ex) {
            
            }finally{
                try {   
                        
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                        
                    }            
            }
        }

//************************************** activer un compte **********************************************************************************
        
        public void activer(Long id){
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            try {
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE personne SET id_etat=1 WHERE ID_PERS=?");
                        prestat.setLong(1, id);
                        prestat.executeUpdate();
            
            }catch (SQLException ex) {
            
            }finally{
                try {   
                        if(resultat != null)
                            resultat.close();
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                        
                    }            
            }
        }

//************************************** modifier un compte **********************************************************************************
        
        public void modifier(Personne p){
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            try {
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE personne SET nom=?,prenom=?,CIN=?,email=?,tel=?,password=MD5(?) WHERE ID_PERS=?");
                        prestat.setString(1, p.getNom());
                        prestat.setString(2, p.getPrenom());
                        prestat.setString(3, p.getCin());
                        prestat.setString(4, p.getEmail());
                        prestat.setString(5, p.getTel());
                        prestat.setString(6, p.getPassword());
                        prestat.setLong(7, p.getId_pers());
                        prestat.executeUpdate();
            
            }catch (SQLException ex) {
            
            }finally{
                try {   
                        if(resultat != null)
                            resultat.close();
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                        
                    }            
            }
        }
        
}
