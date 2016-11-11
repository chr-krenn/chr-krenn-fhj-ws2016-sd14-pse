/**
 * 
 */
package at.fhj.swd14.pse.community;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@Stateful
@ViewScoped
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
	
	private CommunityDto communityDto;
	private String newName;
	private String newDescription;
	private boolean newPublicState;
	private List<CommunityDto> createdCommunities;
	private List<CommunityDto> joinedCommunities;
	private List<CommunityDto> publicCommunities;
	
	/**
	 * Initializes the bean for the view
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("Initialising the CommunityBean");
		long id = 1234;
		this.createdCommunities = communityService.findByAuthorId(id);
		this.joinedCommunities = communityService.findUserRelated(id);
		this.publicCommunities = communityService.findUserRelated(id);
	}

	/**
	 * Creates a new Community
	 *
	 */
	public void createCommunity() {
		LOGGER.debug("Creating new Community...");
    	
		// TODO Get UserDto for logged in user...
		if(this.newName != null) {
			/* TODO
			CommunityDto newCommunity = this.communityDto.createCommunity(this.newName);
			LOGGER.debug("Created new community with ID {}", newCommunity.getId());
			*/
		} else {
			LOGGER.warn("Name is empty, can't create comunity");
		}
	}
}
