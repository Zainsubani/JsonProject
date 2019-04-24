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
        name = "CreateNode",
        urlPatterns = "/createnode"
)
public class CreateNodeServlet extends HttpServlet {
    NodeDAO dao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nodeId = request.getParameter("id");
        String param1 = request.getParameter("param1"); //TODO: check if null
        String param2 = request.getParameter("param2");
        Node node = new Node(param1, param2, nodeId);
        dao.addNode(node);
    }

    public void init(ServletConfig config) throws ServletException {
        super.init();
        dao = DAOController.getDao();
    }
}