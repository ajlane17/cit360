package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SomeObject implements Serializable {
    private int id;
    private String name;
    private List<String> properties;

    public SomeObject(int id, String name, List<String> properties) {
        this.id = id;
        this.name = name;
        this.properties = properties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomeObject that = (SomeObject) o;
        return id == that.id &&
                name.equals(that.name) &&
                properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, properties);
    }
}
