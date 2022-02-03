
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <%@include file="/include/css.jsp"%>
    </head>

    <% //si pas de session cree pour admin , le rediriger vers page de login
        if (session.getAttribute("admin") == null) {
    %>

    <jsp:forward page="../login.jsp"></jsp:forward>

    <%  }%>



    <body>

        <%@include file="/include/header_admin.jsp"%>

        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="box_1620">







                <h3 style="text-align: center;color: red;background-color: beige"> La liste des choix des enseignats</h3><br/>

        <div class="col-md-8 offset-2 " style="padding-top: 30px">
            <table class="table table-striped table-dark " >
                    <thead>
                        <tr>
                            <th scope="col" style="color: #10ddd6;">Enseignant</th>
                            <th scope="col" style="color: #10ddd6;">Module1</th>
                            <th scope="col" style="color: #10ddd6;">Module2</th>
                            <th scope="col" style="color: #10ddd6;">Module3</th>
                            <th scope="col" style="color: #10ddd6;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach items="${listChoix}" var="list">
                            
                            <tr>
                                <th scope="row">${list.nom_ens}</th>
                                <td>${list.ch_module1}</td>
                                <td>${list.ch_module2}</td>
                                <td>${list.ch_module3}</td>
                                <td>
                                    <form action="<%=request.getContextPath()%>/Cntrl_adm?page=modification" method="post"> 
                                        <input type="hidden" name="id_ens" value="${list.id_ens}" />
                                        <input type="hidden" name="nom" value="${list.nom_ens}" />
                                        <input type="hidden" name="m1" value="${list.ch_module1}" />
                                        <input type="hidden" name="m2" value="${list.ch_module2}" />
                                        <input type="hidden" name="m3" value="${list.ch_module3}" />
                                        <input type="submit" value="affecter"/>
                                    </form>
                                </td>
                            </tr>
                        
                        </c:forEach>
                      
                    </tbody>
                </table>
        </div>









            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <!--================Home Blog Area =================-->

        <!--================End Home Blog Area =================-->




        <%@include file="/include/footer.jsp"%>

    </body>

    <%@include file="/include/js.jsp"%>


</html>
