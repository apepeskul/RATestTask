package service;

import java.util.List;
import java.util.Map;

import dto.DivisionDto;

public interface DivisionService {
  void addNew(DivisionDto division);

  void update(DivisionDto divisionDto);

  void deleteById(Long id);

  DivisionDto getById(int id);

  List<DivisionDto> findAll();

  Map findAllAsMap();
}
