
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <%@include file="/include/css.jsp"%>
    </head>

    <% //si pas de session cree pour admin , le rediriger vers page de login
        if(session.getAttribute("admin")== null) {
    %>
        
            <jsp:forward page="../login.jsp"></jsp:forward>

    <%  } %>

    

    <body>

        <%@include file="/include/header_admin.jsp"%>

        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="box_1620">











                <div style="padding-top: 100px"/>
                <center><div style="background-color: beige;width: 600px"><b><h1 style=" color: tomato;">Université Mohammed Premier</h1></b></div></center><br>
                    
                <center><div style="background-color: beige;width: 400px"><h2 style=" color: tomato">Faculté des sciences </h2></div></center><br>
                    
                <center><div style="background-color: beige;width: 100px"><h2 style=" color: tomato">Oujda </h2></div></center><br>

             



             




            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <!--================Home Blog Area =================-->

        <!--================End Home Blog Area =================-->




        <%@include file="/include/footer.jsp"%>

    </body>

    <%@include file="/include/js.jsp"%>


</html>
