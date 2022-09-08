package elagin.dmitrii.warehouse_service.entities;

import jakarta.validation.constraints.Size;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Warehouse {
    @Id
    @Size(min = 2, max = 128)
    private String name;

    public Warehouse() {
    }

    public Warehouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return name.equals(warehouse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
