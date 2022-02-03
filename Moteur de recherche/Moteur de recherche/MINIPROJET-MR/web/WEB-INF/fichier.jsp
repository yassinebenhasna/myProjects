<%-- 
    Document   : fichier
    Created on : 30 mai 2020, 03:33:26
    Author     : yassine
--%>

<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        

        
        <%
            
            String nomFichier = (String)request.getAttribute("nomFichier"); 
            BufferedReader nomFichier1 = new BufferedReader(new InputStreamReader( new ByteArrayInputStream( nomFichier.getBytes() ) , "UTF8" ) );
            nomFichier = nomFichier1.readLine();
            out.print("haaaaaaaa lmachakil 3awd : " + nomFichier + "ffffffffffffffffff       ");
            try {

                String webPath = getServletContext().getRealPath("/");
                String fichierPath = webPath + "..\\..\\web\\fichiers\\CorpusMR\\" + nomFichier ;



                BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( fichierPath ),"UTF8" ) );

                //BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( webPath + "..\\..\\web\\fichiers\\CorpusMR\\" + "1\\كتابي في اللغة العربية\\أحب قريتي.txt" ),"UTF8" ) );
                //BufferedReader br = new BufferedReader( new FileReader( webPath + "..\\..\\web\\fichiers\\CorpusMR\\" + "1\\كتابي في اللغة العربية\\أحب قريتي.txt") );
                                
                            
                            String line = "";
                            while( (line = br.readLine() ) != null ){
                            
                                out.print(line);
                                %><br><br><%
                            }
                                
                br.close();
            } catch (IOException ex) {
                out.print("--------------- erreur lors de la lecture des fichiers: ------------------------------" + ex );
            }
        
        
        %>
        
        
        
    </body>
</html>
