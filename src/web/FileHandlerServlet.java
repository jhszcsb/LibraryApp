package web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.ejb.EJB;
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
public class FileHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookFacade facade;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Part filePart = request.getPart("file");

		try (PrintWriter writer = response.getWriter();
				InputStream filecontent = filePart.getInputStream();) {
			byte[] bytes = new byte[filecontent.available()];
			filecontent.read(bytes);

			Book f = new Book();
			f.setTitle(name);
			f.setImage(bytes);
			f.setContentType(filePart.getContentType());
			facade.create(f);
			response.sendRedirect("index.html");
		}
	}
}
