package de.androbin.collection.map;

import de.androbin.function.*;
import jdk.nashorn.internal.objects.annotations.*;

public interface Map3D<E> extends TriIntFunction<E>
{
	@ Override
	default E apply( final int x, final int y, final int z )
	{
		return getElementAt( x, y, z );
	}
	
	@ Getter
	default E getElementAt( final int x, final int y, final int z )
	{
		return null;
	}
	
	@ Getter
	default boolean isElementAt( final int x, final int y, final int z )
	{
		return getElementAt( x, y, z ) != null;
	}
	
	@ Setter
	default boolean removeElementAt( final int x, final int y, final int z )
	{
		return setElementAt( null, x, y, z );
	}
	
	@ Setter
	default boolean setElementAt( final E element, final int x, final int y, final int z )
	{
		return false;
	}
}