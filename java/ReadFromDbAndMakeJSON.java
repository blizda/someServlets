import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReadFromDbAndMakeJSON {

    public void sendJSON(HttpServletResponse resp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        ArrayNode array = mapper.createArrayNode();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/blizniuk", "blizniuk", "blizniuk");
            DSLContext create = DSL.using(connection);
            Result<Record2<Object, Object>> result = create.select(DSL.field("login"), DSL.field("message"))
                    .from(DSL.table("massagehistory")).fetch();
            for (Record2<Object, Object> entry : result) {
                array.add(mapper.createObjectNode().put(
                        "login", (String) entry.value1())
                        .put("message", (String) entry.value2()));
            }
            rootNode.put("massagesarray", array);
            mapper.writeValue(resp.getOutputStream(), rootNode);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
