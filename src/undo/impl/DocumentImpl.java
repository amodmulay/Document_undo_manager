package undo.impl;

import undo.Document;

/**
 * User: amodmulay
 * Date: 7/25/14
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentImpl implements Document {
    @Override
    public void delete(final int pos, final String s) {
        System.out.println("Deleting in document: " + s);
    }

    @Override
    public void insert(final int pos, final String s) {
        System.out.println("Inserting in document: " + s);
    }

    @Override
    public void setDot(final int pos) {
        System.out.println("Setting dot at: " + pos);
    }
}
