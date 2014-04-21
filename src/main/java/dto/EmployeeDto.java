package dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeDto implements Serializable {
  private Long id;
  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters allowed")
  private String firstName;
  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters allowed")
  private String lastName;
  @Pattern(regexp = "^\\d{0,15}\\.\\d{0,2}$", message = "Only digits allowed in %15.2f format")
  private String salary;
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
}
