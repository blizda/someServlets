import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;

public class Chat extends HttpServlet {

    private HttpSession session;

    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setStatus(200);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        session = req.getSession(true);
        resp.setStatus(200);
        if (req.getParameter("from").equals("log")){
            if ((session.getAttribute("islog") != null
                    && (boolean)session.getAttribute("islog")))
                try {
                    resp.getWriter().write("ok");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            else {
                String log = req.getParameter("login");
                String pass = req.getParameter("pass");
                System.out.println(log);
                if (checkPass(log, pass)) {
                    session.setAttribute("islog", true);
                    session.setAttribute("login", log);
                    resp.getWriter().write("ok");
                } else
                    session.setAttribute("islog", false);
            }
        }
        else{
            if ((session.getAttribute("islog") != null
                    && (boolean)session.getAttribute("islog"))){
                if (req.getParameter("case").equals("getMassages")) {
                    resp.setHeader("text/html", "application/json;charset=UTF-8");
                    ReadFromDbAndMakeJSON fromDbAndMakeJSON = new ReadFromDbAndMakeJSON();
                    fromDbAndMakeJSON.sendJSON(resp);
                }
                else {
                    WriteMassageToDatabase writeMassageToDatabase = new WriteMassageToDatabase();
                    writeMassageToDatabase.writeToDb((String) session.
                            getAttribute("login"), req.getParameter("message"));
                    resp.setHeader("text/html", "application/json;charset=UTF-8");
                    ReadFromDbAndMakeJSON fromDbAndMakeJSON = new ReadFromDbAndMakeJSON();
                    fromDbAndMakeJSON.sendJSON(resp);
                }
            }
        }
    }

    private boolean checkPass(String log, String pass){
        try (Scanner in = new Scanner(getClass().getClassLoader().getResourceAsStream("pass.txt"))){
            while (in.hasNext()) {
                String str = in.nextLine().trim();
                String logAndPass = log + " " + pass;
                if (logAndPass.equals(str)){
                    return true;
                }
            }
        }
        return false;
    }
}
