package undo.impl;

import undo.Change;
import undo.ChangeFactory;


/**
 * .
 * User: amodmulay
 * Date: 7/25/14
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChangeFactoryImpl implements ChangeFactory {
    @Override
    public Change createDeletion(final int pos, final String s, final int oldDot, final int newDot) {
        return new DeletionChangeImpl(pos, s, oldDot, newDot);
    }

    @Override
    public Change createInsertion(final int pos, final String s, final int oldDot, final int newDot) {
        return new InsertionChangeImpl(pos, s, oldDot, newDot);
    }
}
