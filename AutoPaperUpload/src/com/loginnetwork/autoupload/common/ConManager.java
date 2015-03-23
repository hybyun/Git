package com.loginnetwork.autoupload.common;

import java.sql.*;
import java.io.*;
import java.util.*;

public class ConManager {
    
    //�����׽�Ʈ
    //public static final String PROPERTY_FILE ="C:\\Users\\Administrator\\Desktop\\eclipse - ���纻 (3)\\ws\\IHerb\\WebContent\\WEB-INF\\lib\\initial.properties";
    //public static final String PROPERTY_FILE ="D:\\WWW\\IHerb\\WEB-INF\\lib\\initial.properties";
    
	static Properties initialProps = null;
    public static boolean debug = true;
    
    private static ConManager instance;
    
    public static synchronized ConManager getInstance() {
        if (instance == null) {
            instance = new ConManager();
        }

        return instance;
    }

    private ConManager() {
        //initialize();
    }


    /*private void initialize() {
        File f = new File(PROPERTY_FILE);
        InputStream is = null;

        try {
            is = new FileInputStream(f.getAbsolutePath());
            initialProps = new Properties();
            initialProps.load(is);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public String getFilePath(){
    	String filepath = initialProps.getProperty("filepath");
        return filepath;
    }
    
    public String getVehicleFilePath(){
    	String filepath = initialProps.getProperty("vehicleFilepath");
        return filepath;
    }

    public Connection getConnection() throws Exception {
        String dburl = initialProps.getProperty("dburl");
        String dbuser = initialProps.getProperty("dbuser");
        String dbpass = initialProps.getProperty("dbpassword");
        String driver = initialProps.getProperty("dbdriver");
      
        Connection dbconn = null;
        try {
            Class.forName(driver);
            
            dbconn = DriverManager.getConnection(dburl, dbuser, dbpass);

        } catch (ClassNotFoundException e1) {
            throw new Exception("����̹��� �ε��� �� �����ϴ� : " + e1.getMessage());
        } catch (SQLException e2) {
            throw new Exception("DATABASE�� ������ �� �����ϴ� : " + e2.getMessage());
        }

        return dbconn;
    }
    
    

    public void freeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {}
    }

    public void freeclient() {}
    
    public void freeConnection(Connection c, PreparedStatement p, ResultSet r) {
        try {
            if (r != null) r.close();
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) r.close();
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, PreparedStatement p) {
        try {
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s) {
        try {
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
} //end of Main class
