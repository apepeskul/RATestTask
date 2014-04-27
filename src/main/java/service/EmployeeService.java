package service;

import controller.DataTableParamModel;
import dto.DataTablesDto;
import dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {



    void update(EmployeeDto employeeDto);

    void deleteById(Long id);

    EmployeeDto getById(int id);



    List<DataTablesDto> findForDatatables(DataTableParamModel paramModel);

    int getAllCount();

    int getCountForQuery(DataTableParamModel dt);
}
