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

@WebServlet(
        name = "GetNode",
        urlPatterns = "/getnode"
)
public class GetNodeServlet extends HttpServlet {
    NodeDAO dao;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Node node = dao.getByName(id);
        writer.write("{");
        if (!(node==null)){
            writer.write("\"name\":\""+id+"\", \"param1\":\""+node.getParam1()+"\", \"param2\":\""+node.getParam2()+"\"");
        }
        writer.write("}");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init();
        dao = DAOController.getDao();
    }
}