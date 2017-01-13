package at.fhj.swd14.pse.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class UserCommunityTest {

	
	@Test
	public void testHash(){
		UserCommunity uc = new UserCommunity();
		int actual = uc.hashCode();
		
		
		Assert.assertEquals(31, actual);
	}
	
	@Test
	public void testGetPk(){
		Community c = new Community();
		c.setId(1L);
		Long actuals = c.getId();
		Long expected = 1L;
		Assert.assertEquals(expected, actuals);
	}
	

	
	
}
