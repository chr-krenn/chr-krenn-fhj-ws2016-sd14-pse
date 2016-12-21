package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.news.News;
import at.fhj.swd14.pse.news.NewsRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Local
@Singleton
public class NewsRepositoryImpl
        extends AbstractRepository<News>
        implements NewsRepository {
    public NewsRepositoryImpl() {
        super(News.class);
    }
}
