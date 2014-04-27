package service.impl;

import controller.DataTableParamModel;
import dao.DivisionDao;
import dao.EmployeeDao;
import domain.Employee;
import dto.DataTablesDto;
import dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EmployeeService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DivisionDao divisionDao;

    @Override
    public void update(EmployeeDto employeeDto) {
        employeeDto.setFirstName(capitalizeAndTrim(employeeDto.getFirstName()));
        employeeDto.setLastName(capitalizeAndTrim(employeeDto.getLastName()));
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
    public List<DataTablesDto> findForDatatables(DataTableParamModel paramModel) {
        String sSearch = paramModel.sSearch.replaceAll("\\*", "\\%").replaceAll("\\?", "\\_") + "%";
        if (sSearch.equals("")) {
            sSearch = "%";
        }

        String sortColumn;
        switch (paramModel.iSortColumnIndex) {
            case 0:
                sortColumn = "e.id";
                break;
            case 1:
                sortColumn = "e.firstName";
                break;
            case 2:
                sortColumn = "e.lastName";
                break;
            case 3:
                sortColumn = "e.salary";
                break;
            case 4:
                sortColumn = "e.birthDate";
                break;
            case 5:
                sortColumn = "e.active";
                break;
            case 6:
                sortColumn = "e.division.name";
                break;
            default:
                sortColumn = "e.id";
                break;
        }
        return employeeDao.findPagedAndSorted(sSearch, paramModel.iDisplayLength, paramModel.iDisplayStart, sortColumn, paramModel.sSortDirection);
    }

    @Override
    public int getAllCount() {
        return employeeDao.getAllCount();
    }

    @Override
    public int getCountForQuery(DataTableParamModel paramModel) {
        String sSearch = paramModel.sSearch.replaceAll("\\*", "\\%").replaceAll("\\?", "\\_") + "%";
        return employeeDao.getCountForQuery(sSearch);
    }

    private Employee dtoToDomain(EmployeeDto empDto) throws NullPointerException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Employee tempEmp = new Employee();
        try {
            tempEmp.setBirthDate(new java.sql.Date(df.parse(empDto.getBirthDate()).getTime()));
        } catch (ParseException e) {

            e.printStackTrace();
        }
        tempEmp.setLastName(empDto.getLastName());
        tempEmp.setFirstName(empDto.getFirstName());
        tempEmp.setId(empDto.getId());
        tempEmp.setActive(empDto.getActive());
        tempEmp.setSalary(empDto.getSalary());
        tempEmp.setDivision(divisionDao.getById((long) empDto.getDivision()));

        return tempEmp;

    }

    private List<EmployeeDto> makeDtos(List<Employee> empList) {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        for (Employee employee : empList) {
            empDtoList.add(makeDto(employee));
        }
        return empDtoList;
    }

    private EmployeeDto makeDto(Employee employee) {
        DataTablesDto empDto = new DataTablesDto();
        empDto.setActive(employee.getActive() != null ? employee.getActive() : false);
        empDto.setBirthDate(employee.getBirthDate() != null ? employee.getBirthDate().toString() : "");
        empDto.setDivision(employee.getDivision() != null ? employee.getDivision().getId().intValue() : 0);
        empDto.setFirstName(employee.getFirstName() != null ? employee.getFirstName() : "");
        empDto.setLastName(employee.getLastName() != null ? employee.getLastName() : "");
        empDto.setId(employee.getId() != null ? employee.getId() : 0);
        empDto.setSalary(employee.getSalary() != null ? employee.getSalary() : "");
        empDto.setDivisionName(employee.getDivision() != null ? employee.getDivision().getName() : "");

        return empDto;

    }

    private String capitalizeAndTrim(String tempString) {
        tempString = tempString.trim();
        tempString = tempString.substring(0, 1).toUpperCase() + tempString.substring(1).toLowerCase();
        return tempString;
    }

}
