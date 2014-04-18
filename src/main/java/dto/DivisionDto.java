package dto;

import java.io.Serializable;

public class DivisionDto implements Serializable {
  private Long id;
  private String name;

  public DivisionDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public DivisionDto(String name) {
    this.name = name;
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
}
