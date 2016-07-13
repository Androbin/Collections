package de.androbin.collection.map.array.binary;

import de.androbin.collection.map.*;
import de.androbin.collection.map.array.*;

public class BinaryArrayIndexMap3D<E extends Indexable> extends ArrayIndexMap3D<E>
{
	public BinaryArrayIndexMap3D( final E[] elements, final int dimX, final int dimY, final int dimZ )
	{
		this( elements, new int[ 1 << dimX << dimY << dimZ ], dimX, dimY, dimZ );
	}
	
	public BinaryArrayIndexMap3D( final E[] elements, final int[] indices, final int dimX, final int dimY, final int dimZ )
	{
		super( elements, indices, dimX, dimY, dimZ );
	}
	
	@ Override
	public int index( final int x, final int y, final int z )
	{
		return ( x << dimY | y ) << dimZ | z;
	}
}