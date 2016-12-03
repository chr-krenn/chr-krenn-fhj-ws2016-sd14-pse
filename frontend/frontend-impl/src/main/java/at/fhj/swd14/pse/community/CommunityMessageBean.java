/**
 * @author Melanie Schneider
 */
package at.fhj.swd14.pse.community;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

@Named
@SessionScoped

public class CommunityMessageBean implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(CommunityBean.class);

	@EJB(name = "ejb/CommunityService")
	private CommunityService communityService;
	
	@EJB(name = "ejb/UserService")
	private UserService userService;

	

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
		
		this.createdCommunities = new ArrayList<CommunityDto>();
		this.joinedCommunities = new ArrayList<CommunityDto>();
		this.publicCommunities = new ArrayList<CommunityDto>();
		
		this.createdCommunities = communityService.findByAuthorId(currentUserId);
		this.joinedCommunities = communityService.findUserRelated(currentUserId);
		this.publicCommunities = communityService.findUserRelated(currentUserId);
		
	}

//	/**
//	 * Creates a new Community
//	 *
//	 */
//	public void createCommunity() {
//		LOGGER.debug("Creating new Community...");
//    	
//		// TODO Get UserDto for logged in user...
//		if(this.newName != null) {
//			
//			//CommunityDto newCommunity = this.communityDto.createCommunity(this.newName);
//			LOGGER.debug("Created new community with name {}", this.newName);
//			
//		} else {
//			LOGGER.debug("Name is empty, can't create comunity");
//		}
//	}
	
	private List<CommunityDto> listMyCommunities =new ArrayList<CommunityDto>();
	public List<CommunityDto> getListMyCommunities() {
		listMyCommunities.addAll(joinedCommunities);
		listMyCommunities.addAll(joinedCommunities);
		return  listMyCommunities;
	    }
	
	public void setListMyCommunities(){
		
	}
	     
	    public void update() {
	        addMessage("Success", "Data updated");
	    }
	     
	    public void delete() {
	        addMessage("Success", "Data deleted");
	    }
	     
	    public void addMessage(String summary, String detail) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    
	  
		
		private MenuModel createMenuModel;
		public MenuModel getCreateMenuModel(){
			createMenuModel = new DefaultMenuModel();
	    		    		
	    		DefaultSubMenu firstSubmenu = new DefaultSubMenu("My Communities");
	    		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Public Communities");
	    		
	    		for(CommunityDto comm : this.getListMyCommunities())
	    		{
	    			DefaultMenuItem item = new DefaultMenuItem(comm.getName());
	    			firstSubmenu.addElement(item);
	    			item.setIcon("ui-icon-extlink");
	    		}
	    		
	    		for(CommunityDto comm : this.getPublicCommunities())
	    		{
	    			DefaultMenuItem item = new DefaultMenuItem(comm.getName());
	    			secondSubmenu.addElement(item);
	    			item.setIcon("ui-icon-extlink");
	    		}
	    		
//	    		item.setUrl("http://www.primefaces.org");
//	    		item.setIcon("ui-icon-home");
//	    		
	    		createMenuModel.addElement(firstSubmenu);
	    		createMenuModel.addElement(secondSubmenu);
	    		
			return createMenuModel;
	    	
	    }
		
		public void setCreateMenuModel(){
			
		}
	
}
