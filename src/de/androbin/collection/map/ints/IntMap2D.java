package de.androbin.collection.map.ints;

import java.io.*;
import jdk.nashorn.internal.objects.annotations.*;

public interface IntMap2D
{
	@ Getter
	int getElementAt( final int x, final int y );
	
	@ Getter
	boolean isElementAt( final int x, final int y );
	
	void load( final File file ) throws IOException;
	
	@ Setter
	boolean removeElementAt( final int x, final int y );
	
	void save( final File file ) throws IOException;
	
	@ Setter
	default boolean setElementAt( final int element, final int x, final int y )
	{
		return false;
	}
}