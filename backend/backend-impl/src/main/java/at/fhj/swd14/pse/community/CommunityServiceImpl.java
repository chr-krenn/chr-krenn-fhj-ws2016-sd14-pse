package at.fhj.swd14.pse.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserRepository;

@Stateless
public class CommunityServiceImpl implements CommunityService {

	@EJB
	private CommunityRepository communityRepository;

	@EJB
	private UserRepository userRepository;

	@Override
	public long save(CommunityDto communityDto) {

		Community community = CommunityConverter.convert(communityDto);

		Community foundCom = communityRepository.find(community.getId());
		mapDtoToDo(community, foundCom);

		communityRepository.update(foundCom);

		Community expected = communityRepository.find(foundCom.getId());
		if (expected != null) {
			return expected.getId();
		}
		return 0;
	}

	private Community mapDtoToDo(Community commHmi, Community com) {
		com.setActiveState(commHmi.geActiveState());
		com.setAllowedUsers(commHmi.getAllowedUsers());
		com.setPendingUsers(commHmi.getPendingUsers());
		com.setAuthor(commHmi.getAuthor());
		com.setPublicState(commHmi.getPublicState());

		return com;
	}

	@Override
	public long remove(CommunityDto community) {
		communityRepository.remove(CommunityConverter.convert(community));
		Community expected = communityRepository.find(community.getId());
		if (expected != null) {
			return expected.getId();
		}
		return 0;
	}

	@Override
	public CommunityDto find(long id) {
		return CommunityConverter.convert(communityRepository.find(id));
	}

	@Override
	public List<CommunityDto> findByAuthorId(Long creatorUserId) {

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("authorUserId", creatorUserId);
		List<CommunityDto> result = executeNamedQuery("Community.findByAuthorId", parameter);
		return result;
	}

	@Override
	public List<CommunityDto> findUserRelated(Long userId) {
		final List<CommunityDto> communities = executeNamedQuery("Community.findUserRelated", new HashMap<>());

		return communities.stream()
				.filter(dto -> dto.getAllowedUsers().stream()
						.anyMatch(allowedUser -> Objects.equals(allowedUser.getId(), userId)))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<CommunityDto> findRequestedCommunities() {
		return executeNamedQuery("Community.findRequestedCommunities");
	}

	private List<CommunityDto> executeNamedQuery(String name, Map<String, Object> parameter) {
		return new ArrayList<>(
				CommunityConverter.convertToDtoList(communityRepository.executeNamedQuery(name, parameter)));
	}
	
	private List<CommunityDto> executeNamedQuery(String name) {
		return new ArrayList<>(
				CommunityConverter.convertToDtoList(communityRepository.executeNamedQuery(name)));
	}

	@Override
	public List<CommunityDto> findAll() {

		return new ArrayList<>(CommunityConverter.convertToDtoList(communityRepository.findAll()));
	}

	@Override
	public boolean removeUserFromComunity(long communityId, long userId) {
		Community com = communityRepository.find(communityId);
		User allowedUser = userRepository.find(userId);
		if (com != null) {
			com.setAllowedUsersInactive(allowedUser);
		}
		return false;
	}

	@Override
	public boolean addUserToComunity(long communityId, long userId) {
		Community com = communityRepository.find(communityId);
		User allowedUser = userRepository.find(userId);
		if (com != null) {
			com.activateUserInUserCommunities(allowedUser);
		}
		return false;
	}

}
