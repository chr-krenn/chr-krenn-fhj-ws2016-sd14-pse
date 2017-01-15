package at.fhj.swd14.pse.user;

import org.junit.Test;

import at.fhj.swd14.pse.community.UserCommunity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class UserCoverageTest {
	
	@Test
	public void increaseCoverage(){
		User u = new User();
		u.setPassword("1234");
		u.setSalt("salt");
		Assert.assertEquals("1234", u.getPassword());
		Assert.assertEquals("salt", u.getSalt());
		Assert.assertNull(u.getCommunities());
	}
}
