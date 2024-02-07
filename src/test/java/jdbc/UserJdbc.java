package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class UserJdbc extends AbstractJdbc {

    public static UUID getUserUUID(String email) {
        UUID uuid = null;
        try (Connection connection = getConnection()) {
            String script = "select id from member where email = ?";
            PreparedStatement statement = connection.prepareStatement(script);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                uuid = (UUID) resultSet.getObject("id");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return uuid;
    }
}
