package at.fhj.swd14.pse.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MessageBeanTest {

    @InjectMocks
    private MessageStreamBean unitUnderTest;

    @Mock
    private MessageService messageService;
	
    /* more or less copied place holder test */
	@Test
	public void testSaving() {
        when(messageService.save(any(MessageDto.class))).thenReturn(1L);
        when(messageService.find(1L)).thenReturn(new MessageDto(1L));
        unitUnderTest.createMessage();

        assertNotNull(unitUnderTest.getMessage());
        assertEquals(1L, (long) unitUnderTest.getMessage().getId());

        verify(messageService, times(1)).save(any(MessageDto.class));
        verify(messageService, times(1)).find(1L);
		
	}

}
