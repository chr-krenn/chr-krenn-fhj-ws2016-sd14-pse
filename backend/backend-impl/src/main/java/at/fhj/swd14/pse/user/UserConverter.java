package at.fhj.swd14.pse.user;

import java.util.Collection;
import java.util.stream.Collectors;

public final class UserConverter {

    private UserConverter() {
    }

    public static UserDto convert(User user) {
        if (user == null) {
            return null;
        }
        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setMail(user.getMail());
        return dto;
    }

    public static User convert(UserDto user) {
        if (user == null) {
            return null;
        }
        final User entity = new User();
        entity.setId(user.getId());
        entity.setSalt(user.getSalt());
        entity.setPassword(user.getPassword());
        entity.setMail(user.getMail());
        return entity;
    }

    public static Collection<UserDto> convertToDtoList(Collection<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(UserConverter::convert).collect(Collectors.toList());
    }

    public static Collection<User> convertToDoList(Collection<UserDto> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(UserConverter::convert).collect(Collectors.toList());
    }
}
