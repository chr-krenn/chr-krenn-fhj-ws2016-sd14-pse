package at.fhj.swd14.pse.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.CommunityConverter;
import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.repository.CommunityRepository;
import at.fhj.swd14.pse.repository.UserRepository;
import at.fhj.swd14.pse.user.UserDto;

@Stateless
public class CommunityServiceImpl implements CommunityService{

	@EJB
    private CommunityRepository communityRepository;
	
	@EJB
    private UserRepository userRepository;
	
	@Override
	public long save(CommunityDto community) {
		
		Community dtoComm = CommunityConverter.convert(community);
		communityRepository.save(dtoComm);
        return dtoComm.getId();
	}

	@Override
	public CommunityDto find(long id) {
		return CommunityConverter.convert(communityRepository.find(id));
	}

	@Override
	public List<CommunityDto> findByAuthorId(Long creatorUserId) {
		
		// Test Code
		Map<String, Object> parametertest = new HashMap<>();
		parametertest.put("communityId", creatorUserId);
		List<UserDto> users = executeNamedQuery("Community.findAllowedUsers", parametertest, true);
		// Test Code
		
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("authorUserId", creatorUserId);
		return executeNamedQuery("Community.findByAuthorId", parameter);
	}

	@Override
	public List<CommunityDto> findUserRelated(Long userId) {
		
		Map<String, Object> parameter = new HashMap<>();
		List<CommunityDto> comDtos = executeNamedQuery("Community.findUserRelated",parameter);
		
		List<CommunityDto> relatedDtos = new ArrayList<CommunityDto>();
		
		comDtos.forEach(dto -> dto.getAllowedUsers().forEach(user -> {
			if(user.getId() == userId)
			{
				relatedDtos.add(dto);
			}
		}));		
 
		return relatedDtos;
	}
	
	private List<CommunityDto> executeNamedQuery(String name, Map<String, Object> parameter){
		return new ArrayList<>(CommunityConverter.convertToDtoList(communityRepository.executeNamedQuery(name, parameter)));
	}
	
	private List<UserDto> executeNamedQuery(String name, Map<String, Object> parameter, boolean fuckyou){
		return new ArrayList<>(UserConverter.convertToDtoList(userRepository.executeNamedQuery(name, parameter)));
	}

	private List<CommunityDto> executeNamedQuery(String name){
		return new ArrayList<>(CommunityConverter.convertToDtoList(communityRepository.executeNamedQuery(name)));
	}

}
