package at.fhj.swd14.pse.news;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Stateless
public class NewsServiceImpl implements NewsService {

    private static final Logger LOGGER = LogManager.getLogger(NewsServiceImpl.class);

    @EJB
    private NewsRepository newsRepository;

    @Override
    public long save(NewsDto news) {
        try {
            LOGGER.trace("Saving news");
            final News converted = NewsConverter.convert(Objects.requireNonNull(news));
            newsRepository.save(converted);
            return converted.getId();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NewsServiceException("Could not save news");
        }
    }

    @Override
    public long update(NewsDto news) {
        try {
            LOGGER.trace("Updating news");
            final News converted = NewsConverter.convert(Objects.requireNonNull(news));
            newsRepository.update(converted);
            return converted.getId();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NewsServiceException("Could not update news");
        }
    }

    @Override
    public NewsDto find(long id) {
        try {
            LOGGER.trace("Finding news with id {}", id);
            return NewsConverter.convert(newsRepository.find(id));
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            throw new NewsServiceException("Could not find news");
        }
    }

    @Override
    public Collection<NewsDto> findAll() {
        try {
            LOGGER.trace("Finding all news");
            return NewsConverter.convertToDtoList(newsRepository.findAll());
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            throw new NewsServiceException("Could not find any news");
        }
    }

    @Override
    public Collection<NewsDto> findAllOnline() {
        try {
            LOGGER.trace("Finding all online news");
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("onlineDate", ZonedDateTime.now().toInstant());
            return NewsConverter.convertToDtoList(newsRepository.executeNamedQuery(News.FIND_ALL_ONLINE_QUERY, parameters));
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            throw new NewsServiceException("Could not find online news");
        }
    }
}
