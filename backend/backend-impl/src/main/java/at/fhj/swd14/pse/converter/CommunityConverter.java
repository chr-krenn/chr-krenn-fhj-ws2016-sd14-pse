package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.community.CommunityDto;

public class CommunityConverter {
	
	public CommunityConverter() {
		
	}

	public static CommunityDto convert(Community community) {
		if (community == null) {
			return null;
		}
		CommunityDto dto = new CommunityDto(community.getId());
		dto.setId(community.getId());
		dto.setAllowedUsers(community.getAllowedUsers());		
		dto.setName(community.getName());
		dto.setAuthor(UserConverter.convert(community.getAuthor()));
		dto.setActiveState(community.geActiveState());
		dto.setPublicState(community.getPublicState());
		
		return dto;
	}

	public static Community convert(CommunityDto dto) {
		if (dto == null) {
			return null;
		}
		Community community = new Community();
		community.setId(dto.getId());
		community.setAllowedUsers(dto.getAllowedUsers());
		
		
		community.setName(dto.getName());
		community.setAuthor(UserConverter.convert(dto.getAuthor()));
		community.setActiveState(dto.getActiveState());
		community.setPublicState(dto.getPublicState());
		
		
		return community;
	}

	public static Collection<CommunityDto> convertToDtoList(Collection<Community> communities) {
        if (communities == null) {
            return null;
        }
        return communities.stream().map(CommunityConverter::convert).collect(Collectors.toList());
    }

    public static Collection<Community> convertToDoList(Collection<CommunityDto> communities) {
        if (communities == null) {
            return null;
        }
        return communities.stream().map(CommunityConverter::convert).collect(Collectors.toList());
    }
}
