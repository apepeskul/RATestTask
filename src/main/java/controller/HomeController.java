package controller;

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
import org.springframework.validation.FieldError;
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

import dto.DataTablesDto;
import dto.DivisionDto;
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

  @RequestMapping(value = "/admin")
  public ModelAndView admin(HttpServletResponse response) throws IOException {

    ModelAndView model = new ModelAndView("admin");

    model.addObject("div", new DivisionDto());
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

    List<DataTablesDto> result = employeeService.findForDatatables(dt);
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

  @RequestMapping(value = "/datatables/div", produces = { "application/json; charset=UTF-8" })
  public @ResponseBody
  String divisionForDT(HttpServletRequest request, HttpServletResponse response) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json; charset=utf-8");
    List<DivisionDto> divList = divisionService.findAll();
    Gson gson = new Gson();
    JsonObject jsonResponse = new JsonObject();
    jsonResponse.add("aaData", gson.toJsonTree(divList));
    return jsonResponse.toString();

  }

  @RequestMapping(value = "/emp/{id}", produces = { "application/json; charset=UTF-8" })
  @ResponseBody
  public String getEmployee(@PathVariable("id") int id, Model model) {
    Gson gson = new Gson();
    EmployeeDto editEmp = employeeService.getById(id);
    String json = gson.toJson(editEmp);

    return json;
  }

  @RequestMapping(value = "/div/{id}", produces = { "application/json; charset=UTF-8" })
  @ResponseBody
  public String getDivision(@PathVariable("id") int id, Model model) {
    Gson gson = new Gson();
    DivisionDto divisionDto = divisionService.getById(id);
    String json = gson.toJson(divisionDto);

    return json;
  }

  @RequestMapping(value = "/emp/delete/{id}")
  public String deleteEmployee(@PathVariable("id") Long id) {
    employeeService.deleteById(id);
    return "redirect:/";
  }

  @RequestMapping(value = "/div/delete/{id}")
  public String deleteDivision(@PathVariable("id") Long id) {
    divisionService.deleteById(id);
    return "redirect:/admin";
  }

  @RequestMapping(value = "/div/update", produces = { "application/json; charset=UTF-8" })
  public @ResponseBody
  String updateDivision(@Valid DivisionDto divisionDto, BindingResult bindingResult) {
    UserJsonResponse userJsonResponse = new UserJsonResponse();
    if(!bindingResult.hasErrors()) {
      divisionService.update(divisionDto);
    } else {
      Map<String, String> errors = new HashMap<String, String>();
      List<FieldError> fieldErrors = bindingResult.getFieldErrors();
      for(FieldError fieldError : fieldErrors) {
        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
      }
      userJsonResponse.setErrorsMap(errors);
      userJsonResponse.setStatus("ERROR");

    }
    Gson gson = new Gson();
    return gson.toJson(userJsonResponse);
  }

  @RequestMapping(value = "/emp/update", produces = { "application/json; charset=UTF-8" })
  public @ResponseBody
  String updateEmployee(@Valid EmployeeDto employeeDto, BindingResult bindingResult) {
    UserJsonResponse userJsonResponse = new UserJsonResponse();
    if(!bindingResult.hasErrors()) {

      employeeService.update(employeeDto);
    } else {
      Map<String, String> errors = new HashMap<String, String>();
      List<FieldError> fieldErrors = bindingResult.getFieldErrors();
      for(FieldError fieldError : fieldErrors) {
        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
      }
      userJsonResponse.setErrorsMap(errors);
      userJsonResponse.setStatus("ERROR");

    }
    Gson gson = new Gson();
    return gson.toJson(userJsonResponse);
  }

  // //////////////////////////////////////////////////////////
  class UserJsonResponse {
    private String status;
    private Map<String, String> errorsMap;

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public Map<String, String> getErrorsMap() {
      return errorsMap;
    }

    public void setErrorsMap(Map<String, String> errorsMap) {
      this.errorsMap = errorsMap;
    }

  }

}
