package at.fhj.swd14.pse.community;


import at.fhj.swd14.pse.repository.internal.CommunityRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.NamingException;
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
    public void testSaveCommunity() {
        CommunityDto dummyCommDto = CommunityConverter.convert(communityWithData);
        service.save(dummyCommDto);

        Mockito.verify(communityRepo, Mockito.times(1)).save(Mockito.any(Community.class));
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


}