package com.jeospatial.vptree;

import com.jeospatial.GeospatialPoint;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class TreeIterator<E extends GeospatialPoint> implements Iterator<E> {
    private ArrayList<VPTree<E>.VPNode<E>> leafNodes;
    
    private Iterator<VPTree<E>.VPNode<E>> leafIterator;
    private Iterator<E> currentIterator;
    
    private E nextElement;
    
    public TreeIterator(VPTree<E>.VPNode<E> root) {
        this.leafNodes = new ArrayList<VPTree<E>.VPNode<E>>();
        root.gatherLeafNodes(this.leafNodes);
        
        this.leafIterator = this.leafNodes.iterator();
        
        this.currentIterator = this.leafIterator.hasNext() ?
            this.leafIterator.next().getPoints().iterator() : null;
        
        this.findNextElement();
    }
    
    private void findNextElement() {
        if(this.currentIterator == null) {
            this.nextElement = null;
        } else {
            if(this.currentIterator.hasNext()) {
                this.nextElement = this.currentIterator.next();
            } else if(this.leafIterator.hasNext()) {
                this.currentIterator = this.leafIterator.next().getPoints().iterator();
                this.findNextElement();
            } else {
                // The current iterator is empty and the meta-iterator is empty;
                // we've hit the end of the line.
                this.nextElement = null;
            }
        }
    }
    
    public boolean hasNext() {
        return this.nextElement != null;
    }
    
    public E next() {
        if(this.nextElement == null) {
            throw new NoSuchElementException();
        }
        
        E next = this.nextElement;
        this.findNextElement();
        
        return next;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
