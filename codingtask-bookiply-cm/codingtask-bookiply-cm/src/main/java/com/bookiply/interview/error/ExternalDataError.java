package com.bookiply.interview.error;

/**
 * This class will handle the error if External Data is not available.
 */
public class ExternalDataError extends Exception{

    public ExternalDataError (String errorMessage){
        super(errorMessage);
    }
}
