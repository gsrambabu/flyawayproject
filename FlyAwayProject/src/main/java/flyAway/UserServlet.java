package flyAway;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.sql.SQLException;
import java.util.HashMap;



import database.Dao;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try {
			Dao dao=new Dao();
			HashMap<String,String> user=dao.checkUser(email,password);
			HttpSession session=request.getSession();
			if(user!=null) {
				session.setAttribute("user", user);
				response.sendRedirect("User.jsp");}
			else {
				session.setAttribute("message", "Invalid Credentials!");
				response.sendRedirect("User.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
