package de.androbin.collection.map.ints.array.binary;

import de.androbin.collection.map.ints.array.*;

public class BinaryArrayIntMap2D extends ArrayIntMap2D {
  public BinaryArrayIntMap2D( final int dimX, final int dimY ) {
    this( new int[ 1 << dimX << dimY ], dimX, dimY );
  }
  
  public BinaryArrayIntMap2D( final int[] elements, final int dimX, final int dimY ) {
    super( elements, dimX, dimY );
  }
  
  public BinaryArrayIntMap2D( final int[] elements, final boolean[] existance,
      final int dimX, final int dimY ) {
    super( elements, existance, dimX, dimY );
  }
  
  @ Override
  public int index( final int x, final int y ) {
    return x << dimY | y;
  }
}