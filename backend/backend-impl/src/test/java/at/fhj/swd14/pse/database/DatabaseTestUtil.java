package at.fhj.swd14.pse.database;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseTestUtil {

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SEP_TEST",
                buildEntityManagerFactoryProperties()); // Properties must be passed to override default persistence.xml
        return factory.createEntityManager();
    }

    private static Properties buildEntityManagerFactoryProperties() {
        final Properties properties = new Properties();
        properties.putAll(System.getenv());
        properties.putAll(System.getProperties());
        return properties;
    }

}
