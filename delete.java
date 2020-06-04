package homework2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kanban;


@WebServlet("/homework2/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Get the index of the task to be deleted
			int id = Integer.parseInt(request.getParameter("id"));
			
			// Get the name of the list to be deleted from
			String list = request.getParameter("list");
			
			// Get a reference to the correct list in the Servlet Context
			ArrayList<Kanban> listRemoved = (ArrayList<Kanban>) getServletContext().getAttribute(list);
			
			// Delete the todo from correct list
			for (Kanban todo : listRemoved) {
				if (todo.getId() == id) {
					listRemoved.remove( todo );
					break;
				}
			}
		} catch (Exception e) {}
	
		// Redirect the User back to kanban
		response.sendRedirect("Kanban");	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
