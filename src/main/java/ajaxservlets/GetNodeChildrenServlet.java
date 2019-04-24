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
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(
        name = "GetNodeChildren",
        urlPatterns = "/getnodechildren"
)
public class GetNodeChildrenServlet extends HttpServlet {
    NodeDAO dao;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        if(id.equals("root")){
            List<Node> nodes = dao.getAll();
            writer.write("[");

            Iterator<Node> iterator = dao.getAll().iterator();
            Node node = iterator.next();
            writer.write("{\"id\" : \"" + node.getName()+ "\", \"parent\" : \"#\", \"text\" : \""
                    + node.getName() + "\", \"children\" : true}");
            while (iterator.hasNext()){
                node = iterator.next();
                writer.write(", {\"id\" : \"" + node.getName()+ "\", \"parent\" : \"#\", \"text\" : \""
                        + node.getName() + "\", \"children\" : true}");
            }
            writer.write("]");
            return;
        }
        writer.write(dao.getByName(id).toString());
    }

    public void init(ServletConfig config) throws ServletException {
        super.init();
        dao = DAOController.getDao();
    }
}
