package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private Long accNo;
    private String accName;
    private Long accBal;

    Account(Long num, String name, Long bal){
        this.accNo=num;
        this.accName=name;
        this.accBal=bal;
    }


    public void deposit(Long amount){
        this.accBal+=amount;
    }

    public void withdraw(Long amount){
        this.accBal-= amount;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Account{" +
                "accNo=" + accNo +
                ", accName=" + accName +
                ", accBal=" + accBal +
                '}';
    }
}
