package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.security.DatabasePrincipal;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageBeanTest {

    @InjectMocks
    private MessageBean unitUnderTest;

    @Mock
    private UserService userService;

    @Mock
    private MessageService messageService;

    @Before
    public void setup() {
        when(messageService.save(any(MessageDto.class))).thenReturn(1L);
        when(userService.find(1L)).thenReturn(new UserDto(1L));

        final FacesContext context = ContextMocker.mockFacesContext();
        final ExternalContext externalContext = mock(ExternalContext.class);
        when(context.getExternalContext()).thenReturn(externalContext);
        DatabasePrincipal principal = mock(DatabasePrincipal.class);
        when(externalContext.getUserPrincipal()).thenReturn(principal);
        when(principal.getUserId()).thenReturn(1L);
    }

    @Test
    public void createMessageShouldSaveExpectedMessageDto() {
        // GIVEN
        unitUnderTest.getMessage().setContent("someContent");
        unitUnderTest.getMessage().setTitle("someTitle");

        // WHEN
        unitUnderTest.createMessage();

        // THEN
        MessageDto expectedMessageDto = new MessageDto();
        expectedMessageDto.setAuthor(new UserDto(1L));
        expectedMessageDto.setContent("someContent");
        expectedMessageDto.setTitle("someTitle");

        assertNotNull(unitUnderTest.getMessage());
        assertNull(unitUnderTest.getMessage().getId());

        verify(messageService, times(1)).save(Mockito.eq(expectedMessageDto));
        verify(userService, times(1)).find(1L);
    }

    @Test
    public void createMessageShouldNotSaveWhenContentIsEmpty() {
        // GIVEN
        unitUnderTest.getMessage().setTitle("someTitle");
        unitUnderTest.getMessage().setRecipient(new UserDto(1L));

        // WHEN
        unitUnderTest.createMessage();

        // THEN
        assertNotNull(unitUnderTest.getMessage());
        assertNull(unitUnderTest.getMessage().getId());

        verify(messageService, times(0)).save(Mockito.any(MessageDto.class));
        verify(userService, times(1)).find(1L);
    }

    @Test
    public void createMessageShouldNotSaveWhenTitleIsEmpty() {
        // GIVEN
        unitUnderTest.getMessage().setContent("someContent");
        unitUnderTest.getMessage().setRecipient(new UserDto(1L));

        // WHEN
        unitUnderTest.createMessage();

        // THEN
        assertNotNull(unitUnderTest.getMessage());
        assertNull(unitUnderTest.getMessage().getId());

        verify(messageService, times(0)).save(Mockito.any(MessageDto.class));
        verify(userService, times(1)).find(1L);
    }

    @Test
    public void createMessageShouldNotSaveWhenTitleAndContentAreEmpty() {
        // GIVEN
        unitUnderTest.getMessage().setRecipient(new UserDto(1L));

        // WHEN
        unitUnderTest.createMessage();

        // THEN
        assertNotNull(unitUnderTest.getMessage());
        assertNull(unitUnderTest.getMessage().getId());

        verify(messageService, times(0)).save(Mockito.any(MessageDto.class));
        verify(userService, times(1)).find(1L);
    }
}
