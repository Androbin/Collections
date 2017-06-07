package de.androbin.collection.map.array.binary;

import de.androbin.collection.map.array.*;

public class BinaryArrayMap3D<E> extends ArrayMap3D<E> {
  public BinaryArrayMap3D( final E[] elements, final int dimX, final int dimY, final int dimZ ) {
    super( elements, dimX, dimY, dimZ );
  }
  
  @ Override
  public int index( final int x, final int y, final int z ) {
    return ( x << dimY | y ) << dimZ | z;
  }
}