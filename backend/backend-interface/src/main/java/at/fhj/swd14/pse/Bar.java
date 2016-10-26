package at.fhj.swd14.pse;

import java.io.Serializable;

public class Bar implements Serializable {

    private String name;

    public Bar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bar bar = (Bar) o;

        return name != null ? name.equals(bar.name) : bar.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
