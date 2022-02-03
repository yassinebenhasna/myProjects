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







                <h3 style="text-align: center;color: red;background-color: beige"> La liste des modules...</h3><br/>
        
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-3">
                    <div class="panel panel-success">
                        <div class="panel-body">    
                            
                            <form class="form-inline md-form mr-auto mb-4" action="Cntrl_user?page=chercherMod" method="post">
                                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="mc">
                                <button class="btn btn-outline-warning btn-rounded btn-sm my-0" type="submit" style="background-color: beige">Search</button>
                            </form>   
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
                
        <div class="col-md-8 offset-2 " style="padding-top: 30px">
            <table class="table table-striped table-dark " >
                    <thead>
                        <tr>
                            <th scope="col" style="color: #10ddd6;">Module</th>
                            <th scope="col" style="color: #10ddd6;">Responsable</th>
                            <th scope="col" style="color: #10ddd6;">Ens_cours</th>
                            <th scope="col" style="color: #10ddd6;">Ens_TD</th>
                            <th scope="col" style="color: #10ddd6;">Ens_TP</th>
                            <th scope="col" style="color: #10ddd6;">Semestre</th>
                            <th scope="col" style="color: #10ddd6;">Type</th>
                            <th scope="col" style="color: #10ddd6;">Filiere</th>
                            <th scope="col" style="color: #10ddd6;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach items="${listTransformationEnNom.list_trasnformation}" var="list">
                            
                            <tr>
                                <th scope="row"><c:out value="${list.nom_module}" default="_"/></th>
                                <td><c:out value="${list.nom_responsable}" default="_"/></td>
                                <td><c:out value="${list.nom_ens_cours}" default="_"/></td>
                                <td><c:out value="${list.nom_ens_td}" default="_"/></td>
                                <td><c:out value="${list.nom_ens_tp}" default="_"/></td>
                                <td><c:out value="${list.nom_semetre}" default="_"/></td>
                                <td><c:out value="${list.nom_type}" default="_"/></td>
                                <td>
                                    <c:forEach items="${list.nom_filiere}" var="list_fils">
                                        (${list_fils})
                                    </c:forEach>
                                </td>
                                <td>                                 
                                    <form action="<%=request.getContextPath()%>/Cntrl_user?page=Telecharger_module" method="post"> 
                                        <input type="hidden" name="nom_module" value="${list.nom_module}" />
                                        <input type="hidden" name="nom_ens_cours" value="${list.nom_ens_cours}" />
                                        <input type="hidden" name="nom_ens_td" value="${list.nom_ens_td}" />
                                        <input type="hidden" name="nom_ens_tp" value="${list.nom_ens_tp}" />
                                        <input type="submit" value="Telecharger"/>
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
