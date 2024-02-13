import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static boolean signup(String name,String email,String pass, String aadhar)
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

    public static String getrole(String email) throws Exception {
        PreparedStatement preparedStatement = null;
        String role = "";
        ResultSet resultSet = null;
        String query = "SELECT role FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email); 
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            role = resultSet.getString("role");
        }
        return role;
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
        String query = "SELECT * FROM transaction WHERE email=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email); 
        resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) { 
            System.out.println("\n------------------------------------------------------------\n");
            String date = resultSet.getString("date");
            System.out.println("Date: " + date);
            String waste_name = resultSet.getString("waste_name");
            System.out.println("Waste Name: " + waste_name);
            String time = resultSet.getString("time");
            System.out.println("Time: " + time);
            int waste_weigth = resultSet.getInt("waste_weigth");
            System.out.println("Waste Weight: " + waste_weigth);
            int amount = resultSet.getInt("amount");
            System.out.println("Amount: " + amount);
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT total_balance FROM user_details WHERE email_id=?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
    
        if (resultSet.next()) {
            int bal = resultSet.getInt("total_balance");
            if (bal > amount) {
                System.out.println("\n--------------- Amount Withdraw Successful ------------------");
                bal -= amount;
                System.out.println("--------------- Available Balance : " + bal + "-----------------");
    
                // Update the total_balance in the database
                query = "UPDATE user_details SET total_balance = ? WHERE email_id = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, bal);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("--------------------- Insufficient Balance ---------------------");
            }
        } else {
            System.out.println("-------------------------- User not found --------------------------");
        }
    }
    public static void printitems()throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM plastic";
        preparedStatement = con.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        System.out.println("\n\nID          NAME        Rate\n");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String rate = resultSet.getString("amount");
            System.out.println(id+"          "+name+"       "+rate);
        }
        System.out.println("0          Exit");
    }

    public static void deposit(String email, int id, int weight) throws Exception {
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
            query = "INSERT INTO transaction (email, date, waste_id, waste_weight, time) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setDate(2, currentDate);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, weight);
            preparedStatement.setTime(5, currentTime);
            preparedStatement.executeUpdate();
        } else {
            System.out.println("--------------------------  Deposit Unsuccessful --------------------------");
        }
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

        String query = "SELECT name, email_id, aadhar_number, total_balance, total_waste from user_details where role = 3";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        //if(resultSet.next()){
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email_id");
                String aadhar = resultSet.getString("aadhar_number");
                int bal = resultSet.getInt("total_balance");
                int waste = resultSet.getInt("total_waste");
                System.out.println("Name : "+name);
                System.out.println("Email ID : "+email);
                System.out.println("Aadhar Number : "+aadhar);
                System.out.println("Total Balance : "+bal);
                System.out.println("Total Waste : "+waste);
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
            System.out.println("Email_ID : " +email);
            System.out.println("Date : " +date);
            System.out.println("Time : " +time);
            System.out.println("Waste_ID : " +waste_id);
            System.out.println("Weight : " +waste_weight);
            System.out.println("Amount : " +amount);
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
}
