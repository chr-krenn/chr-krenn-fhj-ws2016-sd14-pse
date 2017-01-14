package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.security.DatabasePrincipal;
import at.fhj.swd14.pse.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Melanie Schneider
 */
@Named
@SessionScoped
public class CommunityMessageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(CommunityBean.class);

    @EJB(name = "ejb/CommunityService")
    private transient CommunityService communityService;

    @EJB(name = "ejb/UserService")
    private transient UserService userService;

    private List<CommunityDto> joinedCommunities;

    @PostConstruct
    public void init() {
        LOGGER.info("Initialising CommunityBean");

        // Get logged in user
        long currentUserId = ((DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();

        this.joinedCommunities = communityService.findUserRelated(currentUserId);
    }

    public List<CommunityDto> getJoinedCommunities() {
        return joinedCommunities;
    }

    public void setJoinedCommunities(List<CommunityDto> joinedCommunities) {
        this.joinedCommunities = joinedCommunities;
    }

}
