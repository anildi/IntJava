package ttl.larku.livedemo.dao;

import java.util.ResourceBundle;

/**
 * @author whynot
 */
public class DaoFactory {

    public static StudentDao studentDao() {
        ResourceBundle bundle = ResourceBundle.getBundle("larkuContext");
        String profile = bundle.getString("larku.profile");
        switch(profile) {
            case "development":
                return new InMemoryStudentDao();
            case "production":
                return new JPAStudentDao();
            default:
                throw new RuntimeException("unknown profile: " + profile);
        }
    }
}
