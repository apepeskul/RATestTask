package dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class DivisionDto implements Serializable {
    private Long id;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Must contain only letters")
    private String name;

    public DivisionDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DivisionDto(String name) {
        this.name = name;
    }

    public DivisionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivisionDto)) return false;

        DivisionDto that = (DivisionDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
