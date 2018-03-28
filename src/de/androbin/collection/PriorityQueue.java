package de.androbin.collection;

import java.util.*;

public final class PriorityQueue<E extends Comparable<E>> extends AbstractQueue<E> {
  private CyclicArrayList<E> nodes;
  
  public PriorityQueue() {
    this( 100 );
  }
  
  public PriorityQueue( final int initialCapacity ) {
    nodes = new CyclicArrayList<>( initialCapacity );
  }
  
  public PriorityQueue( final PriorityQueue<E> queue ) {
    nodes = new CyclicArrayList<>( queue.nodes );
  }
  
  @ Override
  public void clear() {
    nodes.clear();
  }
  
  @ Override
  public boolean contains( final Object o ) {
    @ SuppressWarnings( "unchecked" )
    final int index = indexOf( (E) o );
    return index != -1;
  }
  
  public int indexOf( final E element ) {
    final int m = indexOfPriority( element );
    
    if ( m != -1 ) {
      E node;
      
      for ( int i = m; i >= 0; i-- ) {
        if ( ( node = nodes.get( i ) ) == element ) {
          return i;
        } else if ( element.compareTo( node ) != 0 ) {
          break;
        }
      }
      
      for ( int i = m + 1; i < nodes.size(); i++ ) {
        if ( ( node = nodes.get( i ) ) == element ) {
          return i;
        } else if ( element.compareTo( node ) != 0 ) {
          break;
        }
      }
    }
    
    return -1;
  }
  
  public int indexOfInsertion( final E element ) {
    if ( isEmpty() ) {
      return 0;
    }
    
    int l = 0;
    int r = nodes.size() - 1;
    
    while ( true ) {
      final int m = ( l + r ) / 2;
      final int c = element.compareTo( nodes.get( m ) );
      
      if ( c == 0 ) {
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
  
  public int indexOfPriority( final E element ) {
    if ( isEmpty() ) {
      return -1;
    }
    
    int l = 0;
    int r = nodes.size() - 1;
    
    int m;
    int c;
    
    while ( true ) {
      m = ( l + r ) / 2;
      c = element.compareTo( nodes.get( m ) );
      
      if ( c == 0 ) {
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
  
  @ Override
  public Iterator<E> iterator() {
    return nodes.iterator();
  }
  
  @ Override
  public boolean offer( final E element ) {
    nodes.add( indexOfInsertion( element ), element );
    return true;
  }
  
  @ Override
  public E peek() {
    return nodes.get( 0 );
  }
  
  @ Override
  public E poll() {
    return nodes.remove( 0 );
  }
  
  public void reorder( final int index ) {
    final E node = nodes.get( index );
    final int insertIndex = indexOfInsertion( node );
    
    if ( index < insertIndex ) {
      nodes.move( index, insertIndex - 1 );
    } else {
      nodes.move( index, insertIndex );
    }
  }
  
  public void reorder( final E element, final Runnable action ) {
    final int index = indexOf( element );
    action.run();
    reorder( index );
  }
  
  @ Override
  public int size() {
    return nodes.size();
  }
}