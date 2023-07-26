package bankaccount;

public class InSufficentFundException extends Exception{
    
    private String message;

    public InSufficentFundException (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
