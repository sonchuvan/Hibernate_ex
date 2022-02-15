package repository.busRouteRepository;

import entity.BusRoute;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.HibernateUtil;

import java.util.List;

public class BusRouteRepImp implements BusRouteRep {
    @Override
    public List<BusRoute> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<BusRoute> busRoutes = session.createQuery("from BusRoute").list();
            session.getTransaction().commit();
            return busRoutes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BusRoute getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<BusRoute> query = session.createQuery("select br from BusRoute br where br.id = :p_id");
            query.setParameter("p_id", id);
            BusRoute busRoute = query.getSingleResult();
            session.getTransaction().commit();
            return busRoute;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNew(BusRoute busRoute) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(busRoute);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(BusRoute busRoute) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(session);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(BusRoute busRoute) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getMaxId() {
        int maxId = 100;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Integer> query = session.createQuery("select max(id) from BusRoute");
            maxId = query.getSingleResult()+1;
            session.getTransaction().commit();
            return maxId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }


}
