package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Card {

    public int cardNumber;
    private int cardPIN;
    private int cardBalance;

    Card(int cardNumber, int cardPIN, int cardBal){
        this.cardNumber=cardNumber;
        this.cardPIN=cardPIN;
        this.cardBalance=cardBal;
    }


    public int deposit(int amount){
        this.cardBalance+=amount;
        return this.cardBalance;
    }

    public int withdraw(int amount){
        if (this.cardBalance >= amount) {
            this.cardBalance -= amount;
            return this.cardBalance;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

}
