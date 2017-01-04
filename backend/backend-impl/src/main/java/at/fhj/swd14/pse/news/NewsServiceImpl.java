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
            final News converted = NewsConverter.convert(Objects.requireNonNull(news));
            newsRepository.save(converted);
            return converted.getId();
        } catch (Exception e) {
            LOGGER.warn(e);
            throw new NewsServiceException("Failed to save news");
        }
    }

    @Override
    public long update(NewsDto news) {
        try {
            final News converted = NewsConverter.convert(Objects.requireNonNull(news));
            newsRepository.update(converted);
            return converted.getId();
        } catch (Exception e) {
            LOGGER.warn(e);
            throw new NewsServiceException("Failed to update news");
        }
    }

    @Override
    public NewsDto find(long id) {
        try {
            return NewsConverter.convert(newsRepository.find(id));
        } catch (Exception e) {
            LOGGER.info(e);
            throw new NewsServiceException("Failed to find news");
        }
    }

    @Override
    public Collection<NewsDto> findAll() {
        try {
            return NewsConverter.convertToDtoList(newsRepository.findAll());
        } catch (Exception e) {
            LOGGER.info(e);
            throw new NewsServiceException("Failed to find news");
        }
    }

    @Override
    public Collection<NewsDto> findAllOnline() {
        try {
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("onlineDate", ZonedDateTime.now().toInstant());
            return NewsConverter.convertToDtoList(newsRepository.executeNamedQuery(News.FIND_ALL_ONLINE_QUERY, parameters));
        } catch (Exception e) {
            LOGGER.info(e);
            throw new NewsServiceException("Failed to find news");
        }
    }
}
