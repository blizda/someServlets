import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.*;
import java.util.*;

public class WriteMassageToDatabase {
    public void writeToDb(String login, String message, java.util.Date date){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            create.insertInto(DSL.table("datamessages"),
                    DSL.field("login"), DSL.field("messages"),
                    DSL.field("data"))
                    .values(login, message, new Timestamp(date.getTime())).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
