package at.fhj.swd14.pse.message;

import javax.faces.context.ExternalContext;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.faces.context.FacesContext;

import org.mockito.Mockito;

import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.security.DatabasePrincipal;
import at.fhj.swd14.pse.user.UserDto;

class AbstractMessageTest {
	
	private FacesContext context;
	private ExternalContext externalContext;

	AbstractMessageTest() {
		init();
	}
	
	private void init() {
		this.context = ContextMocker.mockFacesContext();
		this.externalContext = mock(ExternalContext.class);
        when(getContext().getExternalContext()).thenReturn(getExternalContext());
        DatabasePrincipal principal = Mockito.mock(DatabasePrincipal.class);
        Mockito.when(getExternalContext().getUserPrincipal()).thenReturn(principal);
        Mockito.when(principal.getUserId()).thenReturn(1L);
	}
	
	private FacesContext getContext() {
		return context;
	}
	
	private ExternalContext getExternalContext() {
		return this.externalContext;
	}

}
