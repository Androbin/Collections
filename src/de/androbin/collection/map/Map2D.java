package de.androbin.collection.map;

import de.androbin.function.*;

public interface Map2D<E> extends BiIntFunction<E>
{
	@ Override
	default E apply( final int x, final int y )
	{
		return getElementAt( x, y );
	}
	
	default E getElementAt( final int x, final int y )
	{
		return null;
	}
	
	default boolean isElementAt( final int x, final int y )
	{
		return getElementAt( x, y ) != null;
	}
	
	default boolean removeElementAt( final int x, final int y )
	{
		return setElementAt( null, x, y );
	}
	
	default boolean setElementAt( final E element, final int x, final int y )
	{
		return false;
	}
}