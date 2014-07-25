package undo.impl;

import undo.Change;
import undo.ChangeType;
import undo.Document;

/**
 * Created with IntelliJ IDEA.
 * User: amodmulay
 * Date: 7/25/14
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertionChangeImpl implements Change {
    @Override
    public String getType() {
        return ChangeType.INSERTION_TYPE.getType();
    }

    @Override
    public void apply(final Document doc) {
        //Apply changes to the document
    }

    @Override
    public void revert(final Document doc) {
        //Revert changes in the document
    }
}
