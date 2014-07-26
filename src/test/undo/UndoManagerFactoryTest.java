package test.undo;

import org.junit.Assert;
import org.junit.Test;
import undo.*;
import undo.impl.ChangeFactoryImpl;
import undo.impl.DocumentImpl;
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
        undoManagerFactory.createUndoManager(new DocumentImpl(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUndoManagerNullDocument() throws Exception {

        UndoManagerFactory undoManagerFactory = new UndoManagerFactoryImpl();
        undoManagerFactory.createUndoManager(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUndoManagerZeroBuffer() throws Exception {

        UndoManagerFactory undoManagerFactory = new UndoManagerFactoryImpl();
        undoManagerFactory.createUndoManager(new DocumentImpl(), 0);
    }

    @Test
    public void testDocumentChange() throws Exception {
        UndoManagerFactory undoManagerFactory = new UndoManagerFactoryImpl();
        ChangeFactory changeFactory = new ChangeFactoryImpl();
        Document document = new DocumentImpl();
        UndoManager undoManager = undoManagerFactory.createUndoManager(document, 2);
        Assert.assertFalse(undoManager.canUndo());
        Assert.assertFalse(undoManager.canRedo());
        Change change1 = changeFactory.createInsertion(1, "Test Change1", 0, 1);
        change1.apply(document);
        Change change2 = changeFactory.createInsertion(2, "Test Change2", 1, 2);
        change2.apply(document);

        undoManager.registerChange(change1);
        undoManager.registerChange(change2);

        Assert.assertTrue(undoManager.canUndo());
        Assert.assertFalse(undoManager.canRedo());

        System.out.println("Undoing and redoing starts here----------------------------------------------");
        undoManager.undo();
        Assert.assertTrue(undoManager.canRedo());
        undoManager.undo();
        undoManager.redo();
        undoManager.redo();

        System.out.println("Registering new change ------------------------------------------------------");
        Change change3 = changeFactory.createInsertion(3, "Test Change3", 2, 3);
        change3.apply(document);
        undoManager.registerChange(change3);
        Assert.assertTrue(undoManager.canUndo());

        Assert.assertTrue(undoManager.canUndo());
        Assert.assertFalse(undoManager.canRedo());

        System.out.println("Undoing and redoing starts here----------------------------------------------");
        undoManager.undo();
        Assert.assertTrue(undoManager.canRedo());
        undoManager.undo();
        undoManager.redo();
        undoManager.redo();
    }
}

