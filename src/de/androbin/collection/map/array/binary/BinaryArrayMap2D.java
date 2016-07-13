package de.androbin.collection.map.array.binary;

import de.androbin.collection.map.array.*;

public class BinaryArrayMap2D<E> extends ArrayMap2D<E>
{
	public BinaryArrayMap2D( final E[] elements, final int dimX, final int dimY )
	{
		super( elements, dimX, dimY );
	}
	
	@ Override
	public int index( final int x, final int y )
	{
		return x << dimY | y;
	}
}