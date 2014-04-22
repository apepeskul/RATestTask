package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import dao.EmployeeDao;
import domain.Employee;

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
  public void delete(Employee emp) {
    getHibernateTemplate().delete(emp);

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
  public void updateById(Long id) {
    getHibernateTemplate().saveOrUpdate(getById(id));

  }

  @Override
  public List<Employee> searchByName(String query) {
    Query searchQuery = getCurrentSession().createQuery("from domain.Employee e where e.firstName like :searchkeyword");
    List<Employee> empList = searchQuery.setParameter("searchkeyword", query).list();
    return empList;
  }

  @Override
  public List<Employee> findAll() {
    return getHibernateTemplate().loadAll(Employee.class);
  }

  @Override
  public List<Employee> findPagedAndSorted(String sSearch, Integer pageSize, Integer startEntry, int sortColumnIndex, String sortDirection) {

    Query query = getCurrentSession().createQuery(
        "from domain.Employee e where e.firstName like :sSearch or e.lastName like :sSearch order by " + (sortColumnIndex + 1) + " " + sortDirection);
    query.setFirstResult(startEntry);
    query.setMaxResults(pageSize);
    List<Employee> empList = query.setParameter("sSearch", sSearch).list();
    return empList;
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

  /* @Override public void store(Employee emp) { String insertSQL =
   * "INSERT INTO Employees (firstName, lastName, salary, birthDate, active) values  (?, ?, ?, ?, ?)"; getJdbcTemplate().update(insertSQL,
   * emp.getFirstName(), emp.getLastName(), emp.getSalary(), emp.getBirthDate(), emp.getActive()); } */

}
