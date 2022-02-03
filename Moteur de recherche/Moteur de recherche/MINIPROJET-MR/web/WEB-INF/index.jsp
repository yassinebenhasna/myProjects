<%-- 
    Document   : index1
    Created on : 29 mai 2020, 23:46:34
    Author     : yassine
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROJET-MR</title>
    </head>
    <body>
        
        
        <form method="POST" name="chercher" action="index.html">
            
            <%
                    String webPath = getServletContext().getRealPath("/");
                    String logoPath = webPath + "..\\..\\web\\fichiers\\logoFSO.jpg";
                    //
            %>

            <div style="text-align: center;padding-top: 130px">          
                
                <img src="images/logoFSO.png" alt="" style=" height :190px ; width :400px;" />
                
                <h1><label for="site-search" style="color: red;">محرك البحث</label></h1>

                <input type="submit" name="btn_chercher" value="ابحث" /> 
                <input style="width:600px; height:35px;" type="text" id="site-search" name="champ_search" placeholder="أدخل كلمات البحث هنا ...." dir="rtl"/>

                
            </div>

        </form>
        
        <div style="position:absolute;bottom:0;width:100%;padding-top:50px;height:10px;text-align: center">
        
            <p style="background: antiquewhite">
                <br>
                <b><U>Travail réalisé par :</U> BENHASNA YASSINE & KACHOUTI MOHAMED <br> <U>MASTER :</U> M2I <br>  © 2019-2020 </b>
            </p>
        </div>
        
    </body>
</html>
