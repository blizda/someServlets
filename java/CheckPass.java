import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckPass {
    public Boolean valiuableData(String login, String pass){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            Result<Record2<Object, Object>> result =  create.select(DSL.field("login"),DSL.field("password"))
                    .from(DSL.table("autorisedata"))
                    .where(DSL.field("login").eq(login))
                    .and(DSL.field("password").eq(pass))
                    .fetch();
            if (result.isNotEmpty()){
                System.out.println("we did it");
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
