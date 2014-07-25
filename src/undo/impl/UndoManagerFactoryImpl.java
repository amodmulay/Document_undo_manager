package undo.impl;

import undo.Document;
import undo.UndoManager;
import undo.UndoManagerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: ${amodmulay}
 * Date: 7/24/14
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class UndoManagerFactoryImpl implements UndoManagerFactory {
    @Override
    public UndoManager createUndoManager(final Document doc, final int bufferSize) {
        return new UndoManagerImpl(doc, bufferSize);
    }
}
