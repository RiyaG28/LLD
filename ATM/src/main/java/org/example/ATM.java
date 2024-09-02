package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ATM {

    private ATMState currState;
    public Card card;

    ATM(){
        this.currState= new IdleState(this);
    }

    ATM(Card card){
        this();
        this.card=card;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ATM{" +
                "currState=" + currState +
                ", card=" + card +
                '}';
    }
}
