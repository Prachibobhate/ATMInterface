import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class transhist {
    void add(int amt, int choice, List<String> transhist1) {
        if (choice == 2) {
            String transaction = amt + " has been withdrawn";
            transhist1.add(transaction);
        } else {
            String transaction = amt + " deposited";
            transhist1.add(transaction);
        }
    }

    void add(int amt, String rec, List<String> transhist1) {
        String transaction = amt + " transferred to " + rec;
        transhist1.add(transaction);
    }

    void display(List<String> transhist1) {
        if (transhist1.size() == 0) {
            System.out.println("No Transactions yet!");
        } else {
            for (int i = 1; i <= transhist1.size(); i++) {
                System.out.println("Transaction " + i + " : " + transhist1.get(i - 1));
            }
        }
    }
}

class bankaccount {
    static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter your name :");
        ATM.name = sc.nextLine();
        System.out.println("Enter username :");
        String user = sc.nextLine();
        System.out.println("Enter password :");
        String pass = sc.nextLine();
        System.out.println("Enter your Account number :");
        ATM.accnumber = sc.nextLine();
        System.out.println("REGISTRATION SUCCESSFUL!");
        System.out.println("---------------------------");
        ATM.prompt();
    }
}

class withdraw {
    int draw(int bal, int amt) {
        return bal - amt;
    }
}

class deposit {
    int add(int bal, int amt) {
        return bal + amt;
    }
}

class transfer {
    int trans(int bal, int amt) {
        return bal - amt;
    }
}

class transaction {
    static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw :");
        int wcash = sc.nextInt();
        if (wcash <= ATM.balance) {
            ATM.balance = ATM.balance - wcash;
            ATM.history.add("Rs. " + wcash + " withdrawn");
            System.out.println("Amount Rs. " + wcash + "/- withdrawn successfully");
        } else {
            System.out.println("Insufficient balance to withdraw the cash");
        }
        ATM.prompt();
    }

    static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit :");
        int dcash = sc.nextInt();
        ATM.updatebalance(dcash);
        ATM.history.add("Rs. " + dcash + " deposited");
        System.out.println("Amount Rs. " + dcash + "/- deposit successful!");
        ATM.prompt();
    }

    static void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the receiving body:");
        String recipient = sc.nextLine();
        System.out.println("Enter the account number of the receiving body:");
        String accNum = sc.nextLine();
        System.out.println("Enter the amount to be transferred:");
        int tcash = sc.nextInt();
        if (tcash <= ATM.balance) {
            ATM.balance -= tcash;
            ATM.history.add("Rs. " + tcash + " transferred to " + recipient);
            System.out.println("Amount Rs. " + tcash + "/- transferred successfully");
        } else {
            System.out.println("Insufficient balance to transfer the cash");
        }
        ATM.prompt();
    }
}

class check {
    static void checkbalance() {
        System.out.println("------------------");
        System.out.println("The available balance in the bank account is: Rs. " + ATM.balance);
        System.out.println("------------------");
        ATM.prompt();
    }
}

class his {
    static void transactionhistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History :");
        if (ATM.history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (int i = 0; i < ATM.history.size(); i++) {
                System.out.println("Transaction " + (i + 1) + ": " + ATM.history.get(i));
            }
        }
        System.out.println("---------------------");
        ATM.prompt();
    }
}

public class ATM {
    public static String name;
    public static int balance = 0;
    public static String accnumber;
    public static ArrayList<String> history = new ArrayList<>();

    static void updatebalance(int dcash) {
        balance += dcash;
    }

    static void showbalance() {
        System.out.println(balance);
    }

    public static void homepage() {
        System.out.println("\033[H\033[2J"); // Clear screen (optional)
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO ATM INTERFACE");
        System.out.println("--------------------------");
        System.out.println("Select option:");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.println("Enter choice:");
        int choice = sc.nextInt();
        if (choice == 1) {
            bankaccount.register();
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Select a valid option!");
            homepage();
        }
    }

    static void prompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME " + ATM.name + "! TO ATM SYSTEM");
        System.out.println("---------------------");
        System.out.println("Select option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> transaction.withdraw();
            case 2 -> transaction.deposit();
            case 3 -> transaction.transfer();
            case 4 -> check.checkbalance();
            case 5 -> his.transactionhistory();
            case 6 -> System.exit(0);
            default -> {
                System.out.println("Invalid option! Try again.");
                prompt();
            }
        }
    }

    public static void main(String[] args) {
        homepage();
    }
}
