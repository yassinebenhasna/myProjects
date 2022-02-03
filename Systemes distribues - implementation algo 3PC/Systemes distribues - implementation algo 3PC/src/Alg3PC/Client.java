/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alg3PC;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author yassine
 */
public class Client {
    
    public static void main(String[] args) {
    
        try {                        
            
            int choix = 0;
            do{
                    //1) cnx au serveur Principal 
                        Socket ServPrincpal = new Socket("localhost" , 1234);
                            System.out.println("***** la cnx au serveur est réussite *****");

                    //2)charger les entrees sorties
                            //entrees
                                InputStream is = ServPrincpal.getInputStream();
                                    InputStreamReader isr = new InputStreamReader(is);
                                    BufferedReader bfr = new BufferedReader(isr);

                            //sorties
                                OutputStream os = ServPrincpal.getOutputStream();
                                    PrintWriter pwr = new PrintWriter(os , true);
                                    
                    //GETSION DES ERREUS : choisir un scenario pour bien intéragir avec l'app
                        Scanner clavier = new Scanner(System.in);
                        
                            //coordinateur
                            System.out.println("\n***** choisir un scénario pour bien intéragir avec l'app : ******");
                            System.out.println("\n\t\t\t-------->coté coordinateur :");
                            System.out.println("\t\t\t\t\t1)tous les serveurs backup répondent <oui>.");
                            System.out.println("\t\t\t\t\t2)un serveur backup répond <non>.");
                            System.out.println("\t\t\t\t\t3)un serveur backup est planté.");
                            System.out.println("\t\t\t\t\t4)un serveur backup n'envoie pas son ACK.");
                            
                            //processus 
                            System.out.println("\n\t\t\t-------->coté processus(serveurs backup) :");
                            System.out.println("\t\t\t\t\t5)le coordinateur n'envoie pas la décision globale.");
                            System.out.println("\t\t\t\t\t6)le coordinateur n'envoie pas la validation.");
                            
                            System.out.print("\n\t\t\t-------->Entrez votre choix :");
                            int choixScenario = clavier.nextInt();
                    
                        System.out.println("********************************************************************");
                        
                    //3)attendre un msg de la part du serveurPrincipale pour nous a dit combien du montant actuel restant
                        String montantActuel = bfr.readLine();
                            System.out.print("\n\n====> votre montant actuel = " + montantActuel + "(DH)");

                    //4)faire une operation                                       
                        System.out.print("\n\nvoulez-vous ajouter ou retirer un montant ? ");
                        System.out.println("\n\n\t\t\t  1)ajouter \t\t 2) retirer ");
                            clavier.nextLine();
                            String typeOperation = clavier.nextLine();

                        String montantSaisit;
                        //ajouter
                        if(typeOperation.equals("1")){    
                            System.out.print("\n\nsasissez le montant que vous voulez ajouter SVP : ");
                                montantSaisit = clavier.nextLine();
                        }
                        //retirer
                        else{
                            System.out.print("\n\nsasissez le montant que vous voulez retirer SVP : ");
                                montantSaisit = clavier.nextLine();
                        }                                   

                    //5)envoyer ces infos au serveur principal pour le traitemant                          
                        pwr.println(typeOperation + "," + montantSaisit + "," + String.valueOf( choixScenario ));

                    //6)attendre la reponse du serveur
                        System.out.println("\n\n\t\t\t------------------ traitement de votre demande en cours ATT SVP... ------------------");
                            String reponse = bfr.readLine();

                    //7)affichage de l'état de l'operation
                        System.out.println("\n\n===============> " + reponse);
                    System.out.print("\n---------------------------------------------- TERMINE --------------------------------------------------------------");
                    
                    System.out.println("\n\nvoulez-vous faire une autre operation ? ");
                    System.out.println("1.oui");
                    System.out.println("2.non");
                    
                    choix = clavier.nextInt();
                    
            }while(choix == 1);
            
            System.out.print("\n\nBye ! ");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
