<%-- 
    Document   : test
    Created on : 20 janv. 2021, 15:19:02
    Author     : yassine
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.util.List"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>


        
        <%
            String path = (String) request.getAttribute("path");
            
            BufferedReader bfr = new BufferedReader( new InputStreamReader( new FileInputStream( path + "outputXmlFiles/results.xml" ),"UTF8" ) );
            //BufferedReader bfr = new BufferedReader( new FileReader( path + "outputXmlFiles/results.xml" ) );
            //BufferedReader bfr = new BufferedReader( new InputStreamReader( new DataInputStream( new FileInputStream( getSrcPath() + "files/" + fileName )) ) );
            //BufferedReader nomFichier1 = new BufferedReader(new InputStreamReader( new ByteArrayInputStream( nomFichier.getBytes() ) , "UTF8" ) );
            //BufferedReader bfr = new BufferedReader( new InputStreamReader( new FileInputStream( getSrcPath() + "files/" + fileName ),"UTF8" ) );
            
            String line = "";
            while( ( line = bfr.readLine() ) != null ){
                out.println(line);
            }
            bfr.close();
        %>

