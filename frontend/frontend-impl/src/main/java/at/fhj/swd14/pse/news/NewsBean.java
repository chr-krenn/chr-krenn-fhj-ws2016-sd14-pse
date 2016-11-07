package at.fhj.swd14.pse.news;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NewsBean implements Serializable {

    private NewsDto news;
    private static final Logger LOGGER = LogManager.getLogger(NewsBean.class);

    @EJB(name = "ejb/NewsService")
    private NewsService newsService;

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
        if(news.getTitle() != null && news.getMessage() != null) {
            newsService.save(news);
        }
    }

    public void editNews()
    {
    }
}
