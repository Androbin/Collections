package de.androbin.collection.map.ints.array.binary;

import de.androbin.collection.map.ints.array.*;

public class BinaryArrayIntMap3D extends ArrayIntMap3D
{
	public BinaryArrayIntMap3D( final int dimX, final int dimY, final int dimZ )
	{
		this( new int[ 1 << dimX << dimY << dimZ ], dimX, dimY, dimZ );
	}
	
	public BinaryArrayIntMap3D( final int[] elements, final int dimX, final int dimY, final int dimZ )
	{
		super( elements, dimX, dimY, dimZ );
	}
	
	public BinaryArrayIntMap3D( final int[] elements, final boolean[] existance, final int dimX, final int dimY, final int dimZ )
	{
		super( elements, existance, dimX, dimY, dimZ );
	}
	
	@ Override
	public int index( final int x, final int y, final int z )
	{
		return ( x << dimY | y ) << dimZ | z;
	}
}