
package WEB;

import WEB.MODEL.LoginModel;
import DAO.TraiLogin;
import METIER.Login;





import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    // recupere les donnes de login
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");

    // verification de username et mot de passe
        Login userLog = new Login();

            userLog.setUser(username);
            userLog.setPass(password);
        
        TraiLogin verifi = new TraiLogin();

            String resultatCnx = verifi.verif(userLog);

    // stocker les donnes dans le model
        LoginModel userModel = new LoginModel();
        
            userModel.setUser(userLog);
            
    // stocker le resultat dans le model 
    
        userModel.setResultatCnx(resultatCnx);

    // si le resultas du cnx est admin , alors on cree une session pour admin
    
        if (resultatCnx.equals("admin")) {

            HttpSession session = request.getSession(); //Creer la session pour admin
            session.setAttribute("admin", username); //creer les attributs de la session
            session.setAttribute("pass", password);
            //session.setMaxInactiveInterval(10);

            getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
        } 

    // si la resultas du cnx est user , alors on cree une session pour utilisateur
        else if (resultatCnx.equals("user")) {

            HttpSession session = request.getSession(); //Creer la session pour admin
            session.setAttribute("user", username); //creer les attributs de la session
            session.setAttribute("pass", password);
            //session.setMaxInactiveInterval(30);
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);

        } 
     // s'il l'anseignant est désactivé
        else if (resultatCnx.equals("Desolé! vous etes désactivé maintenant")) {
            request.setAttribute("errMessage", resultatCnx);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
    // s'il y a une erreur
        else {
            request.setAttribute("errMessage", resultatCnx);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        


    
    }  
}
