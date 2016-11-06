package at.fhj.swd14.pse.converter;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserConverter {

    private UserConverter() {

    }

    public static UserDto convert(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto(user.getId());
        dto.setMail(user.getMail());
        return dto;
    }

    public static User convert(UserDto user) {
        if (user == null) {
            return null;
        }
        User usr = new User(user.getId());
        usr.setMail(user.getMail());
        usr.setPassword(user.getPassword());
        usr.setSalt(user.getSalt());
        return usr;
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
