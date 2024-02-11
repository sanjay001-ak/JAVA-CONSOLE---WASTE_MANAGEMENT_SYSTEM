import java.sql.Connection;

public class Controller {
    static Connection con = null;
    static Model m = new Model();

    public static int getConnection() throws Exception {
        int refer = m.dbConenction();
        return refer;
    }

    public static boolean login(String email,String pass)throws Exception{
        return m.login(email,pass);
    }

    public static void signup(String name,String email,String pass, String aadhar)throws Exception{
        m.signup(name,email,pass,aadhar);
    }

    public static boolean existemail(String email)throws Exception{
        return m.existemail(email);
    }
}
