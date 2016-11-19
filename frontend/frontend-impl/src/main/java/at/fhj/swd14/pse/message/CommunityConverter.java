package at.fhj.swd14.pse.message;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.community.CommunityService;

@Named
@ApplicationScoped
public class CommunityConverter implements Converter {
	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(CommunityConverter.class);
	
	@EJB(name = "ejb/CommunityService")
	private CommunityService communityService;

	public CommunityService getCommunityService() {
		return communityService;
	}

	public void setCommunityService(CommunityService communityService) {
		communityService = communityService;
	}

	/**
	 * Converter Method to get the right Community for the Selectbox
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CommunityConverter.LOGGER.debug("getAsObject for Value: "+value);
		if (value == null) {
			return null;
		}
		Long id = Long.parseLong(value);
		CommunityDto c = null;
		
		if(id > 0) {
			c = communityService.find(id);
		} else {
			c = getDummyCommunityDto(id);
		}
		CommunityConverter.LOGGER.debug("getAsObject returns: "+c);
		return c;
	}

	private CommunityDto getDummyCommunityDto(Long id) {
		CommunityDto c = new CommunityDto(id);
		switch(id.intValue()) {
			case -3:
				c.setName("Alle");
				break;
			case -2:
				c.setName("Private");
				break;
			case -1:
				c.setName("Globale");
				break;
		}
		return c;
	}

	/**
	 * Converter Method to get the right string of a Community for the Selectbox
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		CommunityConverter.LOGGER.debug("getAsString for Value: "+value);
		return value instanceof CommunityDto ? ((CommunityDto) value).getId().toString() : "";
	}
}