import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class AuthServer extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setStatus(200);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String log = req.getParameter("login");
        String pass = req.getParameter("pass");
        if (new CheckPass().valiuableData(log, pass)) {
            resp.setStatus(200);
            try {
                UUID uuid = UUID.randomUUID();
                new SetUUID().set(uuid, log);
                resp.getWriter().write(uuid.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            resp.setStatus(401);
        }
    }
}
