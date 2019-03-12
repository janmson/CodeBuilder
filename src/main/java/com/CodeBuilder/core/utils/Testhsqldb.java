package com.CodeBuilder.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Testhsqldb {

    public static void main(String[] args) throws Exception{

        Class.forName("org.hsqldb.jdbcDriver");

        String url = "jdbc:hsqldb:file:d:/hsqldb/test;shutdown=true";
        System.out.println(0);
        Connection c =DriverManager.getConnection(url, "root", "admin");
        System.out.println("1");
        Statement st = c.createStatement();

        /*String sql = "CREATE TABLE category (" +
                "  id int NOT NULL GENERATED BY DEFAULT AS IDENTITY," +
                "  name varchar(255) DEFAULT NULL," +
                "  PRIMARY KEY (id)" +
                ")";
        st.execute(sql);

        String sql2 = "insert into category values (null,'category1')";
        st.execute(sql2);*/

        System.out.println("2");
        ResultSet rs= st.executeQuery("select * from category");
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id+"\t"+name);
        }
        System.out.println("3");
        st.close();
        c.close();
        System.out.println("4");

    }
}
