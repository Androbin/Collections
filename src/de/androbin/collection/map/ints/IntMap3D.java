package de.androbin.collection.map.ints;

import java.io.*;

public interface IntMap3D
{
	int getElementAt( final int x, final int y, final int z );
	
	boolean isElementAt( final int x, final int y, final int z );
	
	void load( final File file ) throws IOException;
	
	boolean removeElementAt( final int x, final int y, final int z );
	
	void save( final File file ) throws IOException;
	
	default boolean setElementAt( final int element, final int x, final int y, final int z )
	{
		return false;
	}
}