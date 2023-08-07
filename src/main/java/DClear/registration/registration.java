package DClear.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class registration
 */
@WebServlet("/register")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String country=request.getParameter("country");
		String state=request.getParameter("state");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String coursename=request.getParameter("coursename");
		RequestDispatcher dispatcher=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DClear","root","arun@90085");
			PreparedStatement ps=con.prepareStatement("insert into student_user values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setString(5, country);
			ps.setString(6, state);
			ps.setString(7, password);
			ps.setString(8, gender);
			ps.setString(9, coursename);
			int rowCount=ps.executeUpdate();
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('successfully registerd');");
			out.println("</script>");
			dispatcher =request.getRequestDispatcher("home.html");
			if(rowCount>0)
			{
				request.setAttribute("status", "success");
				
			}
			else
			{
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
