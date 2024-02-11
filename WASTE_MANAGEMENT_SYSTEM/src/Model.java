import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Model {
    static Connection con = null;

    public static int dbConenction() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3307/connect_in","root","Sanjay@2004");
        int c = (con != null && !con.isClosed()) ? 1 : 0;
        return c;
    }
    public static boolean login(String email_id,String pass)throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT email_id, password FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email_id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String fetchedemailid = resultSet.getString("email_id");
            String fetchedpassword = resultSet.getString("password");
            if (fetchedemailid.equals(email_id) && fetchedpassword.equals(pass)) {
                return false;
            }
        }
        return true;
    }

    public static boolean existemail(String email)throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT email_id FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String fetchedemailid = resultSet.getString("email_id");
             if (fetchedemailid.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void signup(String name,String email,String pass, String aadhar)
    throws Exception{
        PreparedStatement preparedStatement = null;
        String role="User";
        String query = "insert into user_details(name,email_id,password,aadhar_number,role) values(?,?,?,?,?)";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, pass);
        preparedStatement.setString(4, aadhar);
        preparedStatement.setString(5, role);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            System.out.println("Successfully Added");
        } else {
            System.out.println("Not Successfully Added");
        }
    }
}
