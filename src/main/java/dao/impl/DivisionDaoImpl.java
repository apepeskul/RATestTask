package dao.impl;

import dao.DivisionDao;
import domain.Division;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivisionDaoImpl extends HibernateDaoSupport implements DivisionDao {

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
    public void store(Division division) {
        getCurrentSession().save(division);
    }

    @Override
    public void delete(Division division) {
        getHibernateTemplate().delete(division);

    }

    @Override
    public void deleteById(Long id) {
        getHibernateTemplate().delete(getById(id));
    }

    @Override
    public void update(Division division) {
        getHibernateTemplate().saveOrUpdate(division);
    }

    @Override
    public Division getById(Long id) {
        return getHibernateTemplate().get(Division.class, id);
    }

    @Override
    public void updateById(Long id) {
        getHibernateTemplate().saveOrUpdate(getById(id));

    }
    @Override
    public List<Division> findAll() {
        return getHibernateTemplate().loadAll(Division.class);
    }
}
