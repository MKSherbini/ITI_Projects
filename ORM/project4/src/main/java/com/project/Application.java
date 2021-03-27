package com.project;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.time.Period;

import com.project.inheritance4.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        ServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("/hibernate.cfg.xml")
                        .build();
        Metadata metadata =
                new MetadataSources(standardRegistry).buildMetadata();
        SessionFactory fact =
                metadata.getSessionFactoryBuilder().build();

        var session = fact.openSession();
        session.getTransaction().begin();

        Teacher teacher = new Teacher("teacher");
        Student student = new Student("student");
//        Person person = teacher;
//        session.save(person);
//        Person person = new Person();
//        person.setFirstName("teacher");
//        person.setLastName("teacher");
//        session.save(person);

//        teacher.setId(1);
        session.save(teacher);
//        student.setId(2);
        session.save(student);

        session.getTransaction().commit();
        session.close();
    }


}
