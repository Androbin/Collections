package de.androbin.collection.map.ints;

import java.io.*;

public interface IntMap2D
{
	int getElementAt( final int x, final int y );
	
	boolean isElementAt( final int x, final int y );
	
	void load( final File file ) throws IOException;
	
	boolean removeElementAt( final int x, final int y );
	
	void save( final File file ) throws IOException;
	
	default boolean setElementAt( final int element, final int x, final int y )
	{
		return false;
	}
}