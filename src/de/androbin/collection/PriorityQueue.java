package de.androbin.collection;

import static de.androbin.collection.CyclicArrayUtil.*;
import static de.androbin.math.util.ints.IntMathUtil.*;
import java.util.*;
import java.util.function.*;

@ SuppressWarnings( "unchecked" )
public final class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
  private Object[] nodes;
  private int count;
  private int pointer;
  
  public PriorityQueue() {
    this( 100 );
  }
  
  public PriorityQueue( final int initialCapacity ) {
    this.nodes = new Object[ initialCapacity ];
  }
  
  public E extract( final int index ) {
    final E node = getNode( index );
    
    if ( index << 1 < count ) {
      cyclicarraycopy( nodes, pointer, shiftDown( pointer + 1, nodes.length ), index );
      pointer = shiftDown( pointer + 1, nodes.length );
    } else {
      cyclicarraycopy( nodes, shiftDown( pointer + index + 1, nodes.length ),
          shiftDown( pointer + index, nodes.length ), count - index - 1 );
    }
    
    count--;
    return node;
  }
  
  private E getNode( final int index ) {
    return (E) nodes[ shiftDown( pointer + index, nodes.length ) ];
  }
  
  public int indexOf( final E e ) {
    final int m = indexOfPriority( e );
    
    if ( m != -1 ) {
      E node;
      
      for ( int i = m; i >= 0; i-- ) {
        if ( ( node = getNode( i ) ) == e ) {
          return i;
        } else if ( e.compareTo( node ) != 0 ) {
          break;
        }
      }
      
      for ( int i = m + 1; i < count; i++ ) {
        if ( ( node = getNode( i ) ) == e ) {
          return i;
        } else if ( e.compareTo( node ) != 0 ) {
          break;
        }
      }
    }
    
    return -1;
  }
  
  public int indexOfInsertion( final E e ) {
    int l = 0;
    int r = count - 1;
    
    while ( true ) {
      final int m = ( l + r ) / 2;
      final int c = e.compareTo( getNode( m ) );
      
      /**/ if ( c == 0 ) {
        return m + 1;
      } else if ( l >= r ) {
        return c > 0 ? m + 1 : m;
      } else if ( c > 0 ) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
  }
  
  public int indexOfPriority( final E e ) {
    if ( count == 0 ) {
      return -1;
    }
    
    int l = 0;
    int r = count - 1;
    
    int m;
    int c;
    
    while ( true ) {
      m = ( l + r ) / 2;
      c = e.compareTo( getNode( m ) );
      
      /**/ if ( c == 0 ) {
        return m;
      } else if ( l >= r ) {
        return -1;
      } else if ( c > 0 ) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
  }
  
  public void insert( final int index, final E e ) {
    if ( nodes.length <= count ) {
      this.nodes = toArray( count << 1 );
      this.pointer = 0;
    }
    
    if ( index << 1 < count ) {
      cyclicarraycopy( nodes, pointer, shiftUp( pointer - 1, nodes.length ), index + 1 );
      pointer = shiftUp( pointer - 1, nodes.length );
    } else {
      cyclicarraycopy( nodes, shiftDown( pointer + index, nodes.length ),
          shiftDown( pointer + index + 1, nodes.length ), count - index );
    }
    
    count++;
    setNode( index, e );
  }
  
  public void intoArray( final Object[] array ) {
    if ( pointer + count <= nodes.length ) {
      System.arraycopy( nodes, pointer, array, 0, count );
    } else {
      System.arraycopy( nodes, pointer, array, 0, nodes.length - pointer );
      System.arraycopy( nodes, 0, array, nodes.length - pointer, pointer + count - nodes.length );
    }
  }
  
  public void reorder( final E e ) {
    reorder( indexOf( e ) );
  }
  
  public void reorder( final int index ) {
    final E node = getNode( index );
    final int insertIndex = indexOfInsertion( node );
    
    if ( index <= insertIndex ) {
      cyclicarraycopy( nodes, shiftDown( pointer + index + 1, nodes.length ),
          shiftDown( pointer + index, nodes.length ), insertIndex - index - 1 );
    } else {
      cyclicarraycopy( nodes, shiftDown( pointer + insertIndex + 1, nodes.length ),
          shiftDown( pointer + insertIndex + 2, nodes.length ), index - insertIndex - 1 );
    }
    
    setNode( insertIndex, node );
  }
  
  private void setNode( final int index, final E e ) {
    nodes[ shiftDown( pointer + index, nodes.length ) ] = e;
  }
  
  public Object[] toArray( final int capacity ) {
    final Object[] a = new Object[ capacity ];
    intoArray( a );
    return a;
  }
  
  @ Override
  public int size() {
    return count;
  }
  
  @ Override
  public boolean isEmpty() {
    return count == 0;
  }
  
  @ Override
  public boolean contains( final Object o ) {
    return indexOf( (E) o ) != -1;
  }
  
  @ Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int index;
      
      @ Override
      public void forEachRemaining( final Consumer< ? super E> action ) {
        while ( hasNext() ) {
          action.accept( next() );
        }
      }
      
      @ Override
      public boolean hasNext() {
        return index < count;
      }
      
      @ Override
      public E next() {
        return getNode( index++ );
      }
      
      @ Override
      public void remove() {
        extract( index - 1 );
      }
    };
  }
  
  @ Override
  public Object[] toArray() {
    return toArray( nodes.length );
  }
  
  @ Override
  public <T> T[] toArray( final T[] a ) {
    final T[] b = a.length >= nodes.length ? a : Arrays.copyOf( a, nodes.length );
    intoArray( b );
    return b;
  }
  
  @ Override
  public boolean remove( final Object o ) {
    final int index = indexOf( (E) o );
    
    if ( index == -1 ) {
      return false;
    } else {
      extract( index );
      return true;
    }
  }
  
  @ Override
  public boolean containsAll( final Collection< ? > c ) {
    return c.parallelStream().allMatch( this::contains );
  }
  
  @ Override
  public boolean addAll( final Collection< ? extends E> c ) {
    // prevent short circuting
    return c.parallelStream().map( this::add ).reduce( ( a, b ) -> a || b ).get();
  }
  
  @ Override
  public boolean removeAll( final Collection< ? > c ) {
    // prevent short circuting
    return c.parallelStream().map( this::remove ).reduce( ( a, b ) -> a || b ).get();
  }
  
  @ Override
  public boolean retainAll( final Collection< ? > c ) {
    final Iterator<E> iter = iterator();
    
    boolean changed = false;
    
    while ( iter.hasNext() ) {
      if ( !c.contains( iter.next() ) ) {
        iter.remove();
        changed = true;
      }
    }
    
    return changed;
  }
  
  @ Override
  public void clear() {
    Arrays.fill( this.nodes, null );
    this.pointer = 0;
    this.count = 0;
  }
  
  @ Override
  public boolean add( final E e ) {
    return offer( e );
  }
  
  @ Override
  public boolean offer( final E e ) {
    if ( count == 0 ) {
      setNode( 0, e );
      count++;
    } else {
      insert( indexOfInsertion( e ), e );
    }
    
    return true;
  }
  
  @ Override
  public E remove() {
    return poll();
  }
  
  @ Override
  public E poll() {
    count--;
    final E node = (E) nodes[ pointer ];
    pointer = shiftDown( pointer + 1, nodes.length );
    return node;
  }
  
  @ Override
  public E element() {
    return peek();
  }
  
  @ Override
  public E peek() {
    return (E) nodes[ pointer ];
  }
}