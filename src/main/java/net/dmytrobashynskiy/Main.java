package net.dmytrobashynskiy;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static DBError failureMarker = DBError.GREEN;


    private static void menu(){
        boolean run = true;

        String welcomeText = "Welcome to the DB_calculator! ver. 1.2\nType 'help' to print" +
                " out info on how to work with \nthis application, or 'exit' to close it.";
        String help = "Supported operators are +, -, *, /, ^. \nType in 'calculate' and type in your expression." +
                "\nNumbers greater than 2^31 are supported." +"\nLetters and symbols other than numbers and operators are not allowed."+
                "\nCalculations with floating point are not supported yet.";
        Scanner initScanner = new Scanner(System.in);
        System.out.println(welcomeText);

        while(run){

            String lineInput = initScanner.nextLine();
            switch (lineInput){
                case "help":
                    System.out.println(help);
                    break;
                case "calculate":
                    failureMarker = DBError.GREEN;
                    System.out.println("Type in your expression:");
                    String lineInput2 = initScanner.nextLine();
                    //if(lineInput2.equals(" ")||lineInput2.equals("")){
                    //    System.out.println("Wrong expression: incorrectly written. Try again.");
                    //    break;
                    //}
                    InputParser parser = new InputParser();
                    List<String> readySequence = parser.parse(lineInput2);
                    Scanner floatScanner = new Scanner(lineInput2);
                    switch (failureMarker){//errors here hail from parser and hence - user input
                        case PARENTHESIS:
                            System.out.println("Wrong expression: parenthesis are not properly paired.");
                            break;
                        case WRONG_EXPRESSION:
                            System.out.println("Wrong expression: incorrectly written. Try again.");
                            break;
                        case GREEN://errors here hail from the calculator method.
                            BigInteger intOutput = Calculator.integerCalculation(readySequence);
                            if (failureMarker==DBError.DIV_BY_ZERO){
                                System.out.println("Wrong expression: division by zero. Try again.");
                            }else if(failureMarker==DBError.ILLEGAL_LETTERS){
                                System.out.println("Wrong expression: wrong characters are in the expression. Try again.");
                            }else if(failureMarker==DBError.EXPONENT_OVERFLOW){
                                System.out.println("Wrong expression: numbers in exponentiation operation are too big.");
                            }
                            else if(failureMarker==DBError.WRONG_EXPRESSION){
                                System.out.println("Wrong expression: incorrectly written. Try again.");
                            }
                            else{
                                System.out.println(intOutput);
                            }

                            break;
                        }
                    floatScanner.close();
                    break;
                case "exit":
                    run = false;
                    initScanner.close();
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Incorrect menu input. Type again, please.");
            }

        }
    }


    public static void main(String[] args) {
        menu();

        }

}
