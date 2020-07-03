import processor.CommandLineInputProcessor;
import processor.Processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EcommerceApp {

    private  static Processor processor = new CommandLineInputProcessor();

    public static void main(String[] args) {
        BufferedReader bufferReader = null;
        String input = null;
        try {
            System.out.println("\n\n\n\n\n");
            System.out.println("===================================================================");
            System.out.println("===================      E-commerce Website     ====================");
            System.out.println("===================================================================");
            printUsage();
            switch (args.length) {
                case 0: // Interactive: command-line input/output
                {
                    System.out.println("Please Enter 'exit' to end Execution");
                    System.out.println("Input:");
                    while (true) {
                        try {
                            bufferReader = new BufferedReader(new InputStreamReader(System.in));
                            input = bufferReader.readLine().trim();
                            if (input.equalsIgnoreCase("exit")) {
                                break;
                            } else {
                                if (processor.validate(input)) {
                                    try {
                                        processor.execute(input.trim(),bufferReader);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    printUsage();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 1:// File input/output
                {
                    File inputFile = new File(args[0]);
                    try {
                        bufferReader = new BufferedReader(new FileReader(inputFile));
                        int lineNo = 1;
                        while ((input = bufferReader.readLine()) != null) {
                            input = input.trim();
                            if (processor.validate(input)) {
                                try {
                                    processor.execute(input, bufferReader);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } else
                                System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + input);
                            lineNo++;
                        }
                    } catch (Exception e) {
                        throw new Exception(e);
                    }
                    break;
                }
                default:
                    System.out.println("Invalid input. Usage Style: java -jar <jar_file_path> <input_file_path>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                if (bufferReader != null)
                    bufferReader.close();
            } catch (IOException e) {
            }
        }
    }


    public static void printUsage() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(
                "--------------Please Enter one of the below commands. {variable} to be replaced -----------------------")
                .append("\n");

        stringBuilder.append("A) Add the product -----> add_product ")
                .append("\n");


        stringBuilder
                .append("B) To update the Product -----> update_product")
                .append("\n");


        stringBuilder.append("C) To delete the product -----> delete_product")
                .append("\n");

        stringBuilder.append("\n");

        stringBuilder.append("D) To sign up -----> sign_up").append("\n");


        stringBuilder.append(
                "E) To sign in -----> sign_in")
                .append("\n");


        stringBuilder.append(
                "F) to log out -----> log_out")
                .append("\n");

        stringBuilder.append("\n");


        stringBuilder.append(
                "G) To browse products -----> browse_products")
                .append("\n");


        stringBuilder.append(
                "H) To add to cart -----> add_to_cart")
                .append("\n");


        stringBuilder.append(
                "I) To Checkout -----> checkout")
                .append("\n");

        System.out.println(stringBuilder.toString());
    }




}
