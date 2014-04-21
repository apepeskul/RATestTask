package MVCTest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.DivisionService;
import service.EmployeeService;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import dto.EmployeeDto;

@Controller
public class HomeController {

  @Autowired
  EmployeeService employeeService;
  @Autowired
  DivisionService divisionService;

  @RequestMapping(value = "/")
  public ModelAndView test(HttpServletResponse response) throws IOException {

    /* Division div = new Division(); div.setName("TestDivision"); divDao.store(div); Employee emp = new Employee();
     * emp.setFirstName("Alex"); emp.setLastName("asdasd"); emp.setDivision(div); emp.setBirthDate(new Date(100, 1, 30)); dao.store(emp);
     * 
     * Employee emp2 = new Employee(); emp2.setFirstName("Alex2"); emp2.setLastName("Pepeskul"); emp2.setDivision(div);
     * emp2.setBirthDate(new Date(87, 17, 17)); dao.store(emp2);
     * 
     * // dao.searchByName("ale?");
     * 
     * Employee emp = new Employee(); emp.setFirstName("Alex"); emp.setLastName("asdasd"); emp.setBirthDate(new Date(100, 1, 30));
     * service.addNew(); */

    /* EmployeeDto employeeDto = new EmployeeDto(); employeeDto.setActive(true); employeeDto.setSalary(100D);
     * employeeDto.setFirstName("Oleg"); employeeDto.setLastName("Ivanihin"); employeeDto.setBirthDate("1990-03-11");
     * employeeDto.setDivision("Buhi"); employeeService.addNew(employeeDto); */

    /* List<DivisionDto> divisionDtos = divisionService.findAll(); HashMap map = new HashMap(); for (DivisionDto dto : divisionDtos){
     * map.put(dto.getId(), dto.getName()); } */

    ModelAndView model = new ModelAndView("home");
    Map actives = new HashMap();
    actives.put(0, false);
    actives.put(1, true);
    model.addObject("divisions", divisionService.findAllAsMap());
    EmployeeDto employeeDto = new EmployeeDto();
    model.addObject("emp", employeeDto);
    model.addObject("actives", actives);
    // model.addObject();

    // System.out.println(employeeService.search("Ale?"));

    return model;

  }

  @RequestMapping(value = "/addEmp")
  public ModelAndView addEmployee(ModelAndView mav, @ModelAttribute("emp") @Valid EmployeeDto employeeDto, BindingResult br) {
    mav.setViewName("home");
    mav.addObject("divisions", divisionService.findAllAsMap());
    if(br.hasErrors()) {
      return mav;
    }
    employeeService.addNew(employeeDto);

    employeeDto = new EmployeeDto();

    mav.addObject("emp", employeeDto);
    mav.setViewName("redirect:/");
    return mav;

  }

  @RequestMapping(value = "/datatables" /* params = "data", headers = "Accept=application/json" */, produces = { "application/json; charset=UTF-8" })
  @ResponseBody
  public String data(HttpServletRequest request, HttpServletResponse response) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json; charset=utf-8");

    DataTableParamModel dt = DataTablesParamUtility.getParameters(request);

    int iTotalRecords; // total number of records (unfiltered)
    int iTotalDisplayRecords;// value will be set when code filters companies by keyword

    List<EmployeeDto> result = employeeService.findForDatatables(dt);
    Gson gson = new Gson();
    JsonObject jsonResponse = new JsonObject();
    try {

      jsonResponse.addProperty("sEcho", dt.sEcho);
      jsonResponse.addProperty("iTotalRecords", employeeService.getAllCount());
      jsonResponse.addProperty("iTotalDisplayRecords", employeeService.getCountForQuery(dt));
      jsonResponse.add("aaData", gson.toJsonTree(result));

    } catch(JsonIOException e) {
      e.printStackTrace();

    }
    return jsonResponse.toString();
  }

}
