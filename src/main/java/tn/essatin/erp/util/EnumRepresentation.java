package tn.essatin.erp.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class EnumRepresentation {
    Integer id;
    String name;
    String value;

    public EnumRepresentation(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public EnumRepresentation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EnumRepresentation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnumRepresentation)) return false;
        EnumRepresentation that = (EnumRepresentation) o;
        return id.equals(that.id) && name.equals(that.name) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }
}
