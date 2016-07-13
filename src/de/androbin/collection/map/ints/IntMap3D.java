package de.androbin.collection.map.ints;

import java.io.*;
import jdk.nashorn.internal.objects.annotations.*;

public interface IntMap3D
{
	@ Getter
	int getElementAt( final int x, final int y, final int z );
	
	@ Getter
	boolean isElementAt( final int x, final int y, final int z );
	
	void load( final File file ) throws IOException;
	
	@ Setter
	boolean removeElementAt( final int x, final int y, final int z );
	
	void save( final File file ) throws IOException;
	
	@ Setter
	default boolean setElementAt( final int element, final int x, final int y, final int z )
	{
		return false;
	}
}