package repository.driverRepository;

import entity.Driver;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.HibernateUtil;

import java.util.List;

public class DriverRepImp implements DriverRep {
    @Override
    public List<Driver> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Driver> drivers = session.createQuery("from Driver").list();
            session.getTransaction().commit();
            return drivers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Driver getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Driver> query = session.createQuery("select d from Driver d where d.id = :p_id");
            query.setParameter("p_id",id);
            Driver driver = query.getSingleResult();
            session.getTransaction().commit();
            return driver;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNew(Driver driver) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(driver);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Driver driver) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(driver);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Driver driver) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(driver);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getMaxId() {
        int maxId = 10000;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Integer> query = session.createQuery("select max(id) from Driver");
            maxId = query.getSingleResult()+1;
            session.getTransaction().commit();
            return maxId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
