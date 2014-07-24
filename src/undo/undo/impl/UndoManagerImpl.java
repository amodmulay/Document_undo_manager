package undo.undo.impl;

import undo.Change;
import undo.Document;
import undo.UndoManager;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: amodmulay
 * Date: 7/24/14
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UndoManagerImpl implements UndoManager {

    private final Document document;
    private final int changListBufferSize;
    LinkedList<Change> changeList = null;
    private int undoLevel;


    public UndoManagerImpl(final Document doc, final int bufferSize) {
        document = doc;
        changListBufferSize = bufferSize;
        undoLevel = bufferSize;
        changeList = new LinkedList<Change>(new ArrayList<Change>(bufferSize));
    }

    @Override
    public void registerChange(final Change change) {
        // registering changes only till the size is not exhausted
        if (changeList.size() < changListBufferSize) {
            changeList.addFirst(change);
        } else {
            changeList.removeLast();
            changeList.addFirst(change);
        }
        resetUndoLevel();
    }

    private void resetUndoLevel() {
        undoLevel = changListBufferSize;
    }

    @Override
    public boolean canUndo() {
        return changeList.size() > 0;
    }

    @Override
    public void undo() throws IllegalStateException {
        if (undoLevel == 0)
            throw new IllegalStateException("Reached end of Undo Buffer");

        if (canUndo())
            throw new IllegalStateException("Nothing to Undo");

        changeList.get(undoLevel).apply(document);

        if (undoLevel > 0) {
            undoLevel--;
        }
    }

    @Override
    public boolean canRedo() {
        //if undo level is more than the 0 then one undo is done and hence re do can be performed.
        return undoLevel > 0;
    }

    @Override
    public void redo() {
        if (undoLevel == 0)
            throw new IllegalStateException("nothing to redoo");

        if (canUndo())
            throw new IllegalStateException("Nothing to Undoa");
    }
}
