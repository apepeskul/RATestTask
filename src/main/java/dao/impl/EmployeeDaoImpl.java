package dao.impl;

import dao.EmployeeDao;
import domain.Employee;
import dto.DataTablesDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void store(Employee emp) {
        getCurrentSession().save(emp);
    }


    @Override
    public void deleteById(Long id) {
        getHibernateTemplate().delete(getById(id));
    }

    @Override
    public void update(Employee emp) {
        getHibernateTemplate().saveOrUpdate(emp);
    }

    @Override
    public Employee getById(Long id) {
        return getHibernateTemplate().get(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        return getHibernateTemplate().loadAll(Employee.class);
    }

    @Override
    public List<DataTablesDto> findPagedAndSorted(String sSearch, Integer pageSize, Integer startEntry, String sortColumnIndex, String sortDirection) {

        Query query = getCurrentSession().createQuery(
                // "from domain.Employee e where e.firstName like :sSearch or e.lastName like :sSearch order by " + sortColumnIndex + " " +
                // sortDirection);
                "select e.id, e.firstName,  e.lastName, e.salary, e.birthDate,  e.active, e.division.name from domain.Employee e left outer join e.division "
                        + "where e.firstName like :sSearch or e.lastName like :sSearch order by " + sortColumnIndex + " " + sortDirection);
        query.setFirstResult(startEntry);
        query.setMaxResults(pageSize);
        List<Object[]> empList = query.setParameter("sSearch", sSearch).list();
        List<DataTablesDto> dtoList = new ArrayList<DataTablesDto>();
        for (Object[] obj : empList) {
            DataTablesDto dto = new DataTablesDto();

            dto.setId(obj[0] != null ? (Long) obj[0] : 0);
            dto.setFirstName(obj[1] != null ? (String) obj[1] : "");
            dto.setLastName(obj[2] != null ? (String) obj[2] : "");
            dto.setSalary(obj[3] != null ? (String) obj[3] : "");
            dto.setBirthDate(obj[4] != null ? obj[4].toString() : "");
            dto.setActive(obj[5] != null ? (Boolean) obj[5] : false);
            dto.setDivisionName(obj[6] != null ? (String) obj[6] : "");
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public int getAllCount() {
        Long count = (Long) getCurrentSession().createQuery("select count(*) from domain.Employee e").uniqueResult();
        return count.intValue();
    }

    @Override
    public int getCountForQuery(String sSearch) {
        Query query = getCurrentSession().createQuery("select count(*) from domain.Employee e where e.firstName like :sSearch or e.lastName like :sSearch");
        query.setParameter("sSearch", sSearch).list();
        Long count = (Long) query.setParameter("sSearch", sSearch).uniqueResult();
        return count.intValue();
    }
}
