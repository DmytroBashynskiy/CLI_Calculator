package net.dmytrobashynskiy;

import java.util.*;

public class InputParser {
    private  String operators = "+-/*^";
    private  String delimiters = "() " + operators;
    //method for checking if symbol is an operator
    private boolean isOperator(String token){
        for(int i=0;i<operators.length();i++){
            if(token.charAt(0)==operators.charAt(i)){
                return true;
            }
        }return false;
    }
    //method for checking if symbol is a delimiter
    private boolean isDelimiter(String token){
        //All delimiters are single symbol, this line checks that
        if(token.length() != 1) return false;
        for(int i=0;i<delimiters.length();i++){
            if(token.charAt(0)==delimiters.charAt(i)){
                return true;
            }
        }return false;
    }


    //priority of operators
    private int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("^")) return 3;
        if (token.equals("*") || token.equals("/")) return 4;
        return 5;
    }

    //parsing method - shunting yard algorithm, produces a prepared ReversePolishNotation list of operators&operands
    public List<String> parse (String inputString) {
        //this list will be an output, composed according to Reverse Polish Notation rules
        List <String> outputQueue = new ArrayList<>();
        //this is a local stack for parsing operands and operators conditionally, helps with dealing with parenthesis'
        Deque<String> parsingStack = new ArrayDeque<>();
        //Tokenizer is an old class that breaks Strings into tokens,
        //which are smaller strings separated by delimiter symbols/strings. Delimiters are returned as tokens too
        //thanks to last 'true' setting in the constructor.
        StringTokenizer tokenizer = new StringTokenizer(inputString, delimiters, true);
        String currentToken; //this is local string for local momentary work

        //main iterator, works until there are available tokens to sift trough
        while(tokenizer.hasMoreTokens()){
            //we set current token here
            currentToken=tokenizer.nextToken();

            // check if token is space - skip it
            if(currentToken.equals(" ")){
                continue;
            }

            //this code deals with operators and delimiters
            else if(isDelimiter(currentToken)){
                switch (currentToken){
                    case "(":
                        parsingStack.push(currentToken);
                        break;
                    case ")":
                        //this loop looks of top element of stack does not equal an opening parenthesis
                        //each iteration pulls an element into output queue list until condition is true
                        try {
                            while (!parsingStack.peek().equals("(")) {
                                outputQueue.add(parsingStack.pop());
                                // this check below checks if stack is empty after we transfer an element from it
                                // if it is empty, that means that there was no opening parenthesis
                                // which means the whole expression was given with an error and calc won't work
                                if (parsingStack.isEmpty()) {
                                    // IF FAILS Parentheses are not paired properly;
                                    Main.failureMarker = DBError.PARENTHESIS;
                                    return outputQueue;
                                }
                            }

                            //this deletes that opening parenthesis that was controlling while loop
                            parsingStack.pop();
                            }catch (Exception exc){
                                Main.failureMarker = DBError.WRONG_EXPRESSION; //this happens if, for example '(2))'
                            }
                        break;
                    default:
                        //this compares token priority to the priority of the operator that is already in the parsing stack
                        while (!parsingStack.isEmpty() && (priority(currentToken) <= priority(parsingStack.peek()))) {
                            outputQueue.add(parsingStack.pop());
                        }
                        //push current Token to stack here
                        parsingStack.push(currentToken);
                        break;
                }
            }
            //this adds an operand to the output
            else outputQueue.add(currentToken);

        }
        //second iterator, adds last operators to the output
        while (!parsingStack.isEmpty()){
            if(isOperator(parsingStack.peek())){
                outputQueue.add(parsingStack.pop());
            }
            else{
                Main.failureMarker = DBError.PARENTHESIS;
                return outputQueue;
            }
        }
        return outputQueue;


    }
}
