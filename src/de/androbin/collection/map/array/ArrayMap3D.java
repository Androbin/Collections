package de.androbin.collection.map.array;

import de.androbin.collection.map.*;

public class ArrayMap3D<E> implements Map3D<E>
{
	public final E[]	elements;
	
	public final int	dimX;
	public final int	dimY;
	public final int	dimZ;
	
	public ArrayMap3D( final E[] elements, final int dimX, final int dimY, final int dimZ )
	{
		this.elements = elements;
		
		this.dimX = dimX;
		this.dimY = dimY;
		this.dimZ = dimZ;
	}
	
	@ Override
	public E getElementAt( final int x, final int y, final int z )
	{
		return elements[ index( x, y, z ) ];
	}
	
	public int index( final int x, final int y, final int z )
	{
		return ( x * dimY + y ) * dimZ + z;
	}
	
	@ Override
	public boolean setElementAt( final E element, final int x, final int y, final int z )
	{
		elements[ index( x, y, z ) ] = element;
		return true;
	}
}