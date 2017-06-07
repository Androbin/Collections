package de.androbin.collection.map.ints;

import java.io.*;

public interface IntMap2D {
  int getElementAt( int x, int y );
  
  boolean isElementAt( int x, int y );
  
  void load( File file ) throws IOException;
  
  boolean removeElementAt( int x, int y );
  
  void save( File file ) throws IOException;
  
  default boolean setElementAt( int element, int x, int y ) {
    return false;
  }
}