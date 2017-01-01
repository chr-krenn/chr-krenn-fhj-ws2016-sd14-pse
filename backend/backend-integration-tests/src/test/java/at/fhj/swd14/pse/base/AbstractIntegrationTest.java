package at.fhj.swd14.pse.base;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;

public abstract class AbstractIntegrationTest {

	protected InitialContext context;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Before
	public void setup() throws NamingException
	{
		final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL,"remote://localhost:8080");
         
        context = new InitialContext(jndiProperties);
        
        
	}
	
}
