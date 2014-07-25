package undo.impl;

import undo.Change;
import undo.ChangeFactory;


/**
 * Created with IntelliJ IDEA.
 * User: amodmulay
 * Date: 7/25/14
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChangeFactoryImpl implements ChangeFactory {
    @Override
    public Change createDeletion(int pos, String s, int oldDot, int newDot) {
        return new DeletionChangeImpl();
    }

    @Override
    public Change createInsertion(int pos, String s, int oldDot, int newDot) {
        return new InsertionChangeImpl();
    }
}
