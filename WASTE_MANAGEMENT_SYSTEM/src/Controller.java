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

    public static boolean signup(String name,String email,String pass, String aadhar)throws Exception{
        return m.signup(name,email,pass,aadhar);
    }

    public static boolean existemail(String email)throws Exception{
        return m.existemail(email);
    }

    public static String getrole(String email)throws Exception{
        return m.getrole(email);
    }

    public static int checkbal(String email)throws Exception{
        return m.checkbal(email);
    }

    public static void transaction(String email)throws Exception{
        m.transaction(email);
    }
    public static void tot_waste(String email)throws Exception{
        m.tot_waste(email);
    }
    public static void plastic_rate()throws Exception{
        m.plastic_rate();
    }
    public static void withdraw(String email, int amount)throws Exception{
        m.withdraw(email, amount);
    }
    public static void deposit(String email,int id, int weight)throws Exception{
        m.deposit(email, id, weight);
    }
    public static void managers_waste(String date)throws Exception{
        m.managers_waste(date);
    }
    public static void printitems()throws Exception{
        m.printitems();
    }
    public static void user_list()throws Exception{
        m.user_list();
    }
    public static void transaction_list()throws Exception{
        m.transaction_list();
    }
    public static void change_rate(int id, int amount)throws Exception{
        m.change_rate(id,amount);
    }
    public static void add_item(String name, int amount)throws Exception{
        m.add_item(name, amount);
    }
}
