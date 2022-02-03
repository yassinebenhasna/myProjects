<%-- 
    Document   : Affichage
    Created on : 5 mai 2020, 17:40:02
    Author     : yassine
--%>

<%@page import="MODEL.RechercheModel"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.Reader"%>
<%@page import="service.ArabeToASCII"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROJET-MR</title>
        
        <style type="text/css">
            

            footer {
                clear: both;
                width:100%;
                text-align: center;
            }
            
        </style>
        
    </head>
    <body>
        
        
<!-- ****************************************************la moitié a guache **********************************************************************************-->  

    <div id="maDivGauche" style="float:left ; width: 60%">       
        
         <form method="POST" name="chercher" action="index.html">

            <div style="padding-top: 30px">                

                <div>
                    <h3>
                        
                        <a href="index.html?page=index" style="color: red;padding-right: 50px;padding-left: 20px">محرك البحث</a>
                        
                            <input style="width:600px; height:35px;" type="text" id="site-search" name="champ_search" placeholder="أدخل كلمات البحث هنا ...." dir="rtl">
                            <input type="submit" name="btn_chercher" value="ابحث">
                        
                    </h3>
                </div>
            </div>

         </form>        
    
            <div style="padding-top: 30px;padding-left: 150px">

                <% 
                    String totalResult = (String) request.getAttribute("totalResult");
                    
                    String requete = (String) request.getAttribute("requete");
                    
                    
                    Map<Integer , Map< String , String > > result = (Map<Integer , Map< String , String > >) request.getAttribute("result");

                %> 
                <h4 style="font-size: large"><U><%out.println( " Résultats pour : \"" + requete + "\"" );%></U></h4>
                <h5 style="color: #70757a"><%out.println( "Environ " + totalResult + " résultats" );%></h5>
                <br>
                <%    
                    for( int nbrResultat : result.keySet() ){
                        
                        String niveau = result.get(nbrResultat).get("niveau");
                        String dossier = result.get(nbrResultat).get("dossier");
                        String titre = result.get(nbrResultat).get("titre");
                        /*
                        String webPath = getServletContext().getRealPath("/");
                        String fichierPath = webPath + "..\\..\\web\\fichiers\\" ;
                        
                        BufferedWriter bfw = new BufferedWriter( new FileWriter( fichierPath + "resultat.txt" ) );
                        
                                bfw.write(niveau + "\n" );
                                bfw.write(dossier + "\n" );
                                bfw.write(titre + "\n" );
                         
                                bfw.close();*/
                         
                        BufferedReader niveau1 = new BufferedReader(new InputStreamReader( new ByteArrayInputStream(  niveau.getBytes() ) , "UTF8" ) );
                        BufferedReader dossier1 = new BufferedReader(new InputStreamReader( new ByteArrayInputStream( dossier.getBytes() ) , "UTF8" ));
                        BufferedReader titre1 = new BufferedReader( new InputStreamReader( new ByteArrayInputStream( titre.getBytes() ) , "UTF8" ) );
                        
                        niveau = niveau1.readLine();niveau1.close();
                        dossier = dossier1.readLine();dossier1.close();
                        titre = titre1.readLine();titre1.close();


                        %><h3><a href="AffichageCntrl?page=<%=niveau + "\\" + dossier + "\\" + titre + ".txt" %> "><% out.println( titre ); %></a></h3>
                        
                        <p>    
                            <span style="color: limegreen;padding-left: 20px"><U><% out.println( "INFOS SUPP : " ); %></u></span>
                        
                            <% out.println( "Ce fichier ce trouve dans le ");%>

                            <U><b>NIVEAU :</b></U><span style="color: coral"> <% out.println( niveau ); %></span>
                            
                            <% out.println( " et dans le " );%>

                            <U><b>DOSSIER :</b></U> <span style="color: coral"><% out.println( dossier + " " ); %></span>
                            
                         </p>
                        
                        <br/>

                <%
                    }


/*
    String encodedWithISO88591 = (String)request.getAttribute("test");
    byte[] x = encodedWithISO88591.getBytes();
    byte[] utf8 = new String( x , "ISO-8859-1").getBytes("UTF-8");

    out.println( "les resultats : " + new String( encodedWithISO88591.getBytes("ISO-8859-1"), "UTF-8")  );

    out.println( "les resultats : " + encodedWithISO88591.getBytes("UTF-8")  );

    out.println( "les resultats : " + utf8  );
*/

                %>
                <div style="text-align: center;padding-bottom: 50px;padding-top: 30px; font-size: 20px">
                <%
                
                int nbrPage = Integer.parseInt( totalResult ) / 10;
                
                if( nbrPage >= 50  ){
                    %><a href="index.html?page=index" style="color: red;">محرك البحث</a><br><br><%
                    for(int i=1 ; i<6; i++){
                %>
                        <a href="index.html?page=<%=i%>&requete=<%=requete%>" style="padding-right: 10px"><%=i%></a><%
                    }
                }else if( nbrPage >= 40 && nbrPage <50 ){
                    %><a href="index.html?page=index" style="color: red;">محرك البحث</a><br><br><%
                    for(int i=1 ; i<5; i++){
                    %><a href="index.html?page=<%=i%>&requete=<%=requete%>" style="padding-right: 10px"><%=i%></a><%
                    }
                }else if( nbrPage >= 30 && nbrPage <40 ){
                    %><a href="index.html?page=index" style="color: red;">محرك البحث</a><br><br><%
                    for(int i=1 ; i<4; i++){
                        %><a href="index.html?page=<%=i%>&requete=<%=requete%>" style="padding-right: 10px"><%=i%></a><%
                    }
                }else if( nbrPage >= 20 && nbrPage <30 ){
                    %><a href="index.html?page=index" style="color: red;">محرك البحث</a><br><br><%
                    for(int i=1 ; i<3; i++){
                        %><a href="index.html?page=<%=i%>&requete=<%=requete%>" style="padding-right: 10px"><%=i%></a><%
                    }
                }else if( nbrPage >= 10 && nbrPage <20 ){
                    %><a href="index.html?page=index" style="color: red;">محرك البحث</a><br><br><%
                    for(int i=1 ; i<2; i++){
                        %><a href="index.html?page=<%=i%>&requete=<%=requete%>" style="padding-right: 10px"><%=i%></a><%
                    }
                }else if( nbrPage < 10 ){

                    
                }
                
                %>
                </div>

                
            </div>
       
    </div>
    
<!-- ****************************************************la moitié a droite **********************************************************************************-->  

    <div id="maDivDroite" style="float:right;clear:right;">    
        
        <div style="padding-top: 10px;padding-right: 70px" > 
            <%
                    String webPath = getServletContext().getRealPath("/");
                    String logoPath = webPath + "..\\..\\web\\fichiers\\logoFSO.jpg";
            %>

            <a href="index.html?page=index" ><img src="images/logoFSO.png" alt="" style=" height :190px ; width :400px;" /></a>
        
        </div>
            
        <div style="padding-top: 50px;padding-right: 70px;padding-left: 60px" >                        
            
            <img class="lu-fs" height="160" id="lu_map" 
                 src="https://www.google.com/maps/vt/data=8vGHKDFZBUYcs6cieP00ljavJoNAeLSPtdai87FoxUQUgMEHyfXvk3TyjcZWtejUmO8TWoA5JM5vRP_R66ME83iLL6kPdLbwjv6SiqwWme3UAxLynM2KXZ9C95jxo_7U0e1oJpNVXAb83Nk0Z1ZUI2o7FBkRL16N90s9y0f-2LQvl5zUelWQNWsjBIPjaA&amp;w=278&amp;h=160" width="278" title="La porte de FSO principale&nbsp;: carte" alt="La porte de FSO principale&nbsp;: carte" border="0" data-atf="1" style="display: block;">
            <h3>La porte de FSO principale</h3>
            <br>
            <span>Faculté des sciences à Oujda</span><br/><br>
            <span><b>Adresse :</b> Oujda</span><br>
            <span><b>Horaires :</b></span><br>
            <div style="padding-left: 60px">
                <span>  vendredi &nbsp;&nbsp; &nbsp;08:00–19:00<br>
                        samedi &nbsp;&nbsp; &nbsp;08:00–19:00<br>
                        dimanche &nbsp;&nbsp; &nbsp;Fermé      <br>
                        lundi &nbsp;&nbsp; &nbsp;08:00–19:00<br>
                        mardi &nbsp;&nbsp; &nbsp;08:00–19:00<br>
                        mercredi &nbsp;&nbsp; &nbsp;08:00–19:00<br>
                        jeudi &nbsp;&nbsp; &nbsp;08:00–19:00<br>
                </span><br><br>
            </div>
            <span style="color: palevioletred">Les horaires ou les services proposés peuvent varier</span><br><br>
            <span><b>Téléphone :</b> 05365-00601</span><br>

            
            
            
             
            



          <!--<div style="position:absolute;bottom:0;width:100%;padding-top:50px;height:10px;text-align: center">-->  
                        
        </div>
    
    </div>

           
            <footer >
        
                    <p style="background: antiquewhite">
                        <br>
                        <b><U>Travail réalisé par :</U> BENHASNA YASSINE & KACHOUTI MOHAMED <br> <U>MASTER :</U> M2I <br>  © 2019-2020 </b>
                    </p>

              </footer>

            
    </body>
        
</html>
