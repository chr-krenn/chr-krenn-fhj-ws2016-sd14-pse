package at.fhj.swd14.pse.news;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;

import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

@Named
@SessionScoped
public class NewsBean implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = -4507541276214551604L;
  private NewsDto news;
  private static final Logger LOGGER = LogManager.getLogger(NewsBean.class);

  private Collection<NewsDto> allNews;
  private Date activationDate;
  private Date terminationDate;
  private Date currentDate;
  private UserDto currentUser;
  @EJB(name = "ejb/NewsService")
  private NewsService newsService;

  @EJB(name = "ejb/PersonService")
  private PersonService personService;

  @EJB(name = "ejb/UserService")
  private UserService userService;
  private String buttonNewsText;

  private final static String NEWS_ADD_TEXT = "News hinzufügen";
  private final static String NEWS_EDIT_TEXT = "News editieren";

  /*
   *  Constructor
   */
  public NewsBean() {
    this.buttonNewsText = NEWS_ADD_TEXT;
    LOGGER.debug("Create: " + NewsBean.class.getSimpleName());
    this.news = new NewsDto();
  }

  @PostConstruct
  public void init() {
    this.allNews = newsService.findAllOnline();

    currentDate = new Date();
    setActivationDate(currentDate);
    setTerminationDate(currentDate);
  }

  public NewsDto getNews() {
    return news;
  }

  public void setNews(NewsDto news) {
    this.news = news;
  }

  public void addNews() {
    if (currentUser == null) {
      long userID = ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
      currentUser = userService.find(userID);
    }
    if (currentUser != null) {
      PersonDto person = personService.findByUser(currentUser);

      if (news.getTitle() != null && news.getMessage() != null) {
        if (terminationDate != null) {
          news.setTermination(terminationDate.toInstant());
        }

        if (activationDate != null) {
          news.setActivation(activationDate.toInstant());
        }
        news.setAuthor(person);

        if (news.getId() != null) {
          newsService.update(news);
        } else {
          newsService.save(news);
        }
        news = new NewsDto();
        reloadAllNews();
        this.buttonNewsText = NEWS_ADD_TEXT;
        updateViewElementById("newsForm");
        RequestContext.getCurrentInstance().update("scroller");
      }
    }
  }

  public void prepareNewsToEdit(NewsDto news) {
    this.news = news;
    this.activationDate = Date.from(news.getActivation());
    this.terminationDate = Date.from(news.getTermination());
    this.buttonNewsText = NEWS_EDIT_TEXT;

    updateViewElementById("newsForm");
  }

  private void updateViewElementById(String Id ) {
    RequestContext.getCurrentInstance().update(Id);
  }


  public void setCurrentUser(UserDto currentUser) {
    this.currentUser = currentUser;
  }

  public Date getTerminationDate() {
    return terminationDate;
  }

  public void setTerminationDate(Date terminationDate) {
    this.terminationDate = terminationDate;
  }

  public Date getActivationDate() {
    return activationDate;
  }

  public void setActivationDate(Date activationDate) {
    this.activationDate = activationDate;
  }

  public Collection<NewsDto> getAllNews() {
    return allNews;
  }

  public void setAllNews(Collection<NewsDto> allNews) {
    this.allNews = allNews;
  }

  public Date getCurrentDate() {
    return currentDate;
  }

  public void setCurrentDate(Date currentDate) {
    this.currentDate = currentDate;
  }

  public void reloadAllNews(){
    this.allNews = newsService.findAllOnline();
  }

  public void setButtonNewsText(String buttonNewsText) {
    this.buttonNewsText = buttonNewsText;
  }

  public String getButtonNewsText() {
    return buttonNewsText;
  }
}
