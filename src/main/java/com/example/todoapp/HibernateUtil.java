package com.example.todoapp;





import com.example.todoapp.ToDo;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class HibernateUtil
{

    private static EntityManagerFactory factory;

    //Creates a session connection to the proper database
    public static Session getCurrentSession()
    {
       if(factory == null)
       {
           factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

       }
       EntityManager entityManager = factory.createEntityManager();
       Session session = entityManager.unwrap(org.hibernate.Session.class);

       SessionFactory factory = session.getSessionFactory();

       return session;

    }

    //Create and add Item to the database
    public boolean createItem(ToDo todo) {

        boolean success = true;


        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa");

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(todo);
            entityTransaction.commit();

        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            success = false;
        }

        ENTITY_MANAGER_FACTORY.close();
        return success;
    }


    //Delete User from the database



    //Grabs a list of all the items within the database
    public List<ToDo> listAll()
    {
        Transaction transaction = null;
        List<ToDo> listOfItems = null;

        try(Session session = HibernateUtil.getCurrentSession())
        {
            transaction = session.beginTransaction();

            listOfItems = session.createQuery("from ToDo").getResultList();

            transaction.commit();
        }catch (Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
        }

        return listOfItems;
    }
    public void deleteItem(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            ToDo todo = session.get(ToDo.class, id);
            if (todo != null) {
                session.delete(todo);
                System.out.println("user is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void addItem(ToDo todo)
    {
        System.out.println(todo);

        Transaction transaction = null;

        try(Session session = HibernateUtil.getCurrentSession())
        {
            transaction = session.beginTransaction();

            session.merge(todo);
            System.out.println(todo);

            transaction.commit();



        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }



}
