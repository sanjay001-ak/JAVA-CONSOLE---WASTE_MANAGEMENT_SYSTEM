import java.util.Scanner;
import java.util.regex.Pattern;

public class View {
    static Controller c= new Controller();
    static View v=new View();
    static Scanner sc=new Scanner(System.in);

    public static void signup()throws Exception{
        System.out.print("Enter your Name : ");
        String name =sc.next();
        boolean b=true;
        String email ="";
        while(b){
            System.out.print("\nEnter your Email-ID : ");
            email = sc.next();
            b=c.existemail(email);
            if(!Pattern.matches("^[a-z0-9]{6,}@[a-z]+\\.com$", email)){
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nInvalid Email-ID\n");
                System.out.println("\n------------------------------------------------------------\n");
            }
            else if(b){
                 System.out.println("\n------------------------------------------------------------\n");
                 System.out.println("\n\nEmail Already Exists\n");
                 System.out.println("\n------------------------------------------------------------\n");
            }
        }
        System.out.print("\nEnter password : ");
        String pass=sc.next();
        String aadhar="";
        do{
            System.out.print("\nEnter your Aadhar Number : ");
            aadhar=sc.next();
            if(!Pattern.matches("^[0-9]{12}", aadhar)){
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nInvalid Aadhar Number\n");
                System.out.println("\n------------------------------------------------------------\n");
            }

        }while(b);
        c.signup(name,email,pass,aadhar);
    }

    public static void login()throws Exception{
        boolean b=true;
        while(b){
            System.out.print("\nEnter Email-ID : ");
            String email=sc.next();
            System.out.print("Enter Password : ");
            String pass=sc.next();
            b=c.login(email,pass);
            if(b){
                System.out.println("\n\nInvalid Email_ID or Password");
            }
            System.out.println("\n------------------------------------------------------------\n");
        }

    }

    public static void main(String[] args) throws Exception {

        int refer = c.getConnection();
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----------  WELCOME  TO WASTE MANAGEMENT SYSTEM  ----------");
        System.out.println("LET'S CONNECT THE UNEDUCATED PROFESSIONS INTO A CPMMUNITY !!");
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("Enter any option to proceed forward....\n\n");
        System.out.println("1 --> SIGN-UP \n2 --> LOG-IN \n\n");
        System.out.print("Enter your choice : ");
        int choice=sc.nextInt();
        System.out.println("\n------------------------------------------------------------\n");
        if(choice==1){
            signup();
        }
        else{
            login();
        }
    }
}
