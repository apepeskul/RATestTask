package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class EmployeeDto implements Serializable {
    private Long id;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters allowed")
    private String firstName;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters allowed")
    private String lastName;
    @Pattern(regexp = "^[1-9][0-9]{0,15}(?:.\\d{1,2})?$", message = "Only digits allowed in %15.2f format")
    private String salary;
    @Pattern(regexp = "^((?:19|20)\\d\\d)[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$", message = "Incorrect date format, use yyyy-mm-dd")
    private String birthDate;
    private Boolean active;
    private int division;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", salary=" + salary + ", birthDate='"
                + birthDate + '\'' + ", active=" + active + ", division='" + division + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDto)) return false;

        EmployeeDto that = (EmployeeDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
