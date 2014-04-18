package service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.EmployeeService;
import MVCTest.controller.DataTableParamModel;
import dao.DivisionDao;
import dao.EmployeeDao;
import domain.Employee;
import dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private EmployeeDao employeeDao;
  @Autowired
  private DivisionDao divisionDao;

  @Override
  public List<EmployeeDto> search(String query) {
    query = query.replaceAll("\\*", "\\%").replaceAll("\\?", "\\_");
    return makeDtos(employeeDao.searchByName(query));

  }

  @Override
  public void addNew(EmployeeDto empDto) {
    Employee employee = dtoToDomain(empDto);
    employeeDao.store(employee);
  }

  @Override
  public void update(EmployeeDto employeeDto) {
    employeeDao.update(dtoToDomain(employeeDto));
  }

  @Override
  public void deleteById(Long id) {
    employeeDao.deleteById(id);

  }

  @Override
  public EmployeeDto getById(int id) {
    return makeDto(employeeDao.getById(Long.valueOf(id)));
  }

  @Override
  public List<EmployeeDto> findAll() {
    return makeDtos(employeeDao.findAll());
  }

  @Override
  public List<EmployeeDto> findForDatatables(DataTableParamModel paramModel) {
    String sSearch = "%" + paramModel.sSearch.replaceAll("\\*", "\\%").replaceAll("\\?", "\\_") + "%";
    return makeDtos(employeeDao.findPagedAndSorted(sSearch, paramModel.iDisplayLength, paramModel.iDisplayStart, paramModel.iSortColumnIndex,
        paramModel.sSortDirection));
  }

  @Override
  public int getAllCount() {
    return employeeDao.getAllCount();
  }

  @Override
  public int getCountForQuery(DataTableParamModel paramModel) {
    String sSearch = "%" + paramModel.sSearch.replaceAll("\\*", "\\%").replaceAll("\\?", "\\_") + "%";
    return employeeDao.getCountForQuery(sSearch);
  }

  private Employee dtoToDomain(EmployeeDto empDto) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Employee tempEmp = new Employee();
    try {
      tempEmp.setBirthDate(new java.sql.Date(df.parse(empDto.getBirthDate()).getTime()));
    } catch(ParseException e) {
      e.printStackTrace();
    }
    tempEmp.setLastName(empDto.getLastName());
    tempEmp.setFirstName(empDto.getFirstName());
    tempEmp.setId(empDto.getId());
    tempEmp.setActive(empDto.getActive());
    tempEmp.setSalary(empDto.getSalary());
    tempEmp.setDivision(divisionDao.getById(Long.valueOf(empDto.getDivision())));

    return tempEmp;

  }

  private List<EmployeeDto> makeDtos(List<Employee> empList) {
    List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
    for(Employee employee : empList) {
      empDtoList.add(makeDto(employee));
    }
    return empDtoList;
  }

  private EmployeeDto makeDto(Employee employee) {
    EmployeeDto empDto = new EmployeeDto();
    empDto.setActive(employee.getActive() != null ? employee.getActive() : false);
    empDto.setBirthDate(employee.getBirthDate() != null ? employee.getBirthDate().toString() : "");
    empDto.setDivision(employee.getDivision() != null ? employee.getDivision().getId().intValue() : 0);
    empDto.setFirstName(employee.getFirstName() != null ? employee.getFirstName() : "");
    empDto.setLastName(employee.getLastName() != null ? employee.getLastName() : "");
    empDto.setId(employee.getId());
    empDto.setSalary(employee.getSalary() != null ? employee.getSalary() : 0);
    return empDto;

  }

}
