package at.fhj.swd14.pse.news;

import javax.ejb.Remote;
import java.time.Instant;
import java.util.List;

@Remote
public interface NewsService {

    /**
     * Saves or updates the given news and returns the news id.
     *
     * @param news the news to save
     * @return the id of the news
     */
    long save(NewsDto news);

    /**
     * Returns the news for the given id.
     *
     * @param id the id of the news
     * @return the news for the given id, or null if none found
     */
    NewsDto find(long id);

    /**
     * Finds and returns all news.
     *
     * @return the news
     */
    List<NewsDto> findAll();

    /**
     * Finds and returns all news since the given date.
     *
     * @return the news
     */
    List<NewsDto> findAllSince(Instant instant);

    /**
     * Finds and returns all news which were created by the given author.
     *
     * @param author the author's id
     * @return the news of the author
     */
    List<NewsDto> findByCreatorId(long author);

}
