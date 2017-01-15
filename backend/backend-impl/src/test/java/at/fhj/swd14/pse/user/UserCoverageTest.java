package at.fhj.swd14.pse.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserCoverageTest {

    @Test
    public void increaseCoverage() {
        User u = new User();
        u.setPassword("1234");
        u.setSalt("salt");
        assertEquals("1234", u.getPassword());
        assertEquals("salt", u.getSalt());
        assertNull(u.getCommunities());
    }
}
