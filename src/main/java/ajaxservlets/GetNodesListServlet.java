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
        name = "GetNodesList",
        urlPatterns = "/getnodeslist"
)
public class GetNodesListServlet extends HttpServlet {
    NodeDAO dao;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mode = request.getParameter("mode");
        boolean isDetailedMode;

        if ((mode==null)||mode.equalsIgnoreCase("undetailed")){
            isDetailedMode=false;
        } else if(mode.equalsIgnoreCase("detailed")){
            isDetailedMode=true;
        } else {
            //TODO: bad arg
            System.out.println("Bad arg: " + mode);
            return;
        }

        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        List<Node> nodes = dao.getAll();
        writer.write("[");
        Iterator<Node> iterator = dao.getAll().iterator();
        Node node = iterator.next();

        if(isDetailedMode){
            writer.write("{\"name\":\"" + node.getName()+"\", \"param1\":\"" + node.getParam1() + "\", \"param2\":\""
                    + node.getParam2() + "\"}");
        } else {
            writer.write("\"" + node.getName() +"\"");
        }
        while (iterator.hasNext()){
            node = iterator.next();
            if(isDetailedMode){
                writer.write(", {\"name\":\"" + node.getName()+"\", \"param1\":\"" + node.getParam1() + "\", \"param2\":\""
                        + node.getParam2() + "\"}");
            } else {
                writer.write(", \"" + node.getName() +"\"");
            }
        }
        writer.write("]");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init();
        dao = DAOController.getDao();
    }
}
