package at.fhj.swd14.pse.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.user.User;



@RunWith(MockitoJUnitRunner.class)
public class UserCommunityTest {

	
	@Test
	public void testHash(){
		UserCommunity uc = new UserCommunity();
		int actual = uc.hashCode();
		
		Assert.assertEquals(31, actual);
	}
	
	@Test
	public void testHashNull(){
		UserCommunity uc = new UserCommunity();
		UserCommunityPK pk = new UserCommunityPK(2L, 2L);
		uc.setId(pk);
		int actual = uc.hashCode();
		
		Assert.assertEquals(35, actual);
	}
	
	
	@Test
	public void testGetPk(){
		Community c = new Community();
		c.setId(1L);
		Long actuals = c.getId();
		Long expected = 1L;
		Assert.assertEquals(expected, actuals);
	}
	
	@Test
	public void testEqualsValid(){
		
		UserCommunity c1 = new UserCommunity(new User(1L), new Community(), true);
		UserCommunity c2 = new UserCommunity(new User(1L), new Community(), true);
		
		
		boolean valid = c1.equals(c2);
		
		Assert.assertTrue(valid);

	}
	
	
	@Test
	public void testEqualsInvalid(){
		
		Community c1 = new Community();
		
		boolean invalid = c1.equals(null);
		
		Assert.assertFalse(invalid);

	}
	
	
}
