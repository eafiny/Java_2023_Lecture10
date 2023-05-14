package ru.muctr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgenia Skichko
 */
public class DBDemo {
    private  static SessionFactory factory;

    public static void prepareData(){
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try{
            String sqlString = Files.lines(Paths.get("init.sql"))
                    .collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sqlString).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        prepareData();
        readFromDB();
//        updateInDb();
//        deleteFromDb();
//        addInDb();
//        readWithJpql();
        factory.close();
    }

    private static void readWithJpql() {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Prepod prepod = (Prepod) session.createQuery("select p from Prepod p where p.id = 2").getSingleResult();
            System.out.println(prepod);

            Prepod prepod2 = session.createQuery("select p from Prepod p where p.name = 'John'", Prepod.class).getSingleResult();
            System.out.println(prepod2);

            List<Prepod> prepodList = session.createQuery("select p from Prepod p where p.salary > 60000").getResultList();
            System.out.println(prepodList);

            session.getTransaction().commit();
        }
    }
    private static void addInDb() {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Prepod prepod = new Prepod("Ivan", 150000);
            session.save(prepod);
            session.getTransaction().commit();
        }
    }

    private static void deleteFromDb() {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Prepod prepod = session.get(Prepod.class, 1);
            session.getTransaction().commit();
            session.delete(prepod);
        }
    }

    private static void updateInDb() {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Prepod prepod = session.get(Prepod.class, 3);
            prepod.setSalary(250000);
            prepod.setSalary(350000);

            session.getTransaction().commit();
            prepod.setSalary(450000);
            System.out.println(prepod.getSalary());
        }
    }

    private static void readFromDB() {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Prepod prepod = session.get(Prepod.class, 1);
            System.out.println();
            System.out.println("Преподаватель №1: " + prepod.toString() + "\n");
            System.out.println("Доп. информация о преподавателе №1: " + prepod.getInfo() + "\n");
            System.out.println("Преподаватель №1 работает на кафедре: " + prepod.getDepartment() + "\n");
            System.out.println("Группы преподавателя №1: " + prepod.getGroups() + "/n");
            session.getTransaction().commit();


        }

    }

}
