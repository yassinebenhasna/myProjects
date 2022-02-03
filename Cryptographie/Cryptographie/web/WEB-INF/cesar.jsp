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
        
        <form action="Cesar_cntrl" method="POST">
            
            Entrez votre text :<input type="text" name="text" required="true" /><br/>
            Entrez la clé :<select name="cle">
                <%
                    for(int i=1; i<26; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <% } %>
            </select><br/>
            <input type="submit" value="crypter" name="mthd" /><input type="submit" value="decrypter" name="mthd"/>
        </form>
        
        <h3>
            <%
            
                String text = (String) request.getAttribute("text");
                String text_crypte = (String) request.getAttribute("text_crypte");
                
                if(text_crypte != null)
                    
                        out.print("text : \""+text+" \"apres cryptage sera : "+text_crypte);
                
                String text_decrypte = (String) request.getAttribute("text_decrypte");
                
                if(text_decrypte != null)
                    
                        out.print("text : "+text+" apres decryptage sera : "+text_decrypte);
            
            %>
        </h3>
        
    </center>
    </body>
</html>
