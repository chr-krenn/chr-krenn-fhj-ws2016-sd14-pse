/**
 * 
 */
package at.fhj.swd14.pse.community;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

@Named
@SessionScoped
/**
 * @author schoeneg14, purkart
 *
 */
public class CommunityBean implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(CommunityBean.class);

	@EJB(name = "ejb/CommunityService")
	private CommunityService communityService;
	
	@EJB(name = "ejb/UserService")
	private UserService userService;

	private String newName;
	private String newDescription;
	private boolean newPublicState;
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	public boolean isNewPublicState() {
		return newPublicState;
	}

	public void setNewPublicState(boolean newPublicState) {
		this.newPublicState = newPublicState;
	}

	public List<CommunityDto> getCreatedCommunities() {
		return createdCommunities;
	}

	public void setCreatedCommunities(List<CommunityDto> createdCommunities) {
		this.createdCommunities = createdCommunities;
	}

	public List<CommunityDto> getJoinedCommunities() {
		return joinedCommunities;
	}

	public void setJoinedCommunities(List<CommunityDto> joinedCommunities) {
		this.joinedCommunities = joinedCommunities;
	}

	public List<CommunityDto> getPublicCommunities() {
		return publicCommunities;
	}

	public void setPublicCommunities(List<CommunityDto> publicCommunities) {
		this.publicCommunities = publicCommunities;
	}

	private List<CommunityDto> createdCommunities;
	private List<CommunityDto> joinedCommunities;
	private List<CommunityDto> publicCommunities;

	/**
	 * Initializes the bean for the view
	 */
	@PostConstruct
	public void init() {
		LOGGER.error("Initialising the CommunityBean");
		
		// Get logged in user
		long currentUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal)FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();;
		
		UserDto testUser = userService.find(currentUserId);
		
		this.createdCommunities = communityService.findByAuthorId(currentUserId);
		this.joinedCommunities = communityService.findUserRelated(currentUserId);
		this.publicCommunities = communityService.findUserRelated(currentUserId);
		
		// Just for testing...
		CommunityDto test1 = new CommunityDto(currentUserId);
		test1.setName("test1");
		test1.setAuthor(testUser);
		LOGGER.error(test1.toString());
		
		CommunityDto test2 = new CommunityDto(currentUserId+1);
		test2.setName("test2");
		test2.setAuthor(testUser);
		
		CommunityDto test3 = new CommunityDto(currentUserId+2);
		test3.setName("test3");
		test3.setAuthor(testUser);
		
		this.createdCommunities = new ArrayList<CommunityDto>();
		this.joinedCommunities = new ArrayList<CommunityDto>();
		this.publicCommunities = new ArrayList<CommunityDto>();
		
		this.createdCommunities.add(test1);
		this.createdCommunities.add(test2);
		this.createdCommunities.add(test3);
	}

	/**
	 * Creates a new Community
	 *
	 */
	public void createCommunity() {
		LOGGER.debug("Creating new Community...");
    	
		// TODO Get UserDto for logged in user...
		if(this.newName != null) {
			
			//CommunityDto newCommunity = this.communityDto.createCommunity(this.newName);
			LOGGER.debug("Created new community with name {}", this.newName);
			
		} else {
			LOGGER.debug("Name is empty, can't create comunity");
		}
	}
}
