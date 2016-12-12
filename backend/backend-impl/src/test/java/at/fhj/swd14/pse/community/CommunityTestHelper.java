package at.fhj.swd14.pse.community;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import at.fhj.swd14.pse.converter.UserConverter;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonTestTools;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;

public class CommunityTestHelper {

	public static  Community getTestCommunity()
	{
		Community community = new Community();
		
		return community;
	}
	
	
	public static Community getTestCommunityWithData(){
		
		Date today = new Date(0);
		
		User author = new User();
		author.setId(1L);
		author.setMail("max@mustermann.at");
		author.setPassword("maxmustermann");
		author.setSalt("salt");
		
		List<UserDto> users = new ArrayList<>();
		
		
		Person personA = PersonTestTools.getDummyPerson();
		User userA = personA.getUser();
		
		Person personB = PersonTestTools.getDummyPerson();
		User userB = personA.getUser();
		
		users.add( UserConverter.convert(userA));
		users.add(UserConverter.convert(userB));
		
		Community community = new Community();
		community.setId(1L);
		community.setAuthor(author);
		community.setName("Test Community");
//		community.setCreateTime(today);
		community.setPublicState(true);
		community.setAllowedUsers(users);;
		
	
		return community;
		
		
	}
	
	
}
