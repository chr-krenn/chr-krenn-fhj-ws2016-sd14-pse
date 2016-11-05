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
        return new UserDto(user.getId());
    }

    public static User convert(UserDto user) {
        if (user == null) {
            return null;
        }
        return new User(user.getId());
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
