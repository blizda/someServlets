import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class CheckUUID {
    public Boolean isSession(UUID uuid){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            Result<Record2<Object, Object>> result =  create.select(DSL.field("login"),DSL.field("uuid"))
                    .from(DSL.table("autorisedata"))
                    .where(DSL.field("uuid").eq(uuid))
                    .fetch();
            if (result.isNotEmpty()){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getLogin(UUID uuid){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            Result<Record2<Object, Object>> result =  create.select(DSL.field("login"),DSL.field("uuid"))
                    .from(DSL.table("autorisedata"))
                    .where(DSL.field("uuid").eq(uuid))
                    .fetch();
            if (result.isNotEmpty()){
                for (Record2<Object, Object> entry : result) {
                    return entry.value1().toString();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
