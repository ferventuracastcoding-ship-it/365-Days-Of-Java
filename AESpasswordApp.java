import javax.crypto.SecretKey;
import java.util.Scanner;


public class AESPasswordApp {


    public static void main(String[] args)
    throws Exception {


        Scanner scanner =
            new Scanner(System.in);



        SecretKey key =
            AESUtil.generateKey();



        while(true){


            System.out.println(
            "\n===== AES PASSWORD VAULT ====="
            );


            System.out.println("1. Encrypt Password");
            System.out.println("2. Decrypt Password");
            System.out.println("3. Exit");


            System.out.print("Choice: ");

            int choice =
            scanner.nextInt();

            scanner.nextLine();



            if(choice==1){


                System.out.print(
                "Enter password: "
                );


                String password =
                scanner.nextLine();



                String encrypted =
                AESUtil.encrypt(
                    password,
                    key
                );


                System.out.println(
                "\nEncrypted Password:"
                );


                System.out.println(
                encrypted
                );


            }



            else if(choice==2){


                System.out.print(
                "Enter encrypted password: "
                );


                String encrypted =
                scanner.nextLine();



                String decrypted =
                AESUtil.decrypt(
                    encrypted,
                    key
                );


                System.out.println(
                "\nOriginal Password:"
                );


                System.out.println(
                decrypted
                );


            }



            else if(choice==3){

                System.out.println(
                "Vault locked."
                );

                break;

            }


            else{

                System.out.println(
                "Invalid option"
                );

            }

        }


        scanner.close();

    }

}
