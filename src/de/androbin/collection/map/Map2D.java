package de.androbin.collection.map;

import de.androbin.function.*;
import jdk.nashorn.internal.objects.annotations.*;

public interface Map2D<E> extends BiIntFunction<E>
{
	@ Override
	default E apply( final int x, final int y )
	{
		return getElementAt( x, y );
	}
	
	@ Getter
	default E getElementAt( final int x, final int y )
	{
		return null;
	}
	
	@ Getter
	default boolean isElementAt( final int x, final int y )
	{
		return getElementAt( x, y ) != null;
	}
	
	@ Setter
	default boolean removeElementAt( final int x, final int y )
	{
		return setElementAt( null, x, y );
	}
	
	@ Setter
	default boolean setElementAt( final E element, final int x, final int y )
	{
		return false;
	}
}