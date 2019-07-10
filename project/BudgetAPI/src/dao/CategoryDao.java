package dao;

import entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class CategoryDao implements Dao<Category> {

    private Session session;
    private Transaction transaction;

    @Override
    public Category get(long id) {

        Category category = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            category = session.get(Category.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return category;
    }

    @Override
    public List<Category> getAll() {

        List<Category> categories = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            categories = (List<Category>) session.createQuery("from Category").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }

        return categories;
    }

    @Override
    public void save(Category category) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Category category) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();;
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Category category) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }
}
