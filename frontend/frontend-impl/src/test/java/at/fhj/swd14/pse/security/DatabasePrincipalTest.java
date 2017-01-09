package at.fhj.swd14.pse.security;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Test Class DatabasePrincipal
 * 
 * @author thomas.spitzer
 * 
 */
public class DatabasePrincipalTest {
	
	
	@Test
	public void testEquals() {
		DatabasePrincipal a = createDatabasePrincipalA();
		assertEquals(a,a);
	}

	@Test
	public void testNotEquals() {
		DatabasePrincipal a = createDatabasePrincipalA();
		DatabasePrincipal b = createDatabasePrincipalB();
		assertNotEquals(a,b);
	}

	@Test
	public void testHashCode() {
		assertEquals(createDatabasePrincipalA(), createDatabasePrincipalA());
	}	
	
	private DatabasePrincipal createDatabasePrincipalA() {
		DatabasePrincipal a = new DatabasePrincipal("a");
		a.setSalt("CRzTaAbyUxxEUs56");
		a.setUserId(1);
		return a;
	}
	
	private DatabasePrincipal createDatabasePrincipalB() {
		DatabasePrincipal b = new DatabasePrincipal("b");
		b.setUserId(2);
		b.setSalt("ASDfkdlfasdl3434");
		return b;
	}	

}


