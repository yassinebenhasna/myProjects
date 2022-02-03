<%-- 
    Document   : cesar
    Created on : 13 janv. 2020, 19:46:51
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        
        <form action="Attaque_cesar_cntrl" method="POST">
            
            Entrez votre text crypté :<input type="text" name="text" required="true" /><br/>

            <input type="submit" value="Attaquer" name="mthd"/>
        </form>
        
        <h3>
            <%
            
                String text = (String) request.getAttribute("text");

                if(text != null)
                
                out.print("Resultat de l'attaque sur \""+text+"\" : ");
            
            %>
        </h3>
            
            <% int i = 1; %>

            <c:forEach items="${list}" var="list" >
                <p>le resultat pour cle=<%=i%> est : <c:out value="${list}" /></p>
                <% i++; %>
            </c:forEach>
        
    </center>
    </body>
</html>
