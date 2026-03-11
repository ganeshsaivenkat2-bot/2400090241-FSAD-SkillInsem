package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;

public class ClientDemo {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) {
        ClientDemo demo = new ClientDemo();

        // I. insert a record via persistent object
        Long generatedId = demo.insertPayment("Alice", LocalDate.now(), "PENDING", 120.50);
        System.out.println("Inserted payment id = " + generatedId);

        // II. delete the record by id using HQL named parameter
        demo.deletePaymentById(generatedId);

        sessionFactory.close();
    }

    public Long insertPayment(String name, LocalDate date, String status, Double amount) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Long id = null;
        try {
            tx = session.beginTransaction();
            Payment p = new Payment(name, date, status, amount);
            id = (Long) session.save(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public void deletePaymentById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "delete from Payment where id = :pid";
            Query<?> query = session.createQuery(hql);
            query.setParameter("pid", id);
            int result = query.executeUpdate();
            System.out.println("Records deleted: " + result);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}