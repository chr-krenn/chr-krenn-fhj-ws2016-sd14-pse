package at.fhj.swd14.pse.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        final UserDto user = buildUserDto();
        assertEquals(user.getId().longValue(), userService.save(user));
        verify(userRepository, times(1)).save(refEq(UserConverter.convert(user)));
    }

    @Test(expected = UserServiceException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionIfUserCannotBeSaved() {
        doThrow(IllegalStateException.class).when(userRepository).save(any(User.class));
        userService.save(null);
    }

    @Test
    public void shouldFindUserWithId() {
        when(userRepository.find(1337L)).thenReturn(UserConverter.convert(buildUserDto()));

        final UserDto user = buildUserDto();
        //Set these to null, as these properties should not be retrieved anymore
        user.setSalt(null);
        user.setPassword(null);
        UserAssert.assertEquals(user, userService.find(1337L));
    }

    @Test(expected = UserServiceException.class)
    @SuppressWarnings("unchecked")
    public void shouldConvertExceptionsWhenFindingASingleNews() {
        when(userRepository.find(1337L)).thenThrow(IllegalStateException.class);
        userService.find(1337L);
    }

    private UserDto buildUserDto() {
        final UserDto user = new UserDto();
        user.setId(1337L);
        user.setMail("admin@cthulu");
        user.setPassword("superSecret");
        user.setSalt("verySaltyHere");
        return user;
    }

}