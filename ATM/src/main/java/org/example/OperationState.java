package org.example;

public class OperationState implements ATMState{

    private ATM atm;

    OperationState(ATM atm){
        this.atm=atm;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "OperationState";
    }

    @java.lang.Override
    public void updateDB(Card card, int bal) {

    }

    @java.lang.Override
    public void insertCard(ATM atm) {

    }

    @java.lang.Override
    public void removeCard(ATM atm) {
        atm.setCurrState(new IdleState(atm));
    }


    @java.lang.Override
    public void validatePin(ATM atm, int pin) {
    }

    @java.lang.Override
    public void selectOperation(ATM atm, java.lang.String op) {

    }

    @java.lang.Override
    public void deposit(int amount) {
        atm.getCard().deposit(amount);
    }

    @java.lang.Override
    public void withdraw(int amount) {
        atm.getCard().withdraw(amount);
    }

    @java.lang.Override
    public void showBal() {

    }


}
