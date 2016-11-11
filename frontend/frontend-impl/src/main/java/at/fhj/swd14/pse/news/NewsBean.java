package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NewsBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4507541276214551604L;
	private NewsDto news;
    private static final Logger LOGGER = LogManager.getLogger(NewsBean.class);

    private UserDto currentUser;
    @EJB(name = "ejb/NewsService")
    private NewsService newsService;

    @EJB(name = "ejb/PersonService")
    private PersonService personService;

    @EJB(name = "ejb/UserService")
    private UserService userService;

    /*
     *  Constructor
     */
    public NewsBean() {
        LOGGER.debug("Create: " + NewsBean.class.getSimpleName());
        this.news = new NewsDto();
    }

    public NewsDto getNews() {
        return news;
    }

    public void setNews(NewsDto news) {
        this.news = news;
    }

    public void addNews()
    {
        if(currentUser == null)
        {
            long userID =  ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
            currentUser = userService.find(userID);
        }
        if(currentUser != null) {
            PersonDto person = personService.findByUser(currentUser);

            if(news.getTitle() != null && news.getMessage() != null) {
                news.setAuthor(person);
                newsService.save(news);
            }
        }
    }

    public void editNews()
    {
    }


    public void setCurrentUser(UserDto currentUser) {
        this.currentUser = currentUser;
    }
}
