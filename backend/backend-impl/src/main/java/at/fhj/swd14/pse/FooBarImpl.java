package at.fhj.swd14.pse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;

@Stateless
public class FooBarImpl implements FooBar {

    private static final Logger LOGGER = LogManager.getLogger(FooBarImpl.class);

    @Override

    public void foo() {
        LOGGER.info("Foo called!");
    }

    @Override
    public int bar(Bar bar) {
        LOGGER.info("Bar called with {}!", bar);

        return 0;
    }
}
