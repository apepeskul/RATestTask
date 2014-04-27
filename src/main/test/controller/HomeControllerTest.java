import controller.HomeController;
import dto.DivisionDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.DivisionService;
import service.EmployeeService;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class HomeControllerTest extends Assert {

    @Mock
    EmployeeService employeeService;
    @Mock
    DivisionService divisionService;
    @InjectMocks
    HomeController controller;


    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void homeTest() throws Exception {

        ModelAndView testMav = controller.home();
        assertEquals("home", testMav.getViewName());
        assertNotNull(testMav.getModelMap().get("emp"));
        assertNotNull(testMav.getModelMap().get("divisions"));
        verify(divisionService, times(1)).findAllAsMap();


    }

    @Test
    public void testAdmin() throws Exception {
        ModelAndView testMav = controller.admin();
        assertEquals("admin", testMav.getViewName());
        assertEquals(new DivisionDto(), testMav.getModelMap().get("div"));


    }


    @Test
    public void testDivisionForDT() throws Exception {
        controller.divisionForDT();
        verify(divisionService, times(1)).findAll();


    }

    @Test
    public void testGetEmployee() throws Exception {
        controller.getEmployee(1);
        verify(employeeService).getById(1);
    }

    @Test
    public void testGetDivision() throws Exception {
        controller.getDivision(2);
        verify(divisionService).getById(2);

    }

    @Test
    public void testDeleteEmployee() throws Exception {
        controller.deleteEmployee(anyLong());
        verify(employeeService).deleteById(anyLong());

    }

    @Test
    public void testDeleteDivision() throws Exception {
        controller.deleteDivision(anyLong());
        verify(divisionService).deleteById(anyLong());

    }

}
