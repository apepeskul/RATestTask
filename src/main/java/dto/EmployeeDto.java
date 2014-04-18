package dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeDto implements Serializable {
  private Long id;
  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+$")
  private String firstName;
  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+$")
  private String lastName;
  /* @Pattern(regexp = "\\d{1,15}\\.\\d{1,2}") */
  private Double salary;
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

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
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
