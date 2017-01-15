package at.fhj.swd14.pse.general;

import javax.faces.context.FacesContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;

/**
 * Mocks FacesContext, Source: http://illegalargumentexception.blogspot.co.at/2011/12/jsf-mocking-facescontext-for-unit-tests.html#mockFacesCurrentInstance
 *
 * @author Patrick Kainz
 */
public abstract class ContextMocker extends FacesContext {
    private ContextMocker() {
    }

    private static final Release RELEASE = new Release();

    private static class Release implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            setCurrentInstance(null);
            return null;
        }
    }

    public static FacesContext mockFacesContext() {
        FacesContext context = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        doAnswer(RELEASE).when(context).release();
        return context;
    }
}
