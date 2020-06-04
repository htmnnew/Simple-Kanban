package homework2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kanban;

@WebServlet("/homework2/Kanban")
public class KanbanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KanbanServlet() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//get servlet context
		ServletContext context = this.getServletContext();
		
		//List to store to-do's
		ArrayList<Kanban> todo = new ArrayList<Kanban>();
			//pre populate with some todos
			todo.add(new Kanban("Do Homework","sit down and studyy for homework"));
			todo.add(new Kanban("Buy food","go to the store and buy pizzarolls"));
			todo.add(new Kanban("Apply for a job","start working on resume to apply"));
			
			//Add these into the servlet context
			context.setAttribute("todo", todo);
			
		//List to store in progress's
		ArrayList<Kanban> progress = new ArrayList<Kanban>();
			//pre populate with some in progresses
			progress.add(new Kanban("Study for Midterm","study for upcoming ee midterm"));
			progress.add(new Kanban("Finish Metro","finish the main campaign until end of week"));
			
			//Add these into the servlet context
			context.setAttribute("progress", progress);
				
		//List to store in completed's
		ArrayList<Kanban> completed = new ArrayList<Kanban>();
			//pre populate with some completed
			completed.add(new Kanban("Go to gym","go work out for an hour tonight"));
			completed.add(new Kanban("Surgery","go to the surgery on friday the 1st"));
			
				//Add these into the servlet context
				context.setAttribute("completed", completed);
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //reading from context into arrays
		ServletContext context = this.getServletContext();
		ArrayList<Kanban> todos      = (ArrayList<Kanban>) context.getAttribute("todo");
		ArrayList<Kanban> progress  = (ArrayList<Kanban>) context.getAttribute("progress");
		ArrayList<Kanban> completed = (ArrayList<Kanban>) context.getAttribute("completed");
		
		
		
		
		response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Kanban</title>");
        out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("	 <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\" integrity=\"sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr\" crossorigin=\"anonymous\">");
        out.println("	<style>\r\n" + 
        		"		.card-header > a:link, .card-header > a:visited, .card-header > a:hover { color: white; }	\r\n" + 
        		"		.card-footer  a:link, .card-footer a:visited { color: gray;} \r\n" + 
        		"		a:hover.move { color: blue;}	\r\n" + 
        		"		a:hover.delete { color: red; }\r\n" + 
        		"		</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container-fluid\">");
        out.println("<div class=\"jumbotron my-3\">");
        out.println("	<h1 class=\"display-4\">Kanban</h1>");
        out.println("</div>");
        
        out.println("<div class=\"row\">");
        out.println("  <div class=\"col-md\">");
        
        //left table here-------------------------------------------------------------
	        out.println("<h3>To-Do</h3>");
	        out.println("<hr class=\"my-4\">");

			for( Kanban todo : todos ) {
				out.println(" <div class=\"card mb-4\">");
				out.println(" 	<h5 class=\"card-header text-white bg-warning\">");
				out.println(" 		<a class=\"white-icon\" href=\"priority?id=" + todo.getId() + "&action=decrease&list=todo\"><i class=\"fas fa-caret-down float-right\"></i></a>");
				out.println(" 		<a class=\"white-icon\" href=\"priority?id=" + todo.getId() + "&action=increase&list=todo\"><i class=\"fas fa-caret-up float-right\"></i></a>");
				out.println(		todo.getTitle());
				out.println(" 	</h5>");
				out.println("   <div class=\"card-body\">");
				out.println("     	<p class=\"card-text\">"+  todo.getTask()  +"</p>");
				out.println(" 	</div>");
				out.println(" 	<div class=\"card-footer\">");
				out.println(" 		<span class=\"float-left\">");
				out.println(" 	  		<a class=\"delete\" href=\"delete?id=" + todo.getId() + "&list=todo\"><i class=\"fas fa-trash\"></i></a>");
				out.println(" 	  	</span>");
				out.println(" 		<span class=\"float-right\">");
				out.println(" 			<a class=\"move\" href=\"move?id=" + todo.getId() + "&current=todo&destination=progress\">Move to In-Progress <i class=\"fas fa-caret-right\"></i></a>");
				out.println(" 		</span>	");
				out.println(" 	</div>");
				out.println(" </div>");
			
			}
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        out.println("  </div>");
        out.println("  <div class=\"col-md\">");
        
        //middle table here-----------------------------------------------------------
	        out.println("<h3>In-Progress</h3>");
	        out.println("<hr class=\"my-4\">");
			for( Kanban todo : progress ) {
				out.println(" <div class=\"card mb-4\">");
				out.println(" 	<h5 class=\"card-header text-white bg-primary\">");
				out.println(" 		<a class=\"white-icon\" href=\"priority?id=" + todo.getId() + "&action=decrease&list=progress\"><i class=\"fas fa-caret-down float-right\"></i></a>");
				out.println(" 		<a class=\"white-icon\" href=\"priority?id=" + todo.getId() + "&action=increase&list=progress\"><i class=\"fas fa-caret-up float-right\"></i></a>");
				out.println(		todo.getTitle());
				out.println(" 	</h5>");
				out.println("   <div class=\"card-body\">");
				out.println("     	<p class=\"card-text\">"+  todo.getTask()  +"</p>");
				out.println(" 	</div>");
				out.println(" 	<div class=\"card-footer\">");
				out.println(" 		<span class=\"float-left\">");
				out.println(" 	  		<a class=\"delete\" href=\"delete?id=" + todo.getId() + "&list=progress\"><i class=\"fas fa-trash\"></i></a>");
				out.println(" 	  	</span>");
				out.println(" 		<span class=\"float-right\">");
				out.println(" 			<a class=\"move\" href=\"move?id=" + todo.getId() + "&current=progress&destination=completed\">Move to completed <i class=\"fas fa-caret-right\"></i></a>");
				out.println(" 		</span>	");
				out.println(" 	</div>");
				out.println(" </div>");
			
			}
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        out.println("  </div>");
        out.println("  <div class=\"col-md\">");
        
        //right table here------------------------------------------------------------
	        out.println("<h3>Completed</h3>");
	        out.println("<hr class=\"my-4\">");
			for( Kanban todo : completed ) {
				out.println(" <div class=\"card mb-4\">");
				out.println(" 	<h5 class=\"card-header text-white bg-success\">");
				out.println(		todo.getTitle());
				out.println(" 	</h5>");
				out.println("   <div class=\"card-body\">");
				out.println("     	<p class=\"card-text\">"+  todo.getTask()  +"</p>");
				out.println(" 	</div>");
				out.println(" </div>");
			
			}
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        out.println("  </div>");
        out.println("</div>");
        
        // Add new card from here
        out.println("<hr class=\"my-4\">");
        out.println("<div class=\"row\">");
        out.println("	<div class=\"col\">");
        out.println("		<h3 class=\"text-center\">Add a New Card</h3>");
        out.println("		<form action=\"Kanban\" method=\"post\">");
        out.println("			<div class=\"form-group\">");
        out.println("		  		<label for=\"title\" class=\"\">Title</label>");
        out.println(" 		  		<input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\" placeholder=\"Enter a Title\">");
        out.println("			</div>");
        out.println("			<div class=\"form-group\">");
        out.println("		  		<label for=\"description\" class=\"\">Description</label>");
        out.println("		  		<textarea class=\"form-control\" rows=\"5\" id=\"description\" name=\"description\" placeholder=\"Enter a description\"></textarea>");
        out.println("			</div>");
        out.println("			<input type=\"submit\" class=\"btn btn-primary\" value=\"Add Card\">");
        out.println("		</form>");
        out.println("	</div>");
        out.println("</div>");
        
        //---------------------------------------------------------------------------
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check to see if the Add New Entry form was submitted
		// We'll do so by validating the inputs
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		
		if (title != null && title.trim().length() > 0 &&
			description != null && description.trim().length() > 0) {
					
			// Get a reference to the ToDo list in the Servlet Context
			ArrayList<Kanban> todos = (ArrayList<Kanban>) getServletContext().getAttribute("todo");
			
			// Add a new ToDo to the list using the title and description that were submitted
			todos.add( new Kanban(title, description) );
			
			// Send the User (Client) back to the main page
			response.sendRedirect("Kanban");
		}
		else
			doGet(request, response);
	}

}
