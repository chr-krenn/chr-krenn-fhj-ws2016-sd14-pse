package at.fhj.swd14.pse.community;


import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.converter.StatusConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.CommunityRepository;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserService;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class CommunityServiceImplTest {

	@InjectMocks
	private CommunityServiceImpl service;
	
	@Mock
	private CommunityRepository communityRepo;
	
	
	private Community community;
	private List<Community> communities;

	@Before
	public void setup() throws NamingException
	{
		
        community = CommunityTestHelper.getTestCommunity();

	}
	
	@Test
	public void testFind()
	{
		Mockito.when(communityRepo.find(1L)).thenReturn(community);
		CommunityDto expectedCommunity = service.find(1L);
		
	}
	
	
	
}