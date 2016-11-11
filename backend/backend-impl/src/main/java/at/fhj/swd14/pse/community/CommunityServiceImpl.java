package at.fhj.swd14.pse.community;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.converter.CommentConverter;
import at.fhj.swd14.pse.converter.CommunityConverter;
import at.fhj.swd14.pse.repository.CommunityRepository;

@Stateless
public class CommunityServiceImpl implements CommunityService{

	@EJB
    private CommunityRepository communityRepository;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommunityDto> findUserRelated(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
