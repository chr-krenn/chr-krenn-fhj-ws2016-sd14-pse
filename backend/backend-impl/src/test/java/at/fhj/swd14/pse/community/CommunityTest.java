package at.fhj.swd14.pse.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class CommunityTest {

	private static final long ID = 1L;
	
    @BeforeClass
    public static void beforeClass() {
        //drop table
    }

    @Before
    public void setup() {
        //drop & crate
    }

    @Test
    public void insertCommunity() {
        //test
    }
    
    
    
    @Mock
    private Community community;
    
    @InjectMocks
    private CommunityServiceImpl communityService;
    
    
    @Test
    public void testGetUserCommunities(){
    	List<UserCommunity> usercommunities = Arrays.asList(new UserCommunity(), new UserCommunity());
    	when(community.getUserCommunities()).thenReturn(usercommunities);
    	
    	List<UserCommunity> uc = community.getUserCommunities();
    	Assert.assertEquals(2, uc.size());
    }
  
    
    
    
    
    
    @After
    public void teardown() {
        //drop
    }


    @AfterClass
    public static void afterClass() {
        //drop create table
    }

}
