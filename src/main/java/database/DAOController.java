package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOController {
    private static NodeDAO dao;

    private static void initDao(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("C://path/to/db.properties")) {
            prop.load(input);
            dao = new NodeDAO(prop);
        } catch (IOException ex) {
            prop.setProperty("user", "root");
            prop.setProperty("password", "root");
            prop.setProperty("host", "localhost");
            prop.setProperty("port", "27017");
            prop.setProperty("dbname", "admin");
            prop.setProperty("table", "nodes");
        }
    }

    public static NodeDAO getDao(){
        if (dao==null){
            initDao();
        }
        return dao;
    }
}

