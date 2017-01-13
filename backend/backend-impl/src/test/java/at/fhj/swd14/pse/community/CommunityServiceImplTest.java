package at.fhj.swd14.pse.community;


import at.fhj.swd14.pse.repository.internal.CommunityRepositoryImpl;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.NamingException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommunityServiceImplTest {

    @InjectMocks
    private CommunityServiceImpl service;

    @Mock
    private CommunityRepositoryImpl communityRepo;


    private Community community;
    private Community communityWithData;
    private List<Community> communities;

    @Before
    public void setup() throws NamingException {

        community = CommunityTestHelper.getTestCommunity();
        communityWithData = CommunityTestHelper.getTestCommunityWithData();
    }

    @Test
    public void testFind() {
        Mockito.when(communityRepo.find(1L)).thenReturn(community);
        CommunityDto expectedCommunity = service.find(1L);

    }

    

    @Test
    public void testFindByAuthorId() {
        //TODO anpassen bei der Implementierung, da sonnst error
        //Assert.assertNull(service.findByAuthorId(1L));
    }

    @Test
    public void testFindUserRelated() {
        //TODO anpassen bei der Implementierung, da sonnst error
        //Assert.assertNull(service.findUserRelated(1L));

    }
    
    @Test 
    public void findAllCommunities(){
    	 when(communityRepo.findAll()).thenReturn(Arrays.asList(buildCommunity(), buildCommunity(), buildCommunity()));
         final Collection<CommunityDto> coms = service.findAll();

         assertEquals(3, coms.size());
         coms.forEach(newsDto -> assertEquals(buildCommunityDto(), newsDto));
    }

    private Community buildCommunity(){
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
    
    private CommunityDto buildCommunityDto(){
    	final CommunityDto com = new CommunityDto();
    	 com.setActiveState(true);
	   	 com.setAllowedUsers(Arrays.asList(new UserDto()));
	   	 com.setAuthor(new UserDto());
	   	 com.setId(1L);
	   	 com.setName("TEST");
	   	 com.setPublicState(true);
   	 
   	 return com;
    }


}