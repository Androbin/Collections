package de.androbin.collection.map;

import de.androbin.function.*;

public interface Map3D<E> extends TriIntFunction<E>
{
	@ Override
	default E apply( final int x, final int y, final int z )
	{
		return getElementAt( x, y, z );
	}
	
	default E getElementAt( final int x, final int y, final int z )
	{
		return null;
	}
	
	default boolean isElementAt( final int x, final int y, final int z )
	{
		return getElementAt( x, y, z ) != null;
	}
	
	default boolean removeElementAt( final int x, final int y, final int z )
	{
		return setElementAt( null, x, y, z );
	}
	
	default boolean setElementAt( final E element, final int x, final int y, final int z )
	{
		return false;
	}
}