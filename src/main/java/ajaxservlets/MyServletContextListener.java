package ajaxservlets;

import database.DAOController;
import database.Node;
import database.NodeDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent arg0) {
        NodeDAO dao = DAOController.getDao();

        //
        dao.clearTable();
        //

        Node node1 = new Node("test", "test", "a");
        Node node2 = new Node("test", "test", "b");
        Node node3 = new Node("test", "test", "c");
        Node node4 = new Node("test", "test", "d");
        dao.addNode(node1);
        dao.addNode(node2);
        dao.addNode(node3);
        dao.addNode(node4);
    }
}
