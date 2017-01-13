package at.fhj.swd14.pse.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
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
    private CommunityRepository communityRepository;
    
    @InjectMocks
    private CommunityServiceImpl communityService;
    
    @Test 
    public void findAllCommunities(){
    	 when(communityRepository.findAll()).thenReturn(Arrays.asList(buildCommunities(), buildCommunities(), buildCommunities()));
         final Collection<CommunityDto> coms = communityService.findAll();

         assertEquals(3, coms.size());
         coms.forEach(newsDto -> assertEquals(buildCommunityDtos(), newsDto));
    }

    private Community buildCommunities(){
    	 final Community com = new Community();
    	 com.setActiveState(true);
    	 com.setAllowedUsers(Arrays.asList(new User()));
    	 com.setAllowedUsersInactive(new User());
    	 com.setAuthor(new User());
    	 com.setId(1L);
    	 com.setName("TEST");
    	 com.setPublicState(true);
    	 com.setUserCommunities(Arrays.asList(new UserCommunity()));
    	 
    	 return com;
    }
    
    private CommunityDto buildCommunityDtos(){
    	final CommunityDto com = new CommunityDto();
    	com.setActiveState(true);
   	 com.setAllowedUsers(Arrays.asList(new UserDto()));
   	 com.setAuthor(new UserDto());
   	 com.setId(1L);
   	 com.setName("TEST");
   	 com.setPublicState(true);
   	 
   	 return com;
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
