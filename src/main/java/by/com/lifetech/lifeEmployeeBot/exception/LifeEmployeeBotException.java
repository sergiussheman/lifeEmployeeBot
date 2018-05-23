package by.com.lifetech.lifeEmployeeBot.exception;

public class LifeEmployeeBotException extends RuntimeException {
    public LifeEmployeeBotException(String message) {
        super(message);
    }

    public LifeEmployeeBotException(String message, Exception e){
        super(message, e);
    }
}
