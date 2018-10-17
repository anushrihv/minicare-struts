package com.minicare.dao;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil(){

    }

    static {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
