package at.fhj.swd14.pse.user;

public class UserServiceHelper {

	public static UserDto insertUser(UserService userService) {
		if (userService == null) {
			throw new IllegalArgumentException("userService can't be null");
		}
		UserDto userDto = new UserDto();
		userDto.setMail("user@test.com");
		userDto.setPassword("user");
		userDto.setSalt("salt");
		long userId = userService.save(userDto);
		userDto.setId(userId);
		return userDto;
	}
}
