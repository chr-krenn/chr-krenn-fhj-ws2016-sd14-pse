package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.news.News;

@Local
@Singleton
public class NewsRepository extends AbstractRepository<News> {
    public NewsRepository() {
        super(News.class);
    }
}
