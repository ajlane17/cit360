package com.company;

import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            System.out.println("closing session...");
            session.close();
        }


        if (!session.isOpen()) {
            System.out.println("Creating new session...");
            session = HibernateUtil.getSession();
        }
        System.out.println("Starting a new transaction...");
        Transaction tx = session.beginTransaction();

        System.out.println("Creating a new employee...");
	    EmployeeEntity newEmployee = new EmployeeEntity();
	    newEmployee.setFirstName("Bob");
	    newEmployee.setLastName("Jones");

        System.out.println("Saving employee to the database...");
	    session.save(newEmployee);
	    tx.commit();

        System.out.println("Closing session...");
	    session.close();


        if (!session.isOpen()) {
            System.out.println("Creating new session...");
            session = HibernateUtil.getSession();
        }
        System.out.println("Starting a manual query...");
        String customQuery = "FROM com.company.EmployeeEntity";
        Query query = session.createQuery(customQuery);
        List<EmployeeEntity> employees = query.list();

        System.out.println("Listing employee data...");
        for (EmployeeEntity employee : employees) {
            System.out.println(employee.toString());
        }

        System.out.println("Closing session...");
        session.close();
        HibernateUtil.shutdown();
    }
}
