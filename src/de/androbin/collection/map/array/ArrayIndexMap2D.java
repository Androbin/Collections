package de.androbin.collection.map.array;

import de.androbin.collection.map.*;
import java.io.*;

public class ArrayIndexMap2D<E extends Indexable> implements IndexMap2D<E>
{
	public final E[]	elements;
	public final int[]	indices;
	
	public final int	dimX;
	public final int	dimY;
	
	public ArrayIndexMap2D( final E[] elements, final int dimX, final int dimY )
	{
		this( elements, new int[ dimX * dimY ], dimX, dimY );
	}
	
	public ArrayIndexMap2D( final E[] elements, final int[] indices, final int dimX, final int dimY )
	{
		this.elements = elements;
		this.indices = indices;
		
		this.dimX = dimX;
		this.dimY = dimY;
	}
	
	@ Override
	public E getElementAt( final int x, final int y )
	{
		final int index = index( x, y );
		return indices[ index ] == 0 ? null : elements[ indices[ index ] - 1 ];
	}
	
	public int index( final int x, final int y )
	{
		return x * dimY + y;
	}
	
	@ Override
	public boolean isElementAt( final int x, final int y )
	{
		return indices[ index( x, y ) ] != 0;
	}
	
	@ Override
	public void load( final File file ) throws IOException
	{
		final Reader reader = new BufferedReader( new FileReader( file ) );
		
		for ( int i = 0; i < indices.length; i++ )
		{
			indices[ i ] = reader.read();
		}
		
		reader.close();
	}
	
	@ Override
	public boolean removeElementAt( final int x, final int y )
	{
		indices[ index( x, y ) ] = 0;
		return true;
	}
	
	@ Override
	public void save( final File file ) throws IOException
	{
		final Writer writer = new BufferedWriter( new FileWriter( file ) );
		
		for ( int i = 0; i < indices.length; i++ )
		{
			writer.write( indices[ i ] );
		}
		
		writer.flush();
		writer.close();
	}
	
	@ Override
	public boolean setElementIndexAt( final int index, final int x, final int y )
	{
		indices[ index( x, y ) ] = index + 1;
		return true;
	}
}