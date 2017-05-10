import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Chat extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setStatus(200);
        try {
            resp.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        if (new CheckUUID().isSession(uuid)){
            if (req.getParameter("case").equals("getMassages")){
                resp.setHeader("text/html", "application/json;charset=UTF-8");
                ReadFromDbAndMakeJSON fromDbAndMakeJSON = new ReadFromDbAndMakeJSON();
                fromDbAndMakeJSON.sendJSON(resp);
            }
            else {
                WriteMassageToDatabase writeMassageToDatabase = new WriteMassageToDatabase();
                writeMassageToDatabase.writeToDb(new CheckUUID().getLogin(uuid), req.getParameter("message"));
                resp.setHeader("text/html", "application/json;charset=UTF-8");
                ReadFromDbAndMakeJSON fromDbAndMakeJSON = new ReadFromDbAndMakeJSON();
                fromDbAndMakeJSON.sendJSON(resp);
            }
        }
    }
}
