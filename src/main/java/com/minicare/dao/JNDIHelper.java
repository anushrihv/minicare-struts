package com.minicare.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JNDIHelper {
    public static Connection getJNDIConnection() throws NamingException, SQLException {
        String DATASOURCE_CONTEXT = "java:comp/env/jdbc/test3";
        Connection result = null;
        Context initialContext = new InitialContext();
        DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
        if (datasource != null) {
            result = datasource.getConnection();
        }

        return result;
    }
}
