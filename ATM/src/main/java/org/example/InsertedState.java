package org.example;

public class InsertedState implements ATMState{

    private ATM atm;

    public InsertedState(ATM atm) {
        this.atm = atm;
    }

    @java.lang.Override
    public void insertCard(ATM atm) {

    }

    @java.lang.Override
    public java.lang.String toString() {
        return "InsertedState";
    }

    @java.lang.Override
    public void removeCard(ATM atm) {
        atm.setCurrState(new IdleState(atm));
    }

    @java.lang.Override
    public void validatePin(ATM atm, int pin) {
        if(atm.getCard().getCardPIN() == pin){
            System.out.println("Successful PIN");
            atm.setCurrState(new OperationState(atm));
        }
        else {
            throw new IllegalArgumentException("Invalid PIN");

        }
    }

    @java.lang.Override
    public void selectOperation(ATM atm, java.lang.String op) {

    }

    @java.lang.Override
    public void deposit(int amount) {

    }

    @java.lang.Override
    public void withdraw(int amount) {

    }

    @java.lang.Override
    public void showBal() {

    }

    @java.lang.Override
    public void updateDB(Card card, int bal) {

    }
}
