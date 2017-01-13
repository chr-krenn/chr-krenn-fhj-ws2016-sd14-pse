package at.fhj.swd14.pse.base;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public final class IntegrationTestUtil {

    private static final String JNDI_APP_NAME = "backend-assembly-1.1-SNAPSHOT";
    private static final String JNDI_MODULE_NAME = "backend-impl-1.1-SNAPSHOT";
    private static InitialContext context;

    private IntegrationTestUtil() {
    }

    private static InitialContext initContext() {
        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final String host = System.getProperty("integration.provider.host", "localhost");
        final String port = System.getProperty("integration.provider.port", "8080");

        jndiProperties.put(Context.PROVIDER_URL, "remote://" + host + ":" + port);
        jndiProperties.put("java.naming.factory.initial", org.jboss.naming.remote.client.InitialContextFactory.class.getName());

        jndiProperties.put("endpoint.name", "integration-test-client");
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        jndiProperties.put("remote.connections", "default");
        jndiProperties.put("remote.connection.default.host", host);
        jndiProperties.put("remote.connection.default.port", port);

        if (System.getProperty("integration.provider.username") != null) {
            jndiProperties.put("remote.connection.default.username", System.getProperty("integration.provider.username"));
        }
        if (System.getProperty("integration.provider.password") != null) {
            jndiProperties.put("remote.connection.default.password", System.getProperty("integration.provider.password"));
        }
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        jndiProperties.put("jboss.naming.client.ejb.context", "true");

        final EJBClientConfiguration ejbClientConfiguration = new PropertiesBasedEJBClientConfiguration(jndiProperties);
        final ContextSelector<EJBClientContext> ejbClientContextSelector = new ConfigBasedEJBClientContextSelector(ejbClientConfiguration);
        EJBClientContext.setSelector(ejbClientContextSelector);

        try {
            return new InitialContext(jndiProperties);
        } catch (NamingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static synchronized InitialContext getContext() {
        if (context == null) {
            context = initContext();
        }
        return context;
    }

    public static <T> T getService(Class<T> serviceClass) {
        return getService(buildLookupName(serviceClass));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(String lookupName) {
        try {
            return (T) getContext().lookup(lookupName);
        } catch (NamingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String buildLookupName(Class<?> serviceClass) {
        return "ejb:" + JNDI_APP_NAME + "/" + JNDI_MODULE_NAME + "//" + serviceClass.getSimpleName() + "Impl!" + serviceClass.getName();
    }
}
