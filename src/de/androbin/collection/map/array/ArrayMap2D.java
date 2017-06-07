package de.androbin.collection.map.array;

import de.androbin.collection.map.*;

public class ArrayMap2D<E> implements Map2D<E> {
  public final E[] elements;
  
  public final int dimX;
  public final int dimY;
  
  public ArrayMap2D( final E[] elements, final int dimX, final int dimY ) {
    this.elements = elements;
    
    this.dimX = dimX;
    this.dimY = dimY;
  }
  
  @ Override
  public E getElementAt( final int x, final int y ) {
    return elements[ index( x, y ) ];
  }
  
  public int index( final int x, final int y ) {
    return x * dimY + y;
  }
  
  @ Override
  public boolean setElementAt( final E element, final int x, final int y ) {
    elements[ index( x, y ) ] = element;
    return true;
  }
}