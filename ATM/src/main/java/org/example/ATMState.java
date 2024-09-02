package org.example;

public interface ATMState {

    public void insertCard(ATM atm);
    public void removeCard(ATM atm);
    public void validatePin(ATM atm, int pin);
    public void selectOperation(ATM atm, String op);
    public void deposit(int amount);
    public void withdraw(int amount);
    public void showBal();
    public void updateDB(Card card, int bal);
}
