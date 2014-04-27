package dao;

import domain.Employee;
import dto.DataTablesDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EmployeeDao {

    public void store(Employee emp);


    public void deleteById(Long id);

    public void update(Employee emp);

    public Employee getById(Long id);


    public List<Employee> findAll();

    public List<DataTablesDto> findPagedAndSorted(String sSearch, Integer pageSize, Integer startEntry, String sortColumnIndex, String sortDirection);

    public int getAllCount();

    int getCountForQuery(String sSearch);
}
