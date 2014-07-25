package undo.impl;

import undo.Change;
import undo.Document;
import undo.UndoManager;
import undo.util.UndoAssert;

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
    private final LinkedList<Change> changeList;
    private int undoLevel = 0;

    public UndoManagerImpl(final Document doc, final int bufferSize) {
        UndoAssert.notNull(doc, "Document cannot be null");
        UndoAssert.notValid(bufferSize, "Buffer size cannot be zero");
        document = doc;
        changListBufferSize = bufferSize;
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
            changeList.removeLast();
            changeList.addFirst(change);
        }
        resetUndoLevel();
    }

    /**
     * If there is a new change registered then the change level is reset
     * to the first value in the stack so that an undo action will result
     * in the latest changes being undone
     */
    private void resetUndoLevel() {
        undoLevel = changeList.size() - 1;
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

        //0 is the last object in the stack
        if (undoLevel == 0)
            throw new IllegalStateException("Reached end of Undo Buffer");


        changeList.get(undoLevel).apply(document);
        /*
        Id undo is called before a new change is registered then
        this will lead to the next change in the stack to be undone
         */
        if (undoLevel > 0) {
            undoLevel--;
        }
    }

    @Override
    public boolean canRedo() {
        //if undo level is more than the 0 then one undo is done and hence redo can be performed.
        return (undoLevel > 0 && undoLevel < changListBufferSize);
    }

    @Override
    public void redo() {
        if (undoLevel == 0)
            throw new IllegalStateException("nothing to redoo");

        if (canUndo())
            throw new IllegalStateException("Nothing to Undoa");
    }
}
