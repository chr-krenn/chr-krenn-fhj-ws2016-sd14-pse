/**
 * 
 */
package at.fhj.swd14.pse.community;

import java.io.Serializable;

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
 * @author schoeneg14
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

}
