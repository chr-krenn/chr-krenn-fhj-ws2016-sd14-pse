package at.fhj.swd14.pse.news;

import javax.ejb.Remote;
import java.time.Instant;
import java.util.List;

/**
 * This class is intended to provide management and retrieval methods regarding {@link NewsDto}.
 *
 * @author Richard Raumberger
 */
@Remote
public interface NewsService {

    /**
     * Saves or updates the given news and returns the news id.
     *
     * @param news the news to save; must not be null
     * @return the id of the news
     * @throws NullPointerException; if {@code news} is null
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
     * @param instant all news after this instant will be fetched; must not be null
     * @return the fetched news, if none are found, an empty list is returned
     * @throws NullPointerException if the instant is null
     */
    List<NewsDto> findAllSince(Instant instant);

    /**
     * Finds and returns all news which were created by the given author.
     *
     * @param author the author's id
     * @return the fetched news, if none are found, an empty list is returned
     */
    List<NewsDto> findByAuthorId(long author);

}
