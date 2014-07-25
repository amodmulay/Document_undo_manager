package undo.util;

/**
 * Created with IntelliJ IDEA.
 * User: amodmulay
 * Date: 7/25/14
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class UndoAssert {
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notValid(int value, String message) {
        if (value == 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
