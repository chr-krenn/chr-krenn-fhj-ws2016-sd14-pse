package at.fhj.swd14.pse.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {

    @InjectMocks
    private UserBean unitUnderTest;

    @Mock
    private UserService userService;

    /**
     * This is dummy test to show you how to mock stuff with Mockito.
     * Please insert proper assertions and verifications when testing!
     */
    @Test
    public void test() {
        when(userService.save(any(UserDto.class))).thenReturn(1L);
        when(userService.find(1L)).thenReturn(new UserDto(1L));
        unitUnderTest.save();

        assertNotNull(unitUnderTest.getUser());
        assertEquals(1L, (long) unitUnderTest.getUser().getId());

        verify(userService, times(1)).save(any(UserDto.class));
        verify(userService, times(1)).find(1L);
    }
}