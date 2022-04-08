package com.bartoszek;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static Connection getConnection() throws SQLException {
        Connection conn=null;
        
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            conn= DriverManager.getConnection(url,username,password);
            DriverManager.setLogWriter(new PrintWriter("DatabaseLog.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
