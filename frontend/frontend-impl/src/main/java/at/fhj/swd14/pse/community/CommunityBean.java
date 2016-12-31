/**
 *
 */
package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author schoeneg14, purkart
 *
 */
@Named
@SessionScoped
public class CommunityBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(CommunityBean.class);

	@EJB(name = "ejb/CommunityService")
	private transient CommunityService communityService;

	@EJB(name = "ejb/UserService")
	private transient UserService userService;

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
	private List<CommunityDto> allCommunities;
	private List<CommunityDto> otherCommunities;
	private List<CommunityDto> communitiesToActivate;

	public List<CommunityDto> getCommunitiesToActivate() {
		return communitiesToActivate;
	}

	public void setCommunitiesToActivate(List<CommunityDto> communitiesToActivate) {
		this.communitiesToActivate = communitiesToActivate;
	}

	public List<CommunityDto> getOtherCommunities() {
		return otherCommunities;
	}

	public void setOtherCommunities(List<CommunityDto> otherCommunities) {
		this.otherCommunities = otherCommunities;
	}

	private UserDto loggedInUser;

	private List<UserDto> communityMembers;

	public List<UserDto> getCommunityMembers() {
		return communityMembers;
	}
	
	private List<UserDto> communityRequests;

	public List<UserDto> getCommunityRequests() {
		return communityRequests;
	}

	/**
	 * Initializes the bean for the view
	 */
	@PostConstruct
	public void init() {
		LOGGER.error("Initialising the CommunityBean");

		// Get logged in user
		long currentUserId = ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance()
				.getExternalContext().getUserPrincipal()).getUserId();

		this.loggedInUser = userService.find(currentUserId);

		refresh();
	}

	private void refresh() {
		this.createdCommunities = new ArrayList<>();
		this.joinedCommunities = new ArrayList<>();
		this.publicCommunities = new ArrayList<>();
		this.allCommunities = new ArrayList<>();
		this.otherCommunities = new ArrayList<>();
		this.communitiesToActivate = new ArrayList<>();
		this.communitiesToActivate.clear();

		this.createdCommunities = communityService.findByAuthorId(this.loggedInUser.getId());
		this.joinedCommunities = communityService.findUserRelated(this.loggedInUser.getId());
		this.publicCommunities = communityService.findUserRelated(this.loggedInUser.getId());
		this.allCommunities = communityService.findAll();

		ArrayList<CommunityDto> dummy = new ArrayList<>();
		allCommunities.forEach(dto -> {
			if (!Objects.equals(dto.getAuthor().getId(), this.loggedInUser.getId())) {
				dummy.add(dto);
			}

			if (!dto.getActiveState()) {
				this.communitiesToActivate.add(dto);
			}
		});

		dummy.forEach(dto -> {
			if (!dto.getAllowedUsers().contains(this.loggedInUser)) {
				this.otherCommunities.add(dto);
			}
		});

	}

	/**
	 * Creates a new Community
	 *
	 */
	public void createCommunity() {
		LOGGER.debug("Creating new Community...");

		if (this.newName != null) {
			CommunityDto community = new CommunityDto();
			community.setAuthor(loggedInUser);
			community.setPublicState(newPublicState);
			community.setName(newName);

			// If it is a private community, set it to active..
			if (!newPublicState) {
				community.setActiveState(true);
			} else {
				community.setActiveState(false);
			}

			community.setDescription(newDescription);
			long generatedId = this.communityService.save(community);

			community = communityService.find(generatedId);

			LOGGER.error("created new community author: {} desc: {} name: {} public: {}", this.loggedInUser,
					this.newDescription, this.newName, this.newPublicState);

			refresh();

		} else {
			LOGGER.debug("Name is empty, can't create comunity");
		}
	}

	/**
	 * activate a community
	 *
	 */
	public void activate(CommunityDto community) {
		if (community != null) {
			LOGGER.debug("activate Comunity: {}", community.getName());
			community.setActiveState(true);

			this.communityService.save(community);

			LOGGER.error("activated community {}", community.getName());

			refresh();
		}
	}

	/**
	 * deactivate a community
	 *
	 */
	public void deactivate(CommunityDto community) {
		if (community != null) {
			LOGGER.debug("deactivate Comunity: {}", community.getName());
			community.setActiveState(false);

			this.communityService.save(community);

			LOGGER.error("deactivated community {}", community.getName());

			refresh();
		}
	}

	/**
	 * add the current user to a community
	 *
	 */
	public void join(CommunityDto community) {
		if (community != null) {
			LOGGER.debug("User {} Joining the Comunity: {}", this.loggedInUser, community.getName());
			if (community.getPublicState()) {

				community.addUser(this.loggedInUser);
				LOGGER.error("adding user {} to community {}", this.loggedInUser, community.getName());
			} else {
				community.addPendingUser(this.loggedInUser);
				LOGGER.error("add join request for user {} and community {}", this.loggedInUser, community.getName());
			}

			this.communityService.save(community);

			LOGGER.error("saved user {} to community {}", this.loggedInUser, community.getName());

			refresh();
		}
	}

	/**
	 * leave a community
	 *
	 */
	public void leave(CommunityDto communityDto) {
		if (communityDto != null) {
			LOGGER.debug("User {} leaves the Comunity: {}", this.loggedInUser, communityDto.getName());

			List<UserDto> allowedUsers = communityDto.getAllowedUsers();
			if (allowedUsers.removeIf(user -> Objects.equals(user.getId(), this.loggedInUser.getId()))) {
				communityDto.setAllowedUsers(allowedUsers);
			}

			this.communityService.save(communityDto);

			LOGGER.error("removed user {} from community {}", this.loggedInUser, communityDto.getName());

			refresh();
		}
	}

	/**
	 * accept a user request
	 *
	 */
	public void accept(CommunityDto community) {
		if (community != null) {
			LOGGER.debug("accept User {} for Comunity: {}", this.loggedInUser, community.getName());
			community.addUser(this.loggedInUser);
			community.deletePendingUser(this.loggedInUser.getId());

			this.communityService.save(community);

			LOGGER.error("accepted user {} for community {}", this.loggedInUser, community.getName());

			refresh();
		}
	}

	/**
	 * decline a user request
	 *
	 */
	public void decline(CommunityDto community) {
		if (community != null) {
			LOGGER.debug("decline User {} lfor Comunity: {}", this.loggedInUser, community.getName());
			community.deletePendingUser(this.loggedInUser.getId());

			this.communityService.save(community);

			LOGGER.error("acceptdesclined user {} for community {}", this.loggedInUser, community.getName());

			refresh();
		}
	}

	/**
	 * display a list of users of a community
	 *
	 */
	public void showMembers(CommunityDto community) {

		this.communityMembers = new ArrayList<>();
		if (community != null) {

			LOGGER.debug("request userlist of community: {}", community.getName());
			communityMembers.addAll(community.getAllowedUsers());
		}
	}

	public void kickMember(long communityId, long memberId) {
		LOGGER.debug("request user kick of community: {}", communityId);
		communityService.removeUserFromComunity(communityId, memberId);

	}
	
	/**
	 * display a list of pending users of a community
	 *
	 */
	public void showRequests(CommunityDto community) {

		this.communityRequests = new ArrayList<>();
		if (community != null) {

			LOGGER.debug("request userlist of community: {}", community.getName());
			communityRequests.addAll(community.getPendingUsers());
		}
	}
	
	public void acceptMember(long communityId, long memberId) {
		LOGGER.debug("request user kick of community: {}", communityId);
		communityService.addUserToComunity(communityId, memberId);

	}
}
