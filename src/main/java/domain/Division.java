package domain;

import javax.persistence.*;

@Entity
@Table(name = "Divisions", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Division {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public Division() {
  }

  public Division(String name) {
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
}
