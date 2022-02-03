<%@page import="java.util.List"%>
<%@page import="WEB.MODEL.Infos_perso_module"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <%@include file="/include/css.jsp"%>
    </head>

    <% //si pas de session cree pour admin , le rediriger vers page de login
        if (session.getAttribute("user") == null) {
    %>

    <jsp:forward page="../login.jsp"></jsp:forward>

    <%  }%>



    <body>

        <%@include file="/include/header_user.jsp"%>

        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="box_1620">







                <h3 style="text-align: center;color: red;background-color: beige"> La liste des enseignats...</h3><br/>
                
              
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-3">
                    <div class="panel panel-success">
                        <div class="panel-body">    
                            
                            <form class="form-inline md-form mr-auto mb-4" action="Cntrl_user?page=chercherEns" method="post">
                                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="mc">
                                <button class="btn btn-outline-warning btn-rounded btn-sm my-0" type="submit" style="background-color: beige">Search</button>
                            </form>   
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
                
        <div class="col-md-10 offset-1 " style="padding-top: 30px;">
            <div class="panel-heading"><h4><a href="Cntrl_user?page=Telecharger_ens" style="background-color: beige">Telecharger cette liste au format PDF.</a></h4></div>
            <table class="table table-striped table-dark " >
                    <thead>
                        <tr>
                            <th scope="col" style="color: #10ddd6;">Nom</th>
                            <th scope="col" style="color: #10ddd6;">Prenom</th>
                            <th scope="col" style="color: #10ddd6;">CIN</th>
                            <th scope="col" style="color: #10ddd6;">Email</th>
                            <th scope="col" style="color: #10ddd6;">Tel</th>
                            <th scope="col" style="color: #10ddd6;">Fonction</th>
                            <th scope="col" style="color: #10ddd6;">Grade</th>
                            <th scope="col" style="color: #10ddd6;">Cours</th>
                            <th scope="col" style="color: #10ddd6;">TD</th>
                            <th scope="col" style="color: #10ddd6;">TP</th>
                            
                            
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach items="${list_pers_mod.list_info_mod_ens}" var="list">
                                                        
                            <tr <c:if test="${list.p.id_etat != 1}" > style="background-color: red" </c:if> >
                                <th scope="row">${list.p.nom}</th>
                                <td>${list.p.prenom}</td>
                                <td>${list.p.cin}</td>
                                <td>${list.p.email}</td>
                                <td>${list.p.tel}</td>
                                <td>${list.fct}</td>
                                <td>${list.grade}</td>
                                <td><c:out value="${list.module_cours}" default="_"/></td>
                                <td><c:out value="${list.module_TD}" default="_"/></td>
                                <td><c:out value="${list.module_TP}" default="_"/></td>
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
