import java.util.ArrayList;
import java.util.Scanner;

class Account {

    String website;
    String username;
    String password;

    Account(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }
}


public class PasswordManager {

    static ArrayList<Account> accounts = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);


    public static void addAccount() {

        System.out.print("Website: ");
        String website = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();


        accounts.add(
            new Account(website, username, password)
        );

        System.out.println("Account saved!\n");
    }


    public static void showAccounts() {

        if(accounts.isEmpty()){

            System.out.println("No saved passwords.\n");
            return;
        }


        System.out.println("\nSaved Accounts:");

        for(Account account : accounts){

            System.out.println("----------------");
            System.out.println("Website: " + account.website);
            System.out.println("Username: " + account.username);
            System.out.println("Password: ********");
        }

        System.out.println();
    }


    public static void searchAccount(){

        System.out.print("Search website: ");

        String search = scanner.nextLine();


        for(Account account : accounts){

            if(account.website.equalsIgnoreCase(search)){

                System.out.println("\nAccount Found");
                System.out.println("Website: " + account.website);
                System.out.println("Username: " + account.username);
                System.out.println("Password: " + account.password);

                return;
            }
        }


        System.out.println("Account not found.\n");
    }


    public static void deleteAccount(){

        System.out.print("Delete website: ");

        String website = scanner.nextLine();


        accounts.removeIf(
            account -> account.website.equalsIgnoreCase(website)
        );


        System.out.println("Account deleted.\n");
    }



    public static void main(String[] args){

        int choice;


        do{

            System.out.println("==============================");
            System.out.println("      PASSWORD MANAGER");
            System.out.println("==============================");

            System.out.println("1. Add Password");
            System.out.println("2. View Passwords");
            System.out.println("3. Search Password");
            System.out.println("4. Delete Password");
            System.out.println("5. Exit");

            System.out.print("Choose: ");

            choice = Integer.parseInt(scanner.nextLine());


            switch(choice){

                case 1:
                    addAccount();
                    break;

                case 2:
                    showAccounts();
                    break;

                case 3:
                    searchAccount();
                    break;

                case 4:
                    deleteAccount();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }


        }while(choice != 5);


        scanner.close();
    }
}
