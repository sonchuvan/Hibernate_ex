package repository.rosterRepository;

import entity.BusRoute;
import entity.Driver;
import entity.roster.Roster;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.HibernateUtil;

import java.util.List;

public class RosterRepImp implements RosterRep {
    @Override
    public List<Roster> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Roster> rosters = session.createQuery("from Roster").list();
            session.getTransaction().commit();
            return rosters;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Roster getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Roster> query = session.createQuery("select r from Roster r where r.id = :p_id");
            query.setParameter("p_id", id);
            Roster roster = query.getSingleResult();
            session.getTransaction().commit();
            return roster;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNew(Roster roster) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(roster);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Roster roster) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(roster);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Roster roster) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(roster);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Driver> getDistinct() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Driver> drivers = session.createQuery("select distinct driver from Roster").list();
            session.getTransaction().commit();
            return drivers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkDriverFromDrivingList(int driverId) {

        for (Driver driver : getDistinct()) {
            if (driver.getDriverId() == driverId){
                return true;
            }
        }
        return false;
    }

    @Override
    public long totalRouteQuantity(Driver driver) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Long> query = session.createQuery("select sum(route_quantity) from Roster where driver = :p_driver");
            query.setParameter("p_driver", driver);
            long total = 0;
            if (query.getSingleResult() != null){
                total = query.getSingleResult();
            }
            session.getTransaction().commit();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Roster getBusRouteFromRoster(Driver driver, BusRoute busRoute) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Roster> query = session.createQuery("Select r from Roster r where r.driver= :p_driver and r.busRoute = :p_busRoute");
            query.setParameter("p_driver", driver);
            query.setParameter("p_busRoute", busRoute);
            Roster roster = query.getSingleResult();
            session.getTransaction().commit();
            return roster;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
