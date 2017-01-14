package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.UserConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class CommunityConverter {

	private CommunityConverter() {

	}

	public static CommunityDto convert(Community community) {
		if (community == null) {
			return null;
		}
		CommunityDto dto = new CommunityDto(community.getId());
		dto.setId(community.getId());
		dto.setAllowedUsers(UserConverter.convertToDtoList(community.getAllowedUsers()));
		dto.setPendingUsers(UserConverter.convertToDtoList(community.getPendingUsers()));

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
		community.setAllowedUsers(UserConverter.convertToDoList(dto.getAllowedUsers()));
		community.setPendingUsers(UserConverter.convertToDoList(dto.getPendingUsers()));

		community.setName(dto.getName());
		community.setAuthor(UserConverter.convert(dto.getAuthor()));
		community.setActiveState(dto.getActiveState());
		community.setPublicState(dto.getPublicState());

		return community;
	}

	public static List<CommunityDto> convertToDtoList(Collection<Community> communities) {
		if (communities == null) {
			return null;
		}
		return communities.stream().map(CommunityConverter::convert).collect(Collectors.toList());
	}

	public static List<Community> convertToDoList(Collection<CommunityDto> communities) {
		if (communities == null) {
			return null;
		}
		return communities.stream().map(CommunityConverter::convert).collect(Collectors.toList());
	}
}
