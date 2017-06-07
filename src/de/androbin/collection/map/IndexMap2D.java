package de.androbin.collection.map;

import java.io.*;

public interface IndexMap2D<E extends Indexable> extends Map2D<E> {
  void load( File file ) throws IOException;
  
  void save( File file ) throws IOException;
  
  @ Override
  default boolean setElementAt( final E element, final int x, final int y ) {
    if ( element == null ) {
      return removeElementAt( x, y );
    }
    
    return setElementIndexAt( element.getIndex(), x, y );
  }
  
  default boolean setElementIndexAt( int index, int x, int y ) {
    return false;
  }
}