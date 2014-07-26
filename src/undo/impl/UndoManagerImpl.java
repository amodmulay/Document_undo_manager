package undo.impl;

import undo.Change;
import undo.Document;
import undo.UndoManager;
import undo.util.UndoAssert;

import java.util.LinkedList;

/**
 * Manages the change of state of a document associated with this object.
 * <p/>
 * User: amodmulay
 * Date: 7/24/14
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UndoManagerImpl implements UndoManager {

    private final Document document;
    private final int changListBufferSize;
    private final LinkedList<Change> changeList;
    private int undoLevel;
    private boolean canRedo = false;

    public UndoManagerImpl(final Document doc, final int bufferSize) {
        UndoAssert.notNull(doc, "Document cannot be null");
        UndoAssert.notValid(bufferSize, "Buffer size cannot be zero");
        document = doc;
        changListBufferSize = bufferSize;
        undoLevel = bufferSize;
        changeList = new LinkedList<Change>();
    }

    @Override
    public void registerChange(final Change change) {
        /*
        Adding changes only till the size is not exhausted.
        Else removing the change at the end of the stack
        and adding a new on the top of the stack
        */
        if (changeList.size() < changListBufferSize) {
            changeList.addFirst(change);
        } else {
            System.out.println("Size exhausted. Removing last entry: " + changeList.removeLast().toString());
            changeList.addFirst(change);
        }
        resetUndoLevel();
    }

    /**
     * If there is a new change registered then the change level is reset
     * to the newest value on the stack so that an undo action will result
     * in the latest changes being undone
     */
    private void resetUndoLevel() {
        undoLevel = 0;
        canRedo = false;
    }

    @Override
    public boolean canUndo() {
        /*
        If no change is registered then Undo cannot be performed
         */
        return changeList.size() > 0;
    }

    @Override
    public void undo() throws IllegalStateException {
        if (!canUndo())
            throw new IllegalStateException("Nothing to Undo");

        if (undoLevel == changeList.size())
            throw new IllegalStateException("Reached end of Undo Buffer");

        changeList.get(undoLevel).revert(document);
        /*
        If undo is called before a new change is registered then
        this will lead to the next change in the stack to be undone
         */
        undoLevel++;
        canRedo = true;
    }

    @Override
    public boolean canRedo() {
        return canRedo;
    }

    @Override
    public void redo() {
        if (!canRedo())
            throw new IllegalStateException("Nothing to Redo");

        undoLevel--;
        if (undoLevel < 0)
            throw new IllegalStateException("Reached end of Redo Buffer");
        changeList.get(undoLevel).apply(document);

    }
}
