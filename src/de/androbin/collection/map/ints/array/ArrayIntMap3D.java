package de.androbin.collection.map.ints.array;

import static de.androbin.math.util.ints.IntMathUtil.*;
import de.androbin.collection.map.ints.*;
import java.io.*;

public class ArrayIntMap3D implements IntMap3D
{
	public final int[]		elements;
	public final boolean[]	existance;
	
	public final int		dimX;
	public final int		dimY;
	public final int		dimZ;
	
	public ArrayIntMap3D( final int dimX, final int dimY, final int dimZ )
	{
		this( new int[ dimX * dimY * dimZ ], dimX, dimY, dimZ );
	}
	
	public ArrayIntMap3D( final int[] elements, final int dimX, final int dimY, final int dimZ )
	{
		this( elements, new boolean[ elements.length ], dimX, dimY, dimZ );
	}
	
	public ArrayIntMap3D( final int[] elements, final boolean[] existance, final int dimX, final int dimY, final int dimZ )
	{
		this.elements = elements;
		this.existance = existance;
		
		this.dimX = dimX;
		this.dimY = dimY;
		this.dimZ = dimZ;
	}
	
	@ Override
	public int getElementAt( final int x, final int y, final int z )
	{
		return elements[ index( x, y, z ) ];
	}
	
	public int index( final int x, final int y, final int z )
	{
		return ( x * dimY + y ) * dimZ + z;
	}
	
	@ Override
	public boolean isElementAt( final int x, final int y, final int z )
	{
		return existance[ index( x, y, z ) ];
	}
	
	@ Override
	public void load( final File file ) throws IOException
	{
		final Reader reader = new BufferedReader( new FileReader( file ) );
		
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
		
		reader.close();
	}
	
	@ Override
	public boolean removeElementAt( final int x, final int y, final int z )
	{
		existance[ index( x, y, z ) ] = false;
		return true;
	}
	
	@ Override
	public void save( final File file ) throws IOException
	{
		final Writer writer = new BufferedWriter( new FileWriter( file ) );
		
		for ( int i = 0; i < elements.length; i++ )
		{
			writer.write( existance[ i ] ? unsign( elements[ i ] ) + 1 : 0 );
		}
		
		writer.flush();
		writer.close();
	}
	
	@ Override
	public boolean setElementAt( final int element, final int x, final int y, final int z )
	{
		final int index = index( x, y, z );
		elements[ index ] = element;
		existance[ index ] = true;
		return true;
	}
}