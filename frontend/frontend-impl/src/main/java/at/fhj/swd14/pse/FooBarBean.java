package at.fhj.swd14.pse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "fooBar")
public class FooBarBean {

    private static final Logger LOGGER = LogManager.getLogger(FooBarBean.class);

    @EJB(name = "ejb/FooBar")
    private FooBar remoteFooBar;

    private String name;

    public void callRemoteFoo() {
        LOGGER.info("About to call remote foo()");
        remoteFooBar.foo();
        LOGGER.info("Called remote foo()");
    }

    public int callRemoteBar(ActionEvent event) {
        LOGGER.info("About to call remote bar() with name '{}'", name);
        int i = remoteFooBar.bar(new Bar(name));
        LOGGER.info("Called remote bar()");
        return i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
