package service;

import dto.DivisionDto;

import java.util.List;
import java.util.Map;

public interface DivisionService {
    void addNew(DivisionDto division);

    void update(DivisionDto divisionDto);

    void deleteById(Long id);

    DivisionDto getById(int id);

    List<DivisionDto> findAll();

    Map findAllAsMap();
}
