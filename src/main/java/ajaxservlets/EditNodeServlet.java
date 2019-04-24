package ajaxservlets;

import database.DAOController;
import database.Node;
import database.NodeDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "EditNode",
        urlPatterns = "/editnode"
)
public class EditNodeServlet extends HttpServlet {
    NodeDAO dao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nodeId = request.getParameter("id");
        String param1 = request.getParameter("param1"); //TODO: check if null
        String param2 = request.getParameter("param2");
        Node node = new Node();
        node.setName(nodeId);
        if(param1!=null){
            node.setParam1(param1);
        }
        if(param2!=null){
            node.setParam2(param2);
        }
        dao.update(node);
    }

    public void init(ServletConfig config) throws ServletException {
        super.init();
        dao = DAOController.getDao();
    }
}