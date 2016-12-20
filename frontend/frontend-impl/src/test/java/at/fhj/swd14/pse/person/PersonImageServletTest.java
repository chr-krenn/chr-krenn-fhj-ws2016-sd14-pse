package at.fhj.swd14.pse.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonImageServletTest {

	@Mock
	private PersonService service;
	
	@InjectMocks
	private PersonImageServlet servlet;
	
	private PersonImageDto image;
	
	private HttpServletRequest request;
	private ServletOutputStream ostream;
	private HttpServletResponse response;
	
	@Test
	public void testInfo()
	{
		Assert.assertEquals("Person image retrieval service", servlet.getServletInfo());
	}
	
	private void setupProcess() throws IOException
	{
		request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getParameter("id")).thenReturn("1");
		image = new PersonImageDto(1L);
		image.setContentType("png");
		image.setData(new byte[1]);
		Mockito.when(service.getPersonImage(1L)).thenReturn(image);
		response = Mockito.mock(HttpServletResponse.class);
		ostream = Mockito.mock(ServletOutputStream.class);
		Mockito.when(response.getOutputStream()).thenReturn(ostream);
	}
	
	private void verifyProcess() throws IOException
	{
		Mockito.verify(response,Mockito.times(1)).setContentType("png");
		Mockito.verify(ostream,Mockito.times(1)).write(image.getData());
		Mockito.verify(ostream,Mockito.times(1)).close();
	}
	
	@Test
	public void testGet() throws IOException, ServletException
	{
		setupProcess();
		servlet.doGet(request, response);
		verifyProcess();
	}
	
	@Test
	public void testPost() throws IOException, ServletException
	{
		setupProcess();
		servlet.doPost(request, response);
		verifyProcess();
	}
	
}
