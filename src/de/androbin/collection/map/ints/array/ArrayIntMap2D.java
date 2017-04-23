package de.androbin.collection.map.ints.array;

import static de.androbin.math.util.ints.IntMathUtil.*;
import de.androbin.collection.map.ints.*;
import java.io.*;

public class ArrayIntMap2D implements IntMap2D
{
	public final int[]		elements;
	public final boolean[]	existance;
	
	public final int		dimX;
	public final int		dimY;
	
	public ArrayIntMap2D( final int dimX, final int dimY )
	{
		this( new int[ dimX * dimY ], dimX, dimY );
	}
	
	public ArrayIntMap2D( final int[] elements, final int dimX, final int dimY )
	{
		this( elements, new boolean[ elements.length ], dimX, dimY );
	}
	
	public ArrayIntMap2D( final int[] elements, final boolean[] existance, final int dimX, final int dimY )
	{
		this.elements = elements;
		this.existance = existance;
		
		this.dimX = dimX;
		this.dimY = dimY;
	}
	
	@ Override
	public int getElementAt( final int x, final int y )
	{
		return elements[ index( x, y ) ];
	}
	
	public int index( final int x, final int y )
	{
		return x * dimY + y;
	}
	
	@ Override
	public boolean isElementAt( final int x, final int y )
	{
		return existance[ index( x, y ) ];
	}
	
	@ Override
	public void load( final File file ) throws IOException
	{
		try ( final Reader reader = new BufferedReader( new FileReader( file ) ) )
		{
			for ( int i = 0; i < elements.length; i++ )
			{
				final int b = reader.read();
				
				if ( b > 0 )
				{
					elements[ i ] = sign( b - 1 );
					existance[ i ] = true;
				}
				else
				{
					existance[ i ] = false;
				}
			}
		}
	}
	
	@ Override
	public boolean removeElementAt( final int x, final int y )
	{
		existance[ index( x, y ) ] = false;
		return true;
	}
	
	@ Override
	public void save( final File file ) throws IOException
	{
		try ( final Writer writer = new BufferedWriter( new FileWriter( file ) ) )
		{
			for ( int i = 0; i < elements.length; i++ )
			{
				writer.write( existance[ i ] ? unsign( elements[ i ] ) + 1 : 0 );
			}
		}
	}
	
	@ Override
	public boolean setElementAt( final int element, final int x, final int y )
	{
		final int index = index( x, y );
		elements[ index ] = element;
		existance[ index ] = true;
		return true;
	}
}