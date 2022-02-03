
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







                <h3 style="text-align: center;color: red;background-color: beige">Affecter le type d'enseignement au Mr(Mme) ${nom}:</h3><br/>
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 offset-3">
                            <div class="panel panel-success">
                                <div class="panel-body">

                                    <form action="Cntrl_adm?page=validermodule" method="post" >
                                        <input type="hidden" name="id" value="${id}"/>
                                        <input type="hidden" name="m1" value="${list.listModules.get(0).nom_mod}"/>
                                        <input type="hidden" name="m2" value="${list.listModules.get(1).nom_mod}"/>
                                        <input type="hidden" name="m3" value="${list.listModules.get(2).nom_mod}"/>
                                                                           
                                        
                                        
                                        <div class="form-group">
                                            <label for="type1">${list.listModules.get(0).nom_mod} :</label><p/>
                                            <select name="type1" id="type1" class="form-control">
                                                <option value="">------</option>
                                                <option value="cours">cours</option>
                                                <option value="TD">TD</option>
                                                <option value="TP">TP</option>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <label for="type2">${list.listModules.get(1).nom_mod} :</label><p/>
                                            <select name="type2" id="type2" class="form-control">
                                                <option value="">------</option>
                                                <option value="cours">cours</option>
                                                <option value="TD">TD</option>
                                                <option value="TP">TP</option>
                                            </select>
                                        </div><br/><br/>

                                        <div class="form-group">
                                            <label for="type3">${list.listModules.get(2).nom_mod} :</label><p/>
                                            <select name="type3" id="type3" class="form-control"/>
                                                <option value="">------</option>
                                                <option value="cours">cours</option>
                                                <option value="TD">TD</option>
                                                <option value="TP">TP</option>
                                            </select>
                                        </div><br/><br/>
                                
                                        <div class="form-group">
                                            <input type="submit" value="Valider" class=" btn btn-default">
                                        </div>

                                    </form> 


                                </div>
                            </div>                
                        </div>
                    </div>        
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
