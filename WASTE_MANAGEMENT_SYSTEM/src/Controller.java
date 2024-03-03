import java.sql.Connection;

public class Controller {
    static Connection con = null;
    static Model m = new Model();

    public static int getConnection() throws Exception {
        int refer = m.dbConenction();
        return refer;
    }

    public static boolean login(String email,String pass,int role)throws Exception{
        return m.login(email,pass, role);
    }

    public static boolean signup(String name,String email,String pass, String aadhar)throws Exception{
        return m.signup(name,email,pass,aadhar);
    }

    public static boolean existemail(String email)throws Exception{
        return m.existemail(email);
    }
    public static boolean existemail1(String email)throws Exception{
        return m.existemail1(email);
    }

    public static int getid(String email)throws Exception{
        return m.getid(email);
    }

    public static int checkbal(String email)throws Exception{
        return m.checkbal(email);
    }

    public static void transaction(String email)throws Exception{
        System.out.println(email);
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
    public static void deposit(String email,int w_id, int weight,int id)throws Exception{
        m.deposit(email, w_id, weight,id);
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
    public static void staff_list()throws Exception{
        m.staff_list();
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
    public static void add_staff(String name,String email,String pass, String aadhar, int id, String branch)throws Exception{
        m.add_staff(name,email,pass,aadhar, id, branch);
    }
    public static void add_admin(String name,String email,String pass, String aadhar)throws Exception{
        m.add_admin(name,email,pass,aadhar);
    }
    public static void delete_worker(String email)throws Exception{
        m.delete_worker(email);
    }
    public static void add_mrf(String c_name, int c_id, String email, String i_name, int num, String add, int id, String pass)throws Exception{
        m.add_mrf(c_name, c_id, email, i_name, num, add, id, pass);
    }
    public static void show_invan(int id)throws Exception{
        m.show_invan(id);
    } 
    public static void buy_waste(int w_id, int w)throws Exception{
        m.buy_waste(w_id, w);
    }
}
