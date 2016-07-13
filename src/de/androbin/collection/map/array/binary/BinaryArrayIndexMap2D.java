package de.androbin.collection.map.array.binary;

import de.androbin.collection.map.*;
import de.androbin.collection.map.array.*;

public class BinaryArrayIndexMap2D<E extends Indexable> extends ArrayIndexMap2D<E>
{
	public BinaryArrayIndexMap2D( final E[] elements, final int dimX, final int dimY )
	{
		this( elements, new int[ 1 << dimX << dimY ], dimX, dimY );
	}
	
	public BinaryArrayIndexMap2D( final E[] elements, final int[] indices, final int dimX, final int dimY )
	{
		super( elements, indices, dimX, dimY );
	}
	
	@ Override
	public int index( final int x, final int y )
	{
		return x << dimY | y;
	}
}