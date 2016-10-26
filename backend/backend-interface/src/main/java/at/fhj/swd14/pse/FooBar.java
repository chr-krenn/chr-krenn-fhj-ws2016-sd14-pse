package at.fhj.swd14.pse;

import javax.ejb.Remote;

@Remote
public interface FooBar {
    void foo();

    int bar(Bar bar);
}
