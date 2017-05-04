import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WriteMassageToDatabase {
    public void writeToDb(String login, String message){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            create.insertInto(DSL.table("massagehistory"),
                    DSL.field("login"), DSL.field("message")).values(login, message).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
