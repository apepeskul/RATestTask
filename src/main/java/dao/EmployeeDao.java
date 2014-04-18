package dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domain.Employee;

@Transactional
public interface EmployeeDao {

  public void store(Employee emp);

  public void delete(Employee emp);

  public void deleteById(Long id);

  public void update(Employee emp);

  public Employee getById(Long id);

  public void updateById(Long id);

  public List<Employee> searchByName(String query);

  public List<Employee> findAll();

  public List<Employee> findPagedAndSorted(String sSearch, Integer pageSize, Integer startEntry, int sortColumnIndex, String sortDirection);

  public int getAllCount();

  int getCountForQuery(String sSearch);
}
