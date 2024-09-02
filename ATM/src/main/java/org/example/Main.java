package org.example;

import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("Program started");

        HashMap<Integer, Card> cardMap= new HashMap<>();

        cardMap.put(1,new Card(1, 1234, 5000));
        cardMap.put(2,new Card(2, 4567, 5000));
        cardMap.put(3,new Card(3, 7789, 5000));
        cardMap.put(4,new Card(4,1234,10000000));


        Scanner obj = new Scanner(System.in);

        System.out.println("Insert Card");

        int cardNum= obj.nextInt();

        ATM atm= new ATM(cardMap.get(cardNum));

        System.out.println("ATM is in state: " + atm.getCurrState());

        atm.getCurrState().insertCard(atm);

        System.out.println("ATM is in state: " + atm.getCurrState());

        System.out.println("Enter PIN");
        int pin= obj.nextInt();

       try{
           atm.getCurrState().validatePin(atm,pin);
       }
       catch(IllegalArgumentException e){
           System.out.println(e.getMessage());
           return;
       }

        System.out.println("ATM is in state: " + atm.getCurrState());


        int op;

        while(true) {
            System.out.println("Select Operation:" + "\n" + "1.Deposit\n2.Withdraw\n3.Show Balance\n4.Exit");
            op=obj.nextInt();
            performOperation(atm, op, obj);
            updateMap(cardMap, atm);
            if(op==4)break;
        }

        obj.close();
    }

    public static void performOperation(ATM atm, int op, Scanner obj){

        if(op==1){
            System.out.println("Enter amount you want to deposit");
            int amount= obj.nextInt();
            atm.getCurrState().deposit(amount);

            int balance= atm.getCard().getCardBalance();
            System.out.println("Your new Bank Balance is: " + balance);

        }
        else if(op==2){
            System.out.println("Enter amount you want to withdraw");
            int amount= obj.nextInt();
            atm.getCurrState().withdraw(amount);

            int balance= atm.getCard().getCardBalance();
            System.out.println("Your new Bank Balance is: " + balance);
        }
        else if(op==3) {
            int balance= atm.getCard().getCardBalance();
            System.out.println("Your Bank Balance is: " + balance);
        }
        else if(op==4){
            System.out.println("Exit!");
        }
        else{
            System.out.println("No operation selected");
        }
        
    }

    public static void updateMap(HashMap<Integer,Card> cardMap, ATM atm){
        cardMap.put(atm.getCard().getCardNumber(),atm.getCard());
    }
}