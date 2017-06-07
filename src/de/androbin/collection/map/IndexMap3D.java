package de.androbin.collection.map;

import java.io.*;

public interface IndexMap3D<E extends Indexable> extends Map3D<E> {
  void load( File file ) throws IOException;
  
  void save( File file ) throws IOException;
  
  @ Override
  default boolean setElementAt( final E element, final int x, final int y, final int z ) {
    if ( element == null ) {
      return removeElementAt( x, y, z );
    }
    
    return setElementIndexAt( element.getIndex(), x, y, z );
  }
  
  default boolean setElementIndexAt( int index, int x, int y, int z ) {
    return false;
  }
}