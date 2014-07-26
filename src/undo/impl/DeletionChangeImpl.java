package undo.impl;

import undo.Change;
import undo.ChangeType;
import undo.Document;

/**
 * User: amodmulay
 * Date: 7/25/14
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeletionChangeImpl implements Change {

    private final int pos;
    private final String changeString;
    private final int oldDot;
    private final int newDot;


    public DeletionChangeImpl(final int pos, final String s, final int oldDot, final int newDot) {
        this.pos = pos;
        this.changeString = s;
        this.oldDot = oldDot;
        this.newDot = newDot;
    }

    @Override
    public String getType() {
        return ChangeType.DELETION_TYPE.getType();
    }

    @Override
    public void apply(Document doc) {
        doc.delete(pos, changeString);
        doc.setDot(newDot);
    }

    @Override
    public void revert(Document doc) {
        doc.insert(pos, changeString);
        doc.setDot(oldDot);
    }

    @Override
    public String toString() {
        return "DeletionChangeImpl{" +
                "changeString='" + changeString + '\'' +
                '}';
    }
}
