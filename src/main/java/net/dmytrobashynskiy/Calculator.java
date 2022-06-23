package net.dmytrobashynskiy;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.List;



public class Calculator {



    public static BigInteger integerCalculation (List<String> outputQueue){

        Deque<BigInteger> stack = new ArrayDeque<>();

        for (String entry: outputQueue){
              switch (entry){
                  case "+":
                      try{
                          BigInteger b1 = stack.pop(), a1 = stack.pop();
                          stack.push(a1.add(b1));
                          break;
                      }
                      catch (EmptyStackException wrongExpression){
                          Main.failureMarker = DBError.WRONG_EXPRESSION;
                          stack.push(BigInteger.ONE);
                          break;
                      }
                  case "-":
                      try{
                          BigInteger b2 = stack.pop(), a2 = stack.pop();
                          stack.push(a2.subtract(b2));
                          break;
                      }
                      catch (EmptyStackException wrongExpression){
                          Main.failureMarker = DBError.WRONG_EXPRESSION;
                          stack.push(BigInteger.ONE);
                          break;
                      }
                  case "*":
                      try{
                          BigInteger b3 = stack.pop(), a3 = stack.pop();
                          stack.push(a3.multiply(b3));
                          break;
                      }
                      catch (EmptyStackException wrongExpression){
                          Main.failureMarker = DBError.WRONG_EXPRESSION;
                          stack.push(BigInteger.ONE);
                          break;
                      }
                  case "/":
                      try{
                          try{
                              BigInteger b4 = stack.pop(), a4 = stack.pop();
                              stack.push(a4.divide(b4));
                              break;
                          }
                          catch (EmptyStackException wrongExpression){
                              Main.failureMarker = DBError.WRONG_EXPRESSION;
                              stack.push(BigInteger.ONE);
                              break;
                          }
                      }catch (ArithmeticException divByZero){ //division by zero
                          Main.failureMarker = DBError.DIV_BY_ZERO;
                          stack.push(BigInteger.ONE);
                          break;
                      }
                  case "^":
                      try {
                          try{
                              BigInteger b5 = stack.pop(), a5 = stack.pop();
                              stack.push(a5.pow(b5.intValue()));
                              break;}
                          catch (EmptyStackException wrongExpression){
                              Main.failureMarker = DBError.WRONG_EXPRESSION;
                              stack.push(BigInteger.ONE);
                              break;
                          }
                      }catch (ArithmeticException tooBigNumber){
                          Main.failureMarker = DBError.EXPONENT_OVERFLOW;
                          stack.push(BigInteger.ONE);
                          break;
                      }
                  //number push
                  default:
                      try{
                          BigInteger number = new BigInteger(entry);
                          stack.push(number);
                          break;
                      }catch (NumberFormatException not_A_number){ //this 'detects' if there were any illegal symbols in token
                          Main.failureMarker = DBError.ILLEGAL_LETTERS;
                          stack.push(BigInteger.ONE);
                          break;
                      }


              }
              if (Main.failureMarker!=DBError.GREEN){
                  break;
              }
        }

        try{
            return stack.pop();
        }catch (EmptyStackException nothingWasEntered){
            Main.failureMarker = DBError.WRONG_EXPRESSION;
            return BigInteger.ONE;
        }
    }

}
