package ttl.larku.dao;

import java.util.ResourceBundle;

/**
 * @author whynot
 */
public class DaoFactory {

    private static StudentDAO daoCache = null;

    public static StudentDAO studentDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larku");
        String profile = bundle.getString("larku.profile");
        if(daoCache == null) {

            daoCache = switch (profile) {
                case "dev" -> new InMemoryStudentDAO();
                case "prod" -> new MysqlStudentDAO();
                default -> throw new RuntimeException("Unknown profile: " + profile);
            };
        }

        return daoCache;
    }
}
