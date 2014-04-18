package service;

import java.util.List;

import MVCTest.controller.DataTableParamModel;
import dto.EmployeeDto;

public interface EmployeeService {

  List<EmployeeDto> search(String query);

  void addNew(EmployeeDto emp);

  void update(EmployeeDto employeeDto);

  void deleteById(Long id);

  EmployeeDto getById(int id);

  List<EmployeeDto> findAll();

  List<EmployeeDto> findForDatatables(DataTableParamModel paramModel);

  int getAllCount();

  int getCountForQuery(DataTableParamModel dt);
}
