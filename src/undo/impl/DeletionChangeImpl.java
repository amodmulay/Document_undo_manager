package undo.impl;

import undo.Change;
import undo.ChangeType;
import undo.Document;

/**
 * Created with IntelliJ IDEA.
 * User: amodmulay
 * Date: 7/25/14
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeletionChangeImpl implements Change {

    @Override
    public String getType() {
        return ChangeType.DELETION_TYPE.getType();
    }

    @Override
    public void apply(Document doc) {
        //Apply changes to the document
    }

    @Override
    public void revert(Document doc) {
        //revert changes to the document
    }
}
