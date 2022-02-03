
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





 


                <h3>chercher ens</h3>
                
                
                
                
                
                
                
                
                
            </div>
        </section>
        <!--================End Home Banner Area =================-->


        <%@include file="/include/footer.jsp"%>

    </body>

    <%@include file="/include/js.jsp"%>


</html>
