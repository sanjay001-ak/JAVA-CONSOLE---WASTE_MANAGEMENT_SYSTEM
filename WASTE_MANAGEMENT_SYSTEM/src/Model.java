import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import javax.xml.crypto.Data;

public class Model {
    static Connection con = null;

    public static int dbConenction() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3307/connect_in","root","Sanjay@2004");
        int c = (con != null && !con.isClosed()) ? 1 : 0;
        return c;
    }
    public static boolean login(String email_id,String pass,int role)throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query ="";
        if(role==1){//Admin
            query = "SELECT email_id, password FROM admin_details WHERE email_id=?";
        }
        else if(role==2){//worker
            query = "SELECT email_id, password FROM staff_details WHERE email_id=?";

        }
        else if(role==4){
            query = "SELECT email_id, password FROM mrf WHERE email_id=?";
        }
        else{//user
            query = "SELECT email_id, password FROM user_details WHERE email_id=?";
        }
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
    public static boolean existemail1(String email)throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT email_id FROM staff_details WHERE email_id=?";
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

    public static boolean signup(String name,String email,String pass, String aadhar)
    throws Exception{
        PreparedStatement preparedStatement = null;
        String query = "insert into user_details(name,email_id,password,aadhar_number) values(?,?,?,?)";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, pass);
        preparedStatement.setString(4, aadhar);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nSuccessfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
            return true;
        } else {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nNot Successfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
            return false;
        }
    }

    public static int getid(String email) throws Exception {
        PreparedStatement preparedStatement = null;
        int id = 0;
        ResultSet resultSet = null;
        String query = "SELECT id FROM staff_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email); 
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
    
    public static int checkbal(String email)throws Exception{
        PreparedStatement preparedStatement = null;
        int bal=0;
        ResultSet resultSet = null;
        String query = "SELECT total_balance FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email); 
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            bal = resultSet.getInt("total_balance");
        }
        return bal;
    
    }
    public static void tot_waste(String email)throws Exception{
        PreparedStatement preparedStatement = null;
        int tot_waste=0;
        ResultSet resultSet = null;
        String query = "SELECT total_waste FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email); 
        resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tot_waste = resultSet.getInt("total_waste");
            }
        System.out.println("Total Waste : "+tot_waste);
    
    }
    public static void transaction(String email) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM transaction WHERE email=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email); // Moved this line after preparing the statement
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) { // Check if any records were found
                do {
                    System.out.println("\n------------------------------------------------------------\n");
                    String date = resultSet.getString("date");
                    System.out.println("Date: " + date);
                    String waste_name = resultSet.getString("waste_id");
                    System.out.println("Waste id: " + waste_name);
                    String time = resultSet.getString("time");
                    System.out.println("Time: " + time);
                    int waste_weight = resultSet.getInt("waste_weight");
                    System.out.println("Waste Weight: " + waste_weight);
                    int amount = resultSet.getInt("amount");
                    System.out.println("Amount: " + amount);
                } while (resultSet.next());
            } else {
                System.out.println("No transactions found for the provided email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are always closed
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    
    public static void plastic_rate() throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM plastic";
        preparedStatement = con.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) { 
            System.out.println("\n------------------------------------------------------------\n");
            
            int id = resultSet.getInt("id");
            System.out.println("ID: " + id);
            String name = resultSet.getString("name");
            System.out.println("Plastic Name: " + name);
            String amount = resultSet.getString("amount");
            System.out.println("Amount per kg: " + amount);
            
        }
    }
    
    public static void withdraw(String email, int amount) throws Exception {
        Date currentDate = new Date(System.currentTimeMillis());
        Time currentTime = new Time(System.currentTimeMillis());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT total_balance FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
    
        if (resultSet.next()) {
            int bal = resultSet.getInt("total_balance");
            if (bal > amount) {
                System.out.println("\n------------- Amount Withdraw Successful ---------------");
                bal -= amount;
                System.out.println("--------------- Available Balance : " + bal + "--------------");
    
                // Update the total_balance in the database
                query = "UPDATE user_details SET total_balance = ? WHERE email_id = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, bal);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();

                //insert into withdraw
                query = "insert into withdraw values(?, ?, ?, ?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setDate(2, currentDate);
                preparedStatement.setTime(3, currentTime);
                preparedStatement.setInt(4, amount);
                preparedStatement.executeUpdate();



            } else {
                System.out.println("------------------- Insufficient Balance ------------------");
            }
        } else {
            System.out.println("------------------------ User not found -----------------------");
        }
    }
    public static void printitems()throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM plastic";
        preparedStatement = con.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        System.out.println("\n\nID          NAME\n");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String rate = resultSet.getString("amount");
            System.out.println(id+"          "+name);
        }
        System.out.println("0          Exit");
    }

    public static void deposit(String email,int w_id, int weight,int id) throws Exception {
        Date currentDate = new Date(System.currentTimeMillis());
        Time currentTime = new Time(System.currentTimeMillis());
    
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT total_waste, total_balance FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        String query1 = "SELECT amount FROM plastic WHERE id IN(?)";
        preparedStatement1 = con.prepareStatement(query1); // Corrected
        preparedStatement1.setInt(1, id);
        resultSet1 = preparedStatement1.executeQuery();
        int rate=0;
        if(resultSet1.next()){
            rate = resultSet1.getInt("amount");
            System.out.println(rate);
        }
    
        if (resultSet.next()) { // Check if both result sets have rows
            int bal = resultSet.getInt("total_balance");
            int weight_in_table = resultSet.getInt("total_waste");
            int totalAmount = rate * weight; // Corrected
            bal += totalAmount; // Update balance
            int final_weight = weight + weight_in_table;
    
            System.out.println("\n--------------- Deposit Successful ------------------");
            System.out.println("--------------- Total Balance : " + bal + "-----------------");
            System.out.println("--------------- Total Weight : " + final_weight + "-----------------");
    
            // Update the total_balance, total_weight in the database user_details
            query = "UPDATE user_details SET total_balance = ?, total_waste = ? WHERE email_id = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, bal);
            preparedStatement.setInt(2, final_weight);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
    
            // Add transaction entry
            query = "INSERT INTO transaction (email, date, waste_id, waste_weight, time,staff_id) VALUES (?, ?, ?, ?, ?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setDate(2, currentDate);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, weight);
            preparedStatement.setTime(5, currentTime);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } else {
            System.out.println("--------------------------  Deposit Unsuccessful --------------------------");
        }


        // invantory

         preparedStatement1 = null;
         resultSet1 = null;
         query1 = "SELECT max(date) as date FROM invantory";
        preparedStatement1 = con.prepareStatement(query1);
        resultSet1 = preparedStatement1.executeQuery();
        Date date;
        
                System.out.println("--------------added Successfull-------------------");
                query = "INSERT INTO invantory VALUES (?, ?, ?, ?, ?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setDate(1, currentDate);
                preparedStatement.setInt(2, w_id);
                preparedStatement.setInt(3, weight);
                preparedStatement.setInt(4, 0);
                preparedStatement.setInt(5, 0);
                int rs=preparedStatement.executeUpdate();
                
            
        

        

    }

    public static void managers_waste(String date) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT count(waste_id) AS waste_count FROM transaction WHERE date=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, date); 
        resultSet = preparedStatement.executeQuery();
        
        // Check if the result set has data
        if (resultSet.next()) {
            int wasteCount = resultSet.getInt("waste_count");
            System.out.println("Number of waste transactions on " + date + ": " + wasteCount);
        } else {
            System.out.println("No waste transactions found on " + date);
        }
    }

    public static void user_list()throws Exception{

        String query = "SELECT name, email_id, aadhar_number, total_balance, total_waste from user_details";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        //if(resultSet.next()){
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email_id");
                String aadhar = resultSet.getString("aadhar_number");
                int bal = resultSet.getInt("total_balance");
                int waste = resultSet.getInt("total_waste");
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\n  Name : "+name);
                System.out.println("  Email ID : "+email);
                System.out.println("  Aadhar Number : "+aadhar);
                System.out.println("  Total Balance : "+bal);
                System.out.println("  Total Waste : "+waste);
                System.out.println("\n------------------------------------------------------------\n");
            }
       // }
    }
    public static void staff_list()throws Exception{

        String query = "SELECT name, email_id, id, admin_id, aadhar_number,doj, branch from staff_details";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        //if(resultSet.next()){
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email_id");
                String id = resultSet.getString("id");
                String a_id = resultSet.getString("admin_id");
                String aadhar = resultSet.getString("aadhar_number");
                Date doj = resultSet.getDate("doj");
                String branch = resultSet.getString("branch");
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\n  Name : "+name);
                System.out.println("  Email ID : "+email);
                System.out.println("  Staff ID : "+email);
                System.out.println("  Admin ID : "+email);
                System.out.println("  Aadhar Number : "+aadhar);
                System.out.println("  Date of Joining : "+doj);
                System.out.println("  Branch : "+branch);
                System.out.println("\n------------------------------------------------------------\n");
            }
       // }
    }
    
    public static void transaction_list()throws Exception{
        String query = "SELECT * from transaction";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String date = resultSet.getString("date");
            int waste_id = resultSet.getInt("waste_id");
            int waste_weight = resultSet.getInt("waste_weight");
            int amount = resultSet.getInt("amount");
            String time = resultSet.getString("time");
            int id = resultSet.getInt("staff_id");
            System.out.println("Email_ID : " +email);
            System.out.println("Date : " +date);
            System.out.println("Time : " +time);
            System.out.println("Waste_ID : " +waste_id);
            System.out.println("Weight : " +waste_weight);
            System.out.println("Amount : " +amount);
            System.out.println("Staff_id : " +id);
            System.out.println("\n------------------------------------------------------------\n");
            
        }
    }

    public static void change_rate(int id, int amount)throws Exception{
        String query = "update plastic set amount = ? where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, id);
        int resultSet = preparedStatement.executeUpdate();

        if(resultSet>0){
            System.out.println("--------------------------  Update successful --------------------------");
        }
        else{
            System.out.println("--------------------------  Update Successful --------------------------");
        }
    }

    public static void add_item(String name, int amount)throws Exception{
        String query = "select max(id) AS id from plastic";
        PreparedStatement preparedStatement=con.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
         if(resultSet.next()){
            id = resultSet.getInt("id");
            id+=1;
        }
        query = "insert into plastic values(?, ?, ?)";
        preparedStatement=con.prepareStatement(query);
         preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, amount);
        int insert = preparedStatement.executeUpdate();
        if (insert > 0) {
            System.out.println("\n\n------------Item added successfully------------");
        } else {
            System.out.println("\n\n------------Failed to add item------------");
        }

    }

    public static boolean add_staff(String name,String email,String pass, String aadhar, int admin_id, String branch)
    throws Exception{
        Date currentDate = new Date(System.currentTimeMillis());

        String query = "select max(id) AS id from staff_details";
        PreparedStatement preparedStatement=con.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
         if(resultSet.next()){
            id = resultSet.getInt("id");
            id+=1;
        }
        preparedStatement = null;
        query = "insert into staff_details (name, email_id, id, admin_id, aadhar_number, doj, branch, password) values(?,?,?,?,?,?,?,?)";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, id);
        preparedStatement.setInt(4, admin_id);
        preparedStatement.setString(5, aadhar);
        preparedStatement.setDate(6, currentDate);
        preparedStatement.setString(7, branch);
        preparedStatement.setString(8, pass);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nSuccessfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
            return true;
        } else {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nNot Successfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
            return false;
        }
    }

    public static boolean add_admin(String name,String email,String pass, String aadhar)
    throws Exception{
        Date currentDate = new Date(System.currentTimeMillis());

        String query = "select max(id) AS id from admin_details";
        PreparedStatement preparedStatement=con.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
         if(resultSet.next()){
            id = resultSet.getInt("id");
            id+=1;
        }
        preparedStatement = null;
        query = "insert into admin_details(name, email_id, id, aadhar_number, doj, password) values(?,?,?,?,?,?)";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, id);
        preparedStatement.setString(4, aadhar);
        preparedStatement.setDate(5, currentDate);
        preparedStatement.setString(6, pass);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nSuccessfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
            return true;
        } else {    
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nNot Successfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
            return false;
        }
    }

    public static void delete_worker(String email) throws Exception {
    PreparedStatement preparedStatement = null;
    String query = "DELETE FROM staff_details WHERE email_id = ?";
    
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        
        int rowsDeleted = preparedStatement.executeUpdate();
        
        if (rowsDeleted > 0) {  
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nSuccessfully Deleted\n");
            System.out.println("\n------------------------------------------------------------\n");
        } else {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\n Delete Unsuccessful\n");
            System.out.println("\n------------------------------------------------------------\n");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

public static void add_mrf(String c_name, int c_id, String email, String i_name, int num, String add, int id, String pass)throws Exception{
    PreparedStatement preparedStatement = null;
    String query = "INSERT INTO mrf (c_name, c_id, email_id, incharge_name, contact, address, admin_id, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, c_name);
        preparedStatement.setInt(2, c_id);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, i_name);
        preparedStatement.setInt(5, num);
        preparedStatement.setString(6, add);
        preparedStatement.setInt(7, id);
        preparedStatement.setString(8, pass);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nSuccessfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
        } else {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\n\nNot Successfully Added\n");
            System.out.println("\n------------------------------------------------------------\n");
        }
}

public static void show_invan(int id)throws Exception{
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String query = "SELECT * FROM invantory";
    preparedStatement = con.prepareStatement(query);
    resultSet = preparedStatement.executeQuery();
    if(id==4){
        System.out.println("\n\nWaste-ID          Waste In Stock\n");
        while(resultSet.next()){
            int w_id = resultSet.getInt("w_id");
            String w_instock = resultSet.getString("w_instock");
            System.out.println(w_id+"          "+w_instock);
        }
    }
    else if(id==1){
        while(resultSet.next()){
            Date date=resultSet.getDate("date");
            int w_id = resultSet.getInt("w_id");
            int w_income = resultSet.getInt("w_income");
            int w_sold = resultSet.getInt("w_sold");
            String w_instock = resultSet.getString("w_instock");
            System.out.println("\nDate : "+date);
            System.out.println("Waste_ID : "+w_id);
            System.out.println("Waste_income : "+w_income);
            System.out.println("Waste_sold : "+w_sold);
            System.out.println("Waste_instock : "+w_instock);
            System.out.println("\n------------------------------------------------------------\n");
        }
    }


} 

public static void buy_waste(int w_id, int w) throws Exception {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        // First, fetch the current stock quantity for the given waste ID
        String selectQuery = "SELECT w_instock FROM invantory WHERE w_id=?";
        preparedStatement = con.prepareStatement(selectQuery);
        preparedStatement.setInt(1, w_id);
        resultSet = preparedStatement.executeQuery();

        int w_instock = 0;
        if (resultSet.next()) {
            w_instock = resultSet.getInt("w_instock");
        }

        if (w_instock >= w) {
            w_instock -= w; // Update the stock quantity
            String updateQuery = "UPDATE invantory SET w_instock = ? WHERE w_id = ?";
            preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setInt(1, w_instock);
            preparedStatement.setInt(2, w_id);
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nSuccessfully Purchased\n");
                System.out.println("\n------------------------------------------------------------\n");
            } else {
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nNot Successfully Purchased\n");
                System.out.println("\n------------------------------------------------------------\n");
            }
        } else {
            System.out.println("\n------------------------------------------------------------\n");
            System.out.println("\nInsufficient Weight in Invantory\n");
            System.out.println("\n------------------------------------------------------------\n");
        }
    } finally {
        // Close PreparedStatement and ResultSet in the finally block to ensure they are always closed
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

}
