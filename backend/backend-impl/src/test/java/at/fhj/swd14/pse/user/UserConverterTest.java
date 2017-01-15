package at.fhj.swd14.pse.user;

import org.junit.Test;

import java.util.Collection;

import org.junit.Assert;

public class UserConverterTest {
	
	@Test
	public void testDtoListNull(){
		Collection<User> values = UserConverter.convertToDoList(null);
        Assert.assertNull(values);
	}
	
	@Test
    public void testDoListNull() {
        Collection<UserDto> values = UserConverter.convertToDtoList(null);
        Assert.assertNull(values);
    }
}
