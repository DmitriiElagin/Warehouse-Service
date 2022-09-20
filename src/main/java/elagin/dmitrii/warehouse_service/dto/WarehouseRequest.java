package elagin.dmitrii.warehouse_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class WarehouseRequest {

    private String oldName;

    @Size(min = 2, max = 128)
    @NotBlank
    private String name;

    public WarehouseRequest() {
    }

    public WarehouseRequest(String oldName, String name) {
        this.oldName = oldName;
        this.name = name;
    }

    public WarehouseRequest(String name) {
        this(null, name);
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
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
        WarehouseRequest that = (WarehouseRequest) o;
        return Objects.equals(oldName, that.oldName) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldName, name);
    }
}
