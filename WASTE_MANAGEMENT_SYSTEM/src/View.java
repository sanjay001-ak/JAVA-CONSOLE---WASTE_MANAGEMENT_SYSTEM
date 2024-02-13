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
        do{
            System.out.print("\nEnter your Email-ID : ");
            email = sc.next();
            b=c.existemail(email);
            if(!Pattern.matches("^[a-z0-9]{6,}@[a-z]+\\.com$", email)){
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nInvalid Email-ID\n");
                System.out.println("\n------------------------------------------------------------\n");
                b=false;
            }
            else if(b){
                 System.out.println("\n------------------------------------------------------------\n");
                 System.out.println("\n\nEmail Already Exists\n");
                 System.out.println("\n------------------------------------------------------------\n");
                 b=false;
            }else{
                b=true;
            }
        }while(!b);
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
        b=c.signup(name,email,pass,aadhar);
        
    }

    public static String login()throws Exception{
        boolean b=true;
        String email="";
        while(b){
            System.out.print("\nEnter Email-ID : ");
            email=sc.next();
            System.out.print("Enter Password : ");
            String pass=sc.next();
            b=c.login(email,pass);
            if(b){
                System.out.println("\n------------------------------------------------------------");
                System.out.println("\n\nInvalid Email_ID or Password");
            }
            System.out.println("\n------------------------------------------------------------\n");
        }
        return c.getrole(email);

    }
    public static int checkbal()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Enter Email_id : ");
        String email=sc.next();
        return c.checkbal(email);
    }

    public static void transaction()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Enter Email_id : ");
        String email=sc.next();
        c.transaction(email);
    }

    public static void tot_waste()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Enter Email_id : ");
        String email=sc.next();
        c.tot_waste(email);
    }

    public static void withdraw()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Enter Email_id : ");
        String email=sc.next();
        int amount=0;
        do{
            System.out.println("\n-----------------Minimum Withdrawal amount is 100----------------- \n");
            System.out.print("Enter the Withdraw Amount : ");
            amount=sc.nextInt();
            if(amount<100){
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nYour withdrawal amount is below 100\n");
                System.out.println("\n------------------------------------------------------------\n");
            }
        }while(amount<100);
        c.withdraw(email, amount);
    }

    public static void deposit()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Enter User Email_id : ");
        String email=sc.next();
        c.printitems();
        System.out.print("Enter Waste Id : ");
        int id=sc.nextInt();
        System.out.print("Enter Waste Weight : ");
        int weight=sc.nextInt();
        c.deposit(email, id, weight);
    }

    public static void managers_waste(){
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Enter the particular date (YYYY-MM-DD) : ");
        String date=sc.next();
    }

    public static void change_rate()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        c.printitems();
        System.out.print("\nEnter the plastic ID : ");
        int id=sc.nextInt();
        System.out.print("\nEnter the Amount to change : ");
        int amt=sc.nextInt();
        c.change_rate(id,amt);
    }
    public static void add_item()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("\nEnter the plastic Name : ");
        String name=sc.next();
        System.out.print("\nEnter the Amount of Plastic : ");
        int amt=sc.nextInt();
        c.add_item(name, amt);
    }
    public static void main(String[] args) throws Exception {

        int refer = c.getConnection();
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----------  WELCOME  TO WASTE MANAGEMENT SYSTEM  ----------");
        System.out.println("               LET'S MAKE THE EARTH GREEN !!");
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("Enter any option to proceed forward....\n\n");
        System.out.println("1 --> SIGN-UP \n2 --> LOG-IN \n\n");
        System.out.print("Enter your choice : ");
        int choice=sc.nextInt();
        int user_choice=0;
        System.out.println("\n------------------------------------------------------------\n");
        if(choice==1){
            signup();
        }
        String role=login();
        if(role.contains("3")){
            System.out.println("\n-----------------Welcome User !!-----------------\n");
            do{
                System.out.println("1 --> Check Balance\n2 --> Transaction History\n3 --> Total Waste Provided\n4 --> Show Plastic Rates\n5 --> Withdraw\n6 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    System.out.println("\n\n----------------------Balance : "+checkbal()+"-------------------------");
                }
                else if(user_choice==2){
                    transaction();
                }
                else if(user_choice==3){
                    tot_waste();
                }
                else if(user_choice==4){
                    c.plastic_rate();
                }
                else if(user_choice==5){
                    withdraw();
                }
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=6);
        }
        else if(role.contains("2")){
            System.out.println("\n-----------------Welcome Worker !!-----------------\n");
            do{
                System.out.println("1 --> Deposit Waste from User\n2 --> Add a User\n3 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    deposit();
                }
                else if(user_choice==2){
                    signup();
                }
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=4);
        }
        else if(role.contains("1")){
            System.out.println("\n-----------------Welcome Admin !!-----------------\n");
            do{
                System.out.println("1 --> Show user Details\n2 --> Show Transaction List\n3 --> Change plastic Rate\n4 --> Add New Plastic item\n5 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    c.user_list();
                }
                else if(user_choice==2){
                    c.transaction_list();
                }
                else if(user_choice==3){
                    change_rate();
                }
                else if(user_choice==4){
                    add_item();
                }
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=5);
        }
    }
}
