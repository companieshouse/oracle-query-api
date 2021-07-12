package uk.gov.ch.exception;

public class NoActiveOfficersFoundException extends Exception{
    public NoActiveOfficersFoundException(String message){
        super(message);
    }
}
