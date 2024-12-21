package BankApplication.exception;

public class NoSuchAccountExistException extends Exception{
	public NoSuchAccountExistException(String msg){
		super(msg);
	}
}
