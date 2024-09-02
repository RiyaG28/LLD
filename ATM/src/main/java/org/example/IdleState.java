package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class IdleState implements ATMState{

    private ATM atm;

    IdleState(ATM atm){
        this.atm=atm;
    }

    @java.lang.Override
    public void insertCard(ATM atm){
        atm.setCurrState(new InsertedState(atm));
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

    @java.lang.Override
    public java.lang.String toString() {
        return "IdleState";
    }
}
