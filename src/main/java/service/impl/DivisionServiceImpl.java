package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.DivisionService;
import dao.DivisionDao;
import domain.Division;
import dto.DivisionDto;

@Service
public class DivisionServiceImpl implements DivisionService {

  @Autowired
  private DivisionDao divisionDao;

  @Override
  public void addNew(DivisionDto divisionDto) {

    divisionDao.store(dtoToDomain(divisionDto));
  }

  @Override
  public void update(DivisionDto divisionDto) {
    divisionDao.update(dtoToDomain(divisionDto));

  }

  @Override
  public void deleteById(Long id) {
    divisionDao.deleteById(id);

  }

  @Override
  public DivisionDto getById(int id) {
    return makeDto(divisionDao.getById(Long.valueOf(id)));
  }

  @Override
  public List<DivisionDto> findAll() {
    return makeDtos(divisionDao.findAll());
  }

  @Override
  public Map findAllAsMap() {
    List<DivisionDto> divisionDtos = findAll();
    HashMap map = new HashMap();
    for(DivisionDto dto : divisionDtos) {
      map.put(dto.getId(), dto);
    }
    return map;
  }

  private DivisionDto makeDto(Division division) {
    return new DivisionDto(division.getId(), division.getName());
  }

  private List<DivisionDto> makeDtos(List<Division> divList) {
    List<DivisionDto> dtoList = new ArrayList<DivisionDto>();
    for(Division division : divList) {
      dtoList.add(makeDto(division));
    }
    return dtoList;
  }

  private Division dtoToDomain(DivisionDto divisionDto) {
    Division div = new Division(divisionDto.getName());
      div.setId(divisionDto.getId());
      return div;
  }
}
