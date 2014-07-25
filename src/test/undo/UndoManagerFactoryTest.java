package test.undo;

import org.junit.Test;
import undo.UndoManagerFactory;
import undo.impl.UndoManagerFactoryImpl;


/**
 * Created with IntelliJ IDEA.
 * User: amodmulay
 * Date: 7/25/14
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class UndoManagerFactoryTest {
    @Test
    public void testCreateUndoManager() throws Exception {

        UndoManagerFactory undoManagerFactory = new UndoManagerFactoryImpl();

    }
}
