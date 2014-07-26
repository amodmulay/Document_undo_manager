package undo;

/**
 * Enum describing the change types for a given change
 * User: amodmulay
 * Date: 7/25/14
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ChangeType {

    DELETION_TYPE("Delete"),
    INSERTION_TYPE("Insert");

    private String type;

    private ChangeType(final String changeType) {
        type = changeType;
    }

    public String getType() {
        return type;
    }
}
