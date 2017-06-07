package de.androbin.collection.map.array;

import de.androbin.collection.map.*;
import java.io.*;

public class ArrayIndexMap3D<E extends Indexable> implements IndexMap3D<E> {
  public final E[] elements;
  public final int[] indices;
  
  public final int dimX;
  public final int dimY;
  public final int dimZ;
  
  public ArrayIndexMap3D( final E[] elements, final int dimX, final int dimY, final int dimZ ) {
    this( elements, new int[ dimX * dimY * dimZ ], dimX, dimY, dimZ );
  }
  
  public ArrayIndexMap3D( final E[] elements, final int[] indices, final int dimX, final int dimY,
      final int dimZ ) {
    this.elements = elements;
    this.indices = indices;
    
    this.dimX = dimX;
    this.dimY = dimY;
    this.dimZ = dimZ;
  }
  
  @ Override
  public E getElementAt( final int x, final int y, final int z ) {
    final int index = index( x, y, z );
    return indices[ index ] == 0 ? null : elements[ indices[ index ] - 1 ];
  }
  
  public int index( final int x, final int y, final int z ) {
    return ( x * dimY + y ) * dimZ + z;
  }
  
  @ Override
  public boolean isElementAt( final int x, final int y, final int z ) {
    return indices[ index( x, y, z ) ] != 0;
  }
  
  @ Override
  public void load( final File file ) throws IOException {
    try ( final Reader reader = new BufferedReader( new FileReader( file ) ) ) {
      for ( int i = 0; i < indices.length; i++ ) {
        indices[ i ] = reader.read();
      }
    }
  }
  
  @ Override
  public boolean removeElementAt( final int x, final int y, final int z ) {
    indices[ index( x, y, z ) ] = 0;
    return true;
  }
  
  @ Override
  public void save( final File file ) throws IOException {
    try ( final Writer writer = new BufferedWriter( new FileWriter( file ) ) ) {
      for ( int i = 0; i < indices.length; i++ ) {
        writer.write( indices[ i ] );
      }
    }
  }
  
  @ Override
  public boolean setElementIndexAt( final int index, final int x, final int y, final int z ) {
    indices[ index( x, y, z ) ] = index + 1;
    return true;
  }
}