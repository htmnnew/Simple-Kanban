package homework2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kanban;

@WebServlet("/homework2/priority")
public class priority extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get the index of the task to be moved
			int id = Integer.parseInt(request.getParameter("id"));
			
			// Get the name of the action(increase/decrease)
			String action = request.getParameter("action");
			String list = request.getParameter("list");
			
			// Get a reference to the correct list in the Servlet Context
			ArrayList<Kanban> List = (ArrayList<Kanban>) getServletContext().getAttribute(list);
			
			// find the task in the list
			for (Kanban todo : List) {
				if (todo.getId() == id) {
					int pos = List.indexOf(todo);
					if(action.equals("increase")) {
						//move up 1 position in the list
						Collections.swap(List, pos, pos-1);
						break;
					}
					else if(action.equals("decrease")){
						//move down 1 position in the list
						Collections.swap(List, pos, pos+1);
						break;
					}
					else {
						List.remove( todo );
					}
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
