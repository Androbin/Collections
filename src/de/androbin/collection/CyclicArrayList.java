package de.androbin.collection;

import static de.androbin.collection.CyclicArrayUtil.*;
import static de.androbin.math.util.ints.IntMathUtil.*;
import java.util.*;

public final class CyclicArrayList<E> extends AbstractList<E> {
  private Object[] array;
  private int count;
  private int pointer;
  
  public CyclicArrayList() {
    this( 100 );
  }
  
  public CyclicArrayList( final int initialCapacity ) {
    array = new Object[ initialCapacity ];
  }
  
  public CyclicArrayList( final CyclicArrayList<E> list ) {
    array = Arrays.copyOf( list.array, list.count );
    count = list.count;
    pointer = list.pointer;
  }
  
  @ Override
  public void add( final int index, final E element ) {
    if ( array.length <= count ) {
      this.array = toArray( count << 1 );
      this.pointer = 0;
    }
    
    if ( index << 1 < count ) {
      cyclicarraycopy( array, pointer, shiftUp( pointer - 1, array.length ), index + 1 );
      pointer = shiftUp( pointer - 1, array.length );
    } else {
      cyclicarraycopy( array, indexFor( index ),
          shiftDown( pointer + index + 1, array.length ), count - index );
    }
    
    count++;
    set( index, element );
  }
  
  @ Override
  public void clear() {
    count = 0;
  }
  
  @ Override
  public E get( final int index ) {
    @ SuppressWarnings( "unchecked" )
    final E element = (E) array[ indexFor( index ) ];
    return element;
  }
  
  private int indexFor( final int index ) {
    return shiftDown( pointer + index, array.length );
  }
  
  public void intoArray( final Object[] target ) {
    if ( pointer + count <= array.length ) {
      System.arraycopy( array, pointer, target, 0, count );
    } else {
      System.arraycopy( array, pointer, target, 0, array.length - pointer );
      System.arraycopy( array, 0, target, array.length - pointer, pointer + count - array.length );
    }
  }
  
  public void move( final int src, final int dest ) {
    if ( src == dest ) {
      return;
    }
    
    final E element = get( src );
    
    if ( src < dest ) {
      cyclicarraycopy( array, indexFor( src + 1 ), indexFor( src ), dest - src );
    } else {
      cyclicarraycopy( array, indexFor( dest ), indexFor( dest + 1 ), src - dest );
    }
    
    set( dest, element );
  }
  
  @ Override
  public E remove( final int index ) {
    final E element = get( index );
    
    if ( index << 1 < count ) {
      cyclicarraycopy( array, pointer, indexFor( 1 ), index );
      pointer = indexFor( 1 );
    } else {
      cyclicarraycopy( array, indexFor( index + 1 ), indexFor( index ), count - index - 1 );
    }
    
    count--;
    return element;
  }
  
  @ Override
  public E set( final int index, final E element ) {
    final E previous = get( index );
    array[ indexFor( index ) ] = element;
    return previous;
  }
  
  @ Override
  public int size() {
    return count;
  }
  
  public Object[] toArray( final int capacity ) {
    final Object[] a = new Object[ capacity ];
    intoArray( a );
    return a;
  }
}