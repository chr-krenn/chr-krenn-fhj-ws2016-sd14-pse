package at.fhj.swd14.pse.news;

import javax.ejb.Remote;
import java.util.Collection;

/**
 * This class is intended to provide management and retrieval methods regarding {@link NewsDto}.
 *
 * @author Richard Raumberger
 */
@Remote
public interface NewsService {

    /**
     * Saves the given news and returns the news id.
     *
     * @param news the news to save; must not be null
     * @return the id of the news
     * @throws NullPointerException if {@code news} is null
     */
    long save(NewsDto news);


    /**
     * Saves the given news and returns the news id.
     *
     * @param news the news to save; must not be null
     * @return the id of the news
     * @throws NullPointerException if {@code news} is null
     */
    long update(NewsDto news);

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
    Collection<NewsDto> findAll();

    /**
     * Finds and returns all online news.
     *
     * @return the news
     */
    Collection<NewsDto> findAllOnline();

}
