<%-- 
    Document   : cesar
    Created on : 13 janv. 2020, 19:46:51
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        
        <form action="RSA_cntrl" method="POST">
            
            Entrez votre text :<input type="text" name="text" required="true" /><br/>
            Entrez la clé p :<input type="text" name="p" required="true"/>
            <br/>
            Entrez la clé q :<input type="text" name="q" required="true"/><br/>
            Entrez la clé s :<input type="text" name="s" required="true"/><br/>

            <input type="submit" value="crypter" name="mthd" /><input type="submit" value="decrypter" name="mthd"/>
        </form>
        
        <h3>
            <%
            
                String text = (String) request.getAttribute("text");
                String text_crypte = (String) request.getAttribute("text_crypte");
                
                if(text_crypte != null)
                    
                        out.print("text : "+text+" apres cryptage sera : "+text_crypte);
                
                String text_decrypte = (String) request.getAttribute("text_decrypte");
                
                if(text_decrypte != null)
                    
                        out.print("text : "+text+" apres decryptage sera : "+text_decrypte);
            
            %>
        </h3>
        
    </center>
    </body>
</html>
