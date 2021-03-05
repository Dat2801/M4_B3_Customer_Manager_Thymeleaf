package service;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@EnableTransactionManagement
public class CustomerServiceORM implements ICustomerService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll() {
        String query = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query1 = sessionFactory.openSession().createQuery(query, Customer.class);
        return query1.getResultList();
    }

    @Override
    public Customer edit(int id, Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return customer;
        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        finally {
            if (session != null){
                session.close();
            }
        }
        return null;
    }

    @Override
    public Customer create(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Customer findById(int id) {
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

//    @Override
//    public List<Customer> findByName(String name) {
//        return null;
//    }

    @Override
    public void delete(Customer customer) {
//        String queryStr = "DELETE FROM Customer WHERE Customer.id = :id";
//        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
//        query.setParameter("id", id);
//        query.getSingleResult();
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        finally {
            if (session != null){
                session.close();
            }
        }

    }
}
