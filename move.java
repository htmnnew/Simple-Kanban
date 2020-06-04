package homework2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kanban;

@WebServlet("/homework2/move")
public class move extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Get the index of the task to be moved
			int id = Integer.parseInt(request.getParameter("id"));
			
			// Get the name of the list to be moved from
			String listfrom = request.getParameter("current");
			
			// Get the name of the list to be moved to
			String listinto = request.getParameter("destination");
			
			// Get a reference to the correct lists in the Servlet Context
			ArrayList<Kanban> listRemoved = (ArrayList<Kanban>) getServletContext().getAttribute(listfrom);
			ArrayList<Kanban> listAdded = (ArrayList<Kanban>) getServletContext().getAttribute(listinto);
			
			// find the task in the first list
			for (Kanban todo : listRemoved) {
				if (todo.getId() == id) {
					//add into correct list
					listAdded.add(todo);
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
