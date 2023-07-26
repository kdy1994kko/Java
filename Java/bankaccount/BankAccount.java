package bankaccount;

public class BankAccount {

    private double balance;

    public BankAccount () {
        balance = 0.0;
    }

    public void deposit (double amount) {
        balance = balance + amount;
    }

    public void withdraw (double amount) throws InSufficentFundException {
        if (amount > balance) {
            throw new InSufficentFundException("Insufficient Balance." 
            + "Withdraw process couldn't be completed.");
        }
        
        balance = balance - amount;
    }

    public double getBalance() {
        return balance;
    }
}

