package at.fhj.swd14.pse.converter;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.community.CommunityDto;

public class CommunityConverter {

	public static CommunityDto convert(Community community) {
		if (community == null) {
			return null;
		}
		CommunityDto dto = new CommunityDto(community.getId());
		dto.setAuthor(UserConverter.convert(community.getAuthor()));
		
		return dto;
	}

	public static Community convert(CommunityDto dto) {
		if (dto == null) {
			return null;
		}
		Community comment = new Community();
		
		return comment;
	}
}
