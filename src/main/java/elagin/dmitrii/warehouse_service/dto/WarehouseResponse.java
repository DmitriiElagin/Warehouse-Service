package elagin.dmitrii.warehouse_service.dto;

import java.util.Objects;

public class WarehouseResponse {
    private String name;

    public WarehouseResponse() {
    }

    public WarehouseResponse(String name) {
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
        WarehouseResponse warehouse = (WarehouseResponse) o;
        return name.equals(warehouse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
