package de.androbin.collection.map.ints;

import java.io.*;

public interface IntMap3D {
  int getElementAt( int x, int y, int z );
  
  boolean isElementAt( int x, int y, int z );
  
  void load( File file ) throws IOException;
  
  boolean removeElementAt( int x, int y, int z );
  
  void save( File file ) throws IOException;
  
  default boolean setElementAt( int element, int x, int y, int z ) {
    return false;
  }
}