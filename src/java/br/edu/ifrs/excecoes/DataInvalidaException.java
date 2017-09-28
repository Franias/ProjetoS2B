/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.excecoes;

/**
 *
 * @author gustavo
 */
public class DataInvalidaException extends Exception {

    /**
     * Creates a new instance of <code>DataInvalidaException</code> without
     * detail message.
     */
    public DataInvalidaException() {
    }

    /**
     * Constructs an instance of <code>DataInvalidaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DataInvalidaException(String msg) {
        super(msg);
    }

    public DataInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInvalidaException(Throwable cause) {
        super(cause);
    }
    
    
}
