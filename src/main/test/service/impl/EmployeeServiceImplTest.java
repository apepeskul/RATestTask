package service.impl;

import controller.DataTableParamModel;
import dao.DivisionDao;
import dao.EmployeeDao;
import domain.Employee;
import dto.EmployeeDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;

import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest extends Assert {
    @Mock
    EmployeeDao employeeDao;
    @Mock
    DivisionDao divisionDao;
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @DataProvider
    public Object[][] testDto() {
        Object[][] result = null;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("  test");
        employeeDto.setLastName("     test2    ");
        employeeDto.setBirthDate("1999-01-01");
        employeeDto.setActive(false);
        employeeDto.setSalary("100");
        employeeDto.setDivision(1);
        result = new Object[][]{{employeeDto}};
        return result;
    }

    @DataProvider
    public Object[][] testDto2() {
        Object[][] result;
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setFirstName("  test");
        employeeDto2.setLastName("     test2    ");
        employeeDto2.setBirthDate("1999/01/01");
        employeeDto2.setActive(false);
        employeeDto2.setSalary("100");
        employeeDto2.setDivision(1);
        result = new Object[][]{{employeeDto2}};
        return result;
    }

    @DataProvider
    public Object[][] testParamModel() {
        DataTableParamModel paramModel = new DataTableParamModel();
        paramModel.sSortDirection = "asc";
        paramModel.iDisplayLength = 5;
        paramModel.iDisplayStart = 10;
        return new Object[][]{{paramModel}};
    }


    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(employeeDao.getById(anyLong())).thenReturn(new Employee());
        when(divisionDao.getById(0L)).thenThrow(NullPointerException.class);

    }

    @Test(dataProvider = "testDto")
    public void testUpdate(EmployeeDto employeeDto) throws Exception {
        employeeService.update(employeeDto);
        assertEquals("Test", employeeDto.getFirstName());
        assertEquals("Test2", employeeDto.getLastName());
        verify(employeeDao, times(1)).update(any(Employee.class));
    }

    @Test(dataProvider = "testDto2")
    public void testUpdate2(EmployeeDto employeeDto) {
        try {
            employeeService.update(employeeDto);
        } catch (Exception e) {
            assertEquals(e.getClass(), ParseException.class);
        }
    }

    @Test
    public void testDeleteById() throws Exception {

        employeeService.deleteById(anyLong());
        verify(employeeDao).deleteById(anyLong());

    }

    @Test
    public void testGetById() throws Exception {
        employeeService.getById(0);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setLastName("");
        employeeDto.setFirstName("");
        employeeDto.setSalary("");
        employeeDto.setActive(false);
        employeeDto.setBirthDate("");
        assertEquals(employeeDto, employeeService.getById(anyInt()));
        verify(employeeDao, times(2)).getById(anyLong());

    }

    @Test(dataProvider = "testParamModel")
    public void testFindForDatatables(DataTableParamModel paramModel) throws Exception {
        paramModel.sSearch = "aAAAA";
        paramModel.iSortColumnIndex = 2;
        employeeService.findForDatatables(paramModel);
        verify(employeeDao).findPagedAndSorted("aAAAA%", 5, 10, "e.lastName", "asc");
        paramModel.sSortDirection = "desc";
        paramModel.iSortColumnIndex = 4;
        paramModel.sSearch = "?qaz*wsx";
        employeeService.findForDatatables(paramModel);
        verify(employeeDao).findPagedAndSorted("_qaz%wsx%", 5, 10, "e.birthDate", "desc");


    }

    @Test
    public void testGetAllCount() throws Exception {
        employeeService.getAllCount();
        verify(employeeDao, times(1)).getAllCount();

    }

    @Test(dataProvider = "testParamModel")
    public void testGetCountForQuery(DataTableParamModel paramModel) throws Exception {
        paramModel.sSearch = "*aaa";
        employeeService.getCountForQuery(paramModel);
        verify(employeeDao, times(1)).getCountForQuery("%aaa%");

    }
}
