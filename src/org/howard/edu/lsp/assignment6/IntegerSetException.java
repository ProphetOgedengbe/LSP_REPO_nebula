package org.howard.edu.lsp.assignment6;

/**
 * Custom exception thrown by IntegerSet when an operation cannot be performed
 * on an empty set (e.g., largest() or smallest()).
 */
public class IntegerSetException extends RuntimeException {

    /**
     * Constructs an IntegerSetException with the given detail message.
     *
     * @param message description of the error condition
     */
    public IntegerSetException(String message) {
        super(message);
    }
}
