package web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dal.BookFacade;
import entity.Book;

@WebServlet("/FileHandlerServlet")
@MultipartConfig
@SessionScoped
@Named("fileHandlerServlet")
public class FileHandlerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private BookFacade facade;
	
	private Book current;
	
	private Book edit;
	
	public Book getCurrent() {
		if (current == null)
			current = new Book();
		return current;
	}

	public void setCurrent(Book current) {
		this.current = current;
	}
	
	public Book getEdit() {
		if (edit == null)
			edit = new Book();
		return edit;
	}

	public void setEdit(Book edit) {
		this.edit = edit;
	}
	
	public String edit(Book item) {
		setCurrent(item);
		return "editBook";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Part filePart = request.getPart("file");

		try (PrintWriter writer = response.getWriter();	InputStream filecontent = filePart.getInputStream();) {
			byte[] bytes = new byte[filecontent.available()];
			filecontent.read(bytes);

			//editNewData();
			//current.setImage(bytes);
			//current.setContentType(filePart.getContentType());
			facade.edit(current);
			FacesUtil.addInfoMessage("Book successfully updated");
			response.sendRedirect("findBooks.html");
		}
	}
	
	public void editNewData() {
		current.setAuthor(edit.getAuthor());
		current.setTitle(edit.getTitle());
		current.setAvailablecopies(edit.getAvailablecopies());
		current.setGenre(edit.getGenre());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");

		Book f = facade.find(name);
		if (f == null) {
			response.setContentType("text/plain");
			response.getWriter().println("Not found.");
		} else {
			response.setContentType(f.getContentType());
			response.getOutputStream().write(f.getImage());
		}
	}
}
