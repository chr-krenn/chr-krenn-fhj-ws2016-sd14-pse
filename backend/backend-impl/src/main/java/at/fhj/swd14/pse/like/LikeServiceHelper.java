package at.fhj.swd14.pse.like;

import java.util.List;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserRepository;

public class LikeServiceHelper {

	private List<User> users;
	private UserRepository userRepository;
	private int positionInList;
	
	public LikeServiceHelper(List<User> users,UserRepository userRepository)
	{
		this.users = users;
		this.userRepository = userRepository;
		this.positionInList = 0;
	}
	
	public boolean isUserInList(long id)
	{
		boolean isUserInList = false;
        this.positionInList = 0;
        for (int i = 0; i < users.size(); i++)
        {
        	User user = users.get(i);
        	long userId = user.getId();
        	if (id == userId)
        	{
        		isUserInList = true; // user has already liked the comment
        		this.positionInList = i;
        	}
        }
        
        return isUserInList;
	}
	
	public void removeUserFromList()
	{
		users.remove(this.positionInList);
	}
	
	public void insertUserInList(long id)
	{
		User user = this.userRepository.find(id); // find user in database
    	users.add(user);
	}
}
