package at.fhj.swd14.pse.community;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonTestTools;
import at.fhj.swd14.pse.repository.internal.CommunityRepositoryImpl;
import at.fhj.swd14.pse.repository.internal.UserRepositoryImpl;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class CommunityServiceImplTest {

	@InjectMocks
	private CommunityServiceImpl service;

	@Mock
	private CommunityRepositoryImpl communityRepo;

	@Mock
	private UserRepositoryImpl userRepo;
	private User userA;

	private Community community;
	private Community communityWithData;
	private List<Community> communities;

	@Before
	public void setup() throws NamingException {

		community = CommunityTestHelper.getTestCommunity();
		communityWithData = CommunityTestHelper.getTestCommunityWithData();
		Person person = PersonTestTools.getDummyPerson();
		userA = person.getUser();
	}

	@Test
	public void testFind() {
		Community com = buildTestCommunity(1L);
		Mockito.when(communityRepo.find(1L)).thenReturn(com);
		CommunityDto result = service.find(1L);

		CommunityDto expected = CommunityConverter.convert(buildTestCommunity(1L));
		CommunityAssert.assertEquals(expected, result);

	}

	@Test
	public void testFindByAuthorId() {
		List<Community> community = Arrays.asList(buildTestCommunity(1L));

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("authorUserId", 1L);

		Mockito.when(communityRepo.executeNamedQuery("Community.findByAuthorId", parameter)).thenReturn(community);

		List<CommunityDto> communityDtos = CommunityConverter.convertToDtoList(community);
		List<CommunityDto> dtoList = service.findByAuthorId(1L);
		CommunityAssert.assertEquals(communityDtos, dtoList);
	}

	@Test
	public void findAllCommunities() {
		when(communityRepo.findAll())
				.thenReturn(Arrays.asList(buildTestCommunity(1L), buildTestCommunity(2L), buildTestCommunity(3L)));
		final Collection<CommunityDto> coms = service.findAll();

		assertEquals(3, coms.size());
		// coms.forEach(newsDto -> assertEquals(buildTestCommunityDto(1L),
		// newsDto));
	}

	/* Helper Methods */

	private Community buildTestCommunity(Long userId) {
		final Community com = new Community();

		com.setActiveState(true);
		com.setAllowedUsers(Arrays.asList(new User(userId)));
		com.setAllowedUsersInactive(new User(userId));
		com.setAuthor(new User(userId));
		com.setId(userId);
		com.setName("TEST");
		com.setPublicState(true);
		com.setUserCommunities(Arrays.asList(new UserCommunity()));

		return com;
	}

	private Community buildTestCommunity(Long userId, UserCommunity uk) {
		Community com = new Community();
		com = buildTestCommunity(userId);
		com.setUserCommunities(Arrays.asList(uk));

		return com;
	}

	private Community buildTestCommunity(Long userId, List<User> pendingUsers) {

		Community com = new Community();
		com = buildTestCommunity(userId);
		com.setPendingUsers(pendingUsers);

		return com;
	}

	private CommunityDto buildTestCommunityDto(Long userId) {
		final CommunityDto com = new CommunityDto();

		com.setActiveState(true);
		com.setAllowedUsers(Arrays.asList(new UserDto(userId)));
		com.setAuthor(new UserDto(userId));
		com.setId(userId);
		com.setName("TEST");
		com.setPublicState(true);

		return com;
	}

	private CommunityDto buildTestCommunityDto(Long userId, Long userIdPend) {
		final CommunityDto com = buildTestCommunityDto(userId);
		com.setPendingUsers(Arrays.asList(new UserDto(userIdPend)));

		return com;
	}

	/* schoeneg Temp */

	@Test
	public void test_save_sucess() {
		UserCommunity uk = new UserCommunity();
		uk.setUser(new User(100L));
		Community com = buildTestCommunity(100L, uk);
		CommunityDto comDto = buildTestCommunityDto(100L, 101L);
		Mockito.when(communityRepo.find(100L)).thenReturn(com);
		Mockito.doNothing().when(communityRepo).update(Matchers.any(Community.class));

		long result = service.save(comDto);
		assertEquals(comDto.getId(), (Long) result);
	}

	@Test
	public void test_save_fail() {
		UserCommunity uk = new UserCommunity();
		uk.setUser(new User(110L));
		CommunityDto comDto = buildTestCommunityDto(110L, 101L);
		Mockito.when(communityRepo.find(110L)).thenReturn(null);
		Mockito.doNothing().when(communityRepo).update(Matchers.any(Community.class));

		long result = service.save(comDto);
		Long expected = 0L;
		assertEquals(expected, (Long) result);
	}

	@Test
	public void test_remove_sucess() {
		UserCommunity uk = new UserCommunity();
		uk.setUser(new User(110L));
		Community com = buildTestCommunity(110L, uk);
		CommunityDto comDto = buildTestCommunityDto(110L, 101L);
		Mockito.when(communityRepo.find(110L)).thenReturn(com);

		long result = service.remove(comDto);
		assertEquals(comDto.getId(), (Long) result);
	}

	@Test
	public void test_remove_fail() {
		UserCommunity uk = new UserCommunity();
		uk.setUser(new User(110L));
		CommunityDto comDto = buildTestCommunityDto(110L, 101L);
		Mockito.when(communityRepo.find(110L)).thenReturn(null);

		long result = service.remove(comDto);
		Long expected = 0L;
		assertEquals(expected, (Long) result);
	}

	@Test
	public void test_findUserRelated_sucess() {

		UserCommunity uk = new UserCommunity();
		uk.setUser(new User(110L));
		Community testCom1 = buildTestCommunity(110L, uk);
		testCom1.setAllowedUsers(Arrays.asList(new User(110L)));
		Community testCom2 = buildTestCommunity(111L, uk);
		testCom2.setAllowedUsers(Arrays.asList(new User(110L)));

		Mockito.when(communityRepo.executeNamedQuery(Matchers.any(), Matchers.any()))
				.thenReturn(Arrays.asList(testCom1, testCom2));

		List<CommunityDto> userRelated = service.findUserRelated(110L);
		assertEquals(userRelated.size(), 2);

		List<CommunityDto> userRelatedNoMatch = service.findUserRelated(666L);
		assertEquals(userRelatedNoMatch.size(), 0);
	}

	@Test
	public void testRemoveUserFromCommunity() {
		Mockito.when(communityRepo.find(1L)).thenReturn(communityWithData);
		Mockito.when(userRepo.find(1L)).thenReturn(userA);
		List<User> users = new ArrayList<User>();
		users.add(userA);
		communityWithData.setAllowedUsers(users);
		boolean result = service.removeUserFromComunity(1L, 1L);
		assertTrue(result);
	}

	@Test
	public void testRemoveUserFromCommunityInvalidCommunity() {
		Mockito.when(userRepo.find(1L)).thenReturn(userA);
		List<User> users = new ArrayList<User>();
		users.add(userA);
		communityWithData.setAllowedUsers(users);
		assertFalse(service.removeUserFromComunity(-1L, 1L));
	}

}