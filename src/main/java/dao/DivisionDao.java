package dao;

import domain.Division;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DivisionDao {

    public void store(Division division);

    public void delete(Division division);

    public void deleteById(Long id);

    public void update(Division division);

    public Division getById(Long id);

    public void updateById(Long id);

    // public Division getByName(String name);

    public List<Division> findAll();
}
