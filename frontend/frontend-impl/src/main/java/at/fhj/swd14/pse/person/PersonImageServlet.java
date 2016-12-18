package at.fhj.swd14.pse.person;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet to serve person images
 * @author Patrick Kainz
 *
 */
@WebServlet(name = "PersonImageServlet", urlPatterns = {"/personImage"})
public class PersonImageServlet extends HttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(PersonImageServlet.class);

	private static final long serialVersionUID = 1L;
	
	@EJB(name = "ejb/PersonService")
    private PersonService service;


	/**
	 * Processes both get and post request
	 * @param request Request data
	 * @param response Response channel
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	//exceptions cannot be handled here, as there is no user interaction except the image
        try{
	    	Long id =Long.parseLong(request.getParameter("id"));
	        
	        LOGGER.trace("Retrieving image for user: "+id);
	        PersonImageDto img = service.getPersonImage(id);
	        //write content type and content to the response
	        response.setContentType(img.getContentType());
	        ServletOutputStream outputStream = response.getOutputStream();
	        outputStream.write(img.getData());
	        outputStream.close();
	        
	        LOGGER.trace("Image for user "+id+" retrieved");
        }
        //at least log exceptions and errors, if we can't pass them to the page
        catch(Exception ex)        {
        	LOGGER.error("Exception occured while retrieving person image",ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Person image retrieval service";
    }
	
}
