package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    public static final int MAXI_SAVINGS_CUTOFF_DAYS = 10;

    private final int accountType;
    public List<Transaction> transactions;
    public double accountBalance;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.accountBalance = 0.0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount
            this.accountBalance = this.accountBalance + amount;
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    }  else if (amount > this.accountBalance){
	    throw new IllegalArgumentException("withdraw amount cannot exceed account balance");
	} else {
        transactions.add(new Transaction(-amount));
        this.accountBalance = this.accountBalance - amount;
    }
}
// Need to refactor to calculate interest per day. Need to calculate net balance
// for each day to calculate interest per day
    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
            	if (foundWithdrawBasedOnNumberOfDays(MAXI_SAVINGS_CUTOFF_DAYS))
            		return amount * .001;
            	else
            		return amount * .05;
            default:
                return amount * 0.001;
        }
    }
/*    public double interestEarned(Date startDate, Date endDate) {
        double interest = 0.0;
        double amount = 0.0;
        Date date = startDate
        while (startDate < endDate){
        	amount = getDailyBalance(startDate)
        	startDate
        }
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    interest = amount * 0.001;
                else
                    interest = 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
            	if (foundWithdrawBasedOnNumberOfDays(MAXI_SAVINGS_CUTOFF_DAYS))
            		interest = amount * .001;
            	else
            		interest = amount * .05;
            default:
                interest = amount * 0.001;
        }
        return interest;
    }
*/
// TODO: The purpose of this method is to calculate daily interest rate per 
// duration give start date and end date by utilizing the new method 
// getDailyBalance (Date date). This method will give balance as of a given date
    public double getDailyBalance(Date date){
        double balanceAsOf = 0.0;
        for (Transaction t: transactions)
            if (t.transactionDate.after(date)){
            	break;
            }else {
            	balanceAsOf += t.amount;
            }
        return balanceAsOf;
    }
    public double interestEarnedPerDuration(Date fromDate, Date toDate){
    	return 0.0;
    }
    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }
    private boolean foundWithdrawBasedOnNumberOfDays(int days) {
    	Calendar beginDate = Calendar.getInstance();
    	beginDate.add(Calendar.DATE, -days);
    	for (Transaction t: transactions){
    		if (t.transactionDate.compareTo(beginDate.getTime()) >=0  && t.amount < 0)
    			return true;
    		if (t.transactionDate.compareTo(beginDate.getTime()) < 0 )
    			return false;
    	}
    	return false;
    }
    public int getAccountType() {
        return accountType;
    }
}
