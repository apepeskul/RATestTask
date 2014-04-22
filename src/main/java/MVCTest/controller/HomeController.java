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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    ModelAndView model = new ModelAndView("home");

    model.addObject("divisions", divisionService.findAllAsMap());
    EmployeeDto employeeDto = new EmployeeDto();
    model.addObject("emp", employeeDto);


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

  @RequestMapping(value = "rest/emp/{id}", produces = { "application/json; charset=UTF-8" })
  @ResponseBody
  public String getEmployee(@PathVariable("id") int id, Model model) {
    Gson gson = new Gson();
    EmployeeDto editEmp = employeeService.getById(id);
    String json = gson.toJson(editEmp);

    return json;
  }

  @RequestMapping(value = "/emp/delete/{id}")
  public String deleteEmployee(@PathVariable("id") Long id) {
    employeeService.deleteById(id);
    return "redirect:/";
  }

   @RequestMapping (value = "/emp/update")
    public String updateEmployee(@Valid EmployeeDto employeeDto, BindingResult br){
       return null;
   }

}
