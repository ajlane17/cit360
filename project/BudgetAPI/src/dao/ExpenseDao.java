package dao;

import entity.Expense;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ExpenseDao implements Dao<Expense> {

    private Session session;
    private Transaction transaction;

    @Override
    public Expense get(long id) {
        Expense expense = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            expense = session.get(Expense.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return expense;
    }

    @Override
    public List<Expense> getAll() {
        List<Expense> expenses = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            expenses = (List<Expense>) session.createQuery("from Expense").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }

        return expenses;
    }

    @Override
    public void save(Expense expense) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.save(expense);
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
    public void update(Expense expense) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();;
            session.update(expense);
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
    public void delete(Expense expense) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.delete(expense);
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
