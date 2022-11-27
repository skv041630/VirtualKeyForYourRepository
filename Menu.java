import java.io.*;
import java.util.*;

import static java.util.Objects.*;

public class Menu {

    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public static void main(String[] args)  {
        //Creating two scanner objects one for taking input for option and one for suboption
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        //Creating variables option and suboption
        int option=0;
        int suboption=0;
        //printing Application and Developer Details
        System.out.println("Lockers Pvt. Ltd.");
        System.out.println("Developer: Shatrudhan Kumar Verma");
        System.out.println("Contact: shatrudhanverma0416@gmail.com");
        System.out.println();
        //Adding a while loop and inside while loop creating the switch case inside it
        while (true) {
            //Starting of the  WHILE Loop
            //Showing main menu to the user and asking to select one option from given menu
            System.out.println("Please select one of the options below:");
            System.out.println("1. Display the current file name.");
            System.out.println("2. Display User Interfaces");
            System.out.println("3. Exit");
            try{
                option = scanner.nextInt();
            }
            catch (Exception first){
                scanner.next();
                //noinspection ThrowablePrintedToSystemOut
                System.err.println(first);
            }
            switch (option) {
                //when user selects option 1
                case 1:
                    //Taking path as input from the user
                    System.out.print("Enter the path to get the list of files:");
                    String inputFile = scanner.next();
                    //Creating a file object and passing the path taken from the user
                    File myFile = new File(inputFile);
                    //Creating an object of menu class
                    Menu menuobj = new Menu();
                    //Checking if given path is a directory and displaying files if true
                    if (myFile.exists() && myFile.isDirectory()) {
                        File[] a = myFile.listFiles();
                        System.out.println("======================================================================");
                        System.out.println("Displaying files of " + inputFile);
                        System.out.println("======================================================================");
                        assert a != null;
                        menuobj.printFileNames(a, 0, 0);


                    }
                    //If false showing invalid path
                    else {
                        System.out.println("======================================================================");
                        System.err.println("It's not a valid path, please enter correct path!");
                        System.out.println("======================================================================");
                    }
                    break;
                    //when user selects option 2
                case 2:
                    do {
                        //displaying menu of option 2 and asking to select desired option
                        System.out.println("=====================================================");
                        System.out.println("Select an option to perform the respective task:");
                        System.out.println("11. Add a file");
                        System.out.println("12. Delete file");
                        System.out.println("13. Search file");
                        System.out.println("14. Go Back to Main Menu");
                        System.out.println("15. Exit");
                        System.out.println("=====================================================");
                        try{
                            suboption = scanner1.nextInt();
                        }
                        catch (Exception second){
                            scanner1.next();
                            System.out.println(second);

                        }

                        switch (suboption) {
                            //when user select first option of sub menu adding file with
                            //user given file name to the current directory
                            case 11:
                            System.out.println("Enter the name of the file you want to be created: ");
                            String str= scanner.next();
                            str=str+".txt";
                            addFiles(str);
                            break;
                            //when user select second option of sub menu deleting file with
                            //user given file name to the current directory
                            case 12:
                                System.out.println("Enter the name of the file to be deleted:");
                                System.err.println("!File names are case sensitive!");
                                String str2= scanner.next();
                                deleteFile(str2);
                                break;
                            //when user select third option of sub menu searching file with
                            //user given file name to the current directory
                            case 13:
                                System.out.println("Enter the name of the file to be deleted:");
                                System.err.println("!File names are case sensitive!");
                                String str3= scanner.next();
                                searchFiles(str3);
                                break;
                            //when user select option 0 of sub menu returning to main menu
                            case 15:
                                System.out.println("Thanks for choosing Lockers Pvt. Ltd.");
                                System.out.println("Exiting from the application");
                                return;
                             //Creating a default case if user provides any invalid input
                            default:
                                //Making sure it doesn't throw the default statement when user
                                //tries to return to main menu
                                if (suboption!=14) {
                                    System.err.println("!!you have entered an invalid option, please try again!!");
                                }
                                break;
                        }
                    }
                    //providing condition for do while loop, the loop will only run if given input
                    //by user is not 14
                    while (suboption!=14);
                    break;
                case 3:
                    System.out.println("Thanks for choosing Lockers Pvt. Ltd.");
                    System.out.println("Exiting from the application");
                    return; //Closing the application and exiting the loop
                //creating default case for main menu if user provides any invalid input
                default:
                 System.err.println("you have entered an invalid option, please try again!!");
                 break;
            }
        }
    }
    //Creating a method to print files of the directory and its subdirectory
    private void printFileNames(File[] a, int i, int lvl) {
        if (i == a.length) return;
        try {
            for (int j = 0; j < lvl; j++) {
                System.out.print("\t");
            }
            if (a[i].isFile()) {
                System.out.println(a[i].getName());
            } else if (a[i].isDirectory()) {
                System.out.println("[" + a[i].getName() + "]");
                printFileNames(requireNonNull(a[i].listFiles()), 0, lvl + 1);
            }
            printFileNames(a, i + 1, lvl);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Creating addFiles method for creating a file by given name by user
    private static void addFiles(String FilePath){
        File addFile=new File(FilePath);
        try {
            if (addFile.createNewFile()) //calling create method of File class
            {
                System.out.println("======================================================================");
                System.out.println("your file with file name " + FilePath + " has been added.");
                System.out.println("======================================================================");
            } else {
                System.out.println("======================================================================");
                System.err.println("File with given name already exists in this directory.");
                System.out.println("======================================================================");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //Creating deleteFile method for deleting the user given file
    private static void deleteFile(String path){
        File delFile=new File(path);
        if (delFile.delete()) //calling delete method of File class
        {
            System.out.println("======================================================================");
            System.out.println("your file with file name " + path + " has been deleted.");
            System.out.println("======================================================================");
        } else {
            System.err.println("File not Found");
        }
    }
    //Creating searchFiles method for searching for the file given by the user
    private static void searchFiles(String path) {
        File searchFile=new File(path);
        if (searchFile.exists()){
            System.out.println("File is found and the location is "+searchFile.getAbsolutePath());
        }
        else {
            System.err.println("File you are trying to search doesn't exist.");
        }
    }
}