import java.util.Scanner;
import java.util.regex.Pattern;

public class View {
    static Controller c= new Controller();
    static Scanner sc=new Scanner(System.in);
    static String email="";
    static int id=0;
    View(String email, int id){
        this.email=email;
        this.id=id;
    }
    public static void signup()throws Exception{
        System.out.print("Enter  Name : ");
        String name =sc.next();
        boolean b=true;
        String email ="";
        do{
            System.out.print("\nEnter  Email-ID : ");
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
            System.out.print("\nEnter  Aadhar Number : ");
            aadhar=sc.next();
            if(!Pattern.matches("^[0-9]{12}", aadhar)){
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nInvalid Aadhar Number\n");
                System.out.println("\n------------------------------------------------------------\n");
                b=true;
            }
            else{
                b=false;
            }

        }while(b);
        b=c.signup(name,email,pass,aadhar);
        
    }

    public static int login(String email)throws Exception{
        int role=0;
        boolean b=true;
        while(b){
            do{
                System.out.println("1 --> Admin \n2 --> Staff\n3 --> User\n4 --> MRF Company\n");
                System.out.print("Enter your Role : ");
                role=sc.nextInt();
                if(role>5 || role<=0){
                    System.out.println("\n------------------------------------------------------------");
                    System.out.println("\n\n-------------------Invalid Choice---------------------------");
                }
                else{
                    b=false;
                }
            }while(b);
            System.out.print("\n\nEnter Email-ID : ");
            email=sc.next();
            System.out.print("Enter Password : ");
            String pass=sc.next();
            b=c.login(email,pass,role);
            if(b){
                System.out.println("\n------------------------------------------------------------");
                System.out.println("\n\nInvalid Email_ID or Password");
            }
            System.out.println("\n------------------------------------------------------------\n");
        }
        View v=new View(email,c.getid(email));
        return role;

    }
    public static int checkbal(String email)throws Exception{
        System.out.println("\n------------------------------------------------------------");
        return c.checkbal(email);
    }

    public static void transaction(String email)throws Exception{
        System.out.println("\n------------------------------------------------------------");
        c.transaction(email);
    }

    public static void tot_waste(String email)throws Exception{
        System.out.println("\n------------------------------------------------------------");
        c.tot_waste(email);
    }

    public static void withdraw(String email)throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        int amount=0;
        do{
            System.out.println("\n-----------------Minimum Withdrawal amount is 100----------------- \n");
            System.out.print("Enter the Withdraw Amount : ");
            amount=sc.nextInt();
            System.out.println();
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
        String email ="";
        boolean b;
        do{
            System.out.print("\nEnter  Email-ID : ");
            email = sc.next();
            b=c.existemail(email);
            if(!b){
                 System.out.println("\n------------------------------------------------------------\n");
                 System.out.println("\n\n Invalid Email_ID\n");
                 System.out.println("\n-------------    -----------------------------------------------\n");
            }
        }while(!b);
        c.printitems();
        System.out.print("Enter Waste Id : ");
        int w_id=sc.nextInt();
        System.out.print("Enter Waste Weight : ");
        int weight=sc.nextInt();
        c.deposit(email, w_id, weight,id);
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
    public static void delete_worker()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        String email ="";
        boolean b=false;
        do{
            System.out.print("\nEnter  Email-ID : ");
            email = sc.next();
            b=c.existemail1(email);
            if(!b){
                 System.out.println("\n------------------------------------------------------------\n");
                 System.out.println("\n\nEmail Not Exists\n");
                 System.out.println("\n------------------------------------------------------------\n");
                 b=false;
            }else{
                b=true;
            }
        }while(!b);
        c.delete_worker(email);
    }
    public static void add_member()throws Exception{
        System.out.print("\n\nEnter Member Name : ");
        String name =sc.next();
        boolean b=true;
        String email ="";
        do{
            System.out.print("\nEnter Member Email-ID : ");
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
        b=false;
        do{
            System.out.print("\nEnter  Aadhar Number : ");
            aadhar=sc.next();
            if(!Pattern.matches("^[0-9]{12}", aadhar)){
                System.out.println("\n------------------------------------------------------------\n");
                System.out.println("\n\nInvalid Aadhar Number\n");
                System.out.println("\n------------------------------------------------------------\n");
                b=true;
            }
            else{
                b=false;
            }

        }while(b);
        System.out.print("\n1 --> Admin\n2 --> Worker\n3 --> User\n\nEnter Role : ");
            int role=sc.nextInt();
            do{
                if(role==1){
                    b=false;
                    c.add_admin(name,email,pass,aadhar);
                    
                }else if(role==2){
                    b=false;
                    System.out.print("\nEnter Branch Name : ");
                    String branch=sc.next();
                    c.add_staff(name,email,pass,aadhar, id, branch);
                }
                else if(role==3){
                    b=false;
                    c.signup(name,email,pass,aadhar);
                }
                else{
                    System.out.println("\n------------------------------------------------------------\n");
                    System.out.println("\n\nInvalid Choice\n");
                    System.out.println("\n------------------------------------------------------------\n");
                    b=true;
                }
            }while(b);
            
        
    }

    public static void add_mrf()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("\n\nEnter Company Name : ");
        String c_name =sc.next();
        System.out.print("\n\nEnter Company ID : ");
        int c_id =sc.nextInt();
        System.out.print("\nEnter Email ID : ");
        String email=sc.next();
        System.out.print("\nEnter Incharge Name : ");
        String i_name=sc.next();
        System.out.print("\nEnter Mobile Number : ");
        int num=sc.nextInt();
        System.out.print("\nEnter Address : ");
        String add=sc.next();
        System.out.print("\nEnter password : ");
        String pass=sc.next();
        c.add_mrf(c_name, c_id, email, i_name, num, add, id, pass);
    }

    public static void buy_waste()throws Exception{
        System.out.println("\n------------------------------------------------------------\n");
        c.printitems();
        System.out.print("\nEnter Waste Id : ");
        int w_id=sc.nextInt();
        System.out.print("\nEnter Waste Quantity : ");
        int w=sc.nextInt();
        c.buy_waste(w_id, w);
    }



    //------------------------------main--------------------------------------------

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
        int role=login(email);
        if(role==3){
            System.out.println("\n-----------------------Welcome User!!----------------------\n");
            do{
                System.out.println("1 --> Check Balance\n2 --> Transaction History\n3 --> Total Waste Provided\n4 --> Show Plastic Rates\n5 --> Withdraw\n6 --> Log-Out\n7 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    System.out.println("\n\n----------------------Balance : "+checkbal(email)+"-------------------------");
                }
                else if(user_choice==2){
                    transaction(email);
                }
                else if(user_choice==3){
                    tot_waste(email);
                }
                else if(user_choice==4){
                    c.plastic_rate();
                }
                else if(user_choice==5){
                    withdraw(email);
                }
                else if(user_choice==6){
                    login(email);
                }
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=7);
        }
        else if(role==2){
            System.out.println("\n-----------------Welcome Worker !!-----------------\n");
            do{
                System.out.println("1 --> Deposit Waste from User\n2 --> Add a User\n3 --> Log-Out\n4 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    deposit();
                }
                else if(user_choice==2){
                    signup();
                }
                else if(user_choice==2){
                    login(email);
                }
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=4);
        }
        else if(role==1){
            System.out.println("\n-----------------Welcome Admin !!-----------------\n");
            do{
                System.out.println("1 --> Show user Details\n2 --> Show Staff Details\n3 --> Show Transaction List\n4 --> Change plastic Rate\n5 --> Add New Plastic item\n6 --> Add Member\n7 --> Fire a Staff\n8 --> Add a MRF Company\n9 --> Show Invantory\n10 --> Log-Out\n11 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    c.user_list();
                }
                else if(user_choice==2){
                    c.staff_list();
                }
                else if(user_choice==3){
                    c.transaction_list();
                }
                else if(user_choice==4){
                    change_rate();
                }
                else if(user_choice==5){
                    add_item();
                }
                else if(user_choice==6){
                    add_member();
                }
                else if(user_choice==7){
                    delete_worker();
                }
                else if(user_choice==8){
                    add_mrf();
                }
                else if(user_choice==9){
                    c.show_invan(1);
                }
                else if(user_choice==10){
                    login(email);
                }
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=10);
        }
        else if(role ==4){
            System.out.println("\n-----------------Welcome MRF Company !!-----------------\n");
            do{
                System.out.println("1 --> Show Invantory\n2 --> Buy Waste\n3 --> Exit");
                System.out.print("\nEnter your choice : ");
                user_choice=sc.nextInt();
                if(user_choice==1){
                    c.show_invan(4);
                }
                else if(user_choice==2){
                    buy_waste();
                }
               
                System.out.println("\n------------------------------------------------------------\n");
            }while(user_choice!=10);
        }
    }
}
