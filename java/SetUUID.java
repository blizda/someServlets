import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class SetUUID {
    public void set(UUID uuid, String log){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            create.update(DSL.table("autorisedata"))
                    .set(DSL.field("uuid"), uuid)
                    .where(DSL.field("login").eq(log))
                    .execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
