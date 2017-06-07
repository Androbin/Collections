package de.androbin.collection;

import java.lang.reflect.*;

public final class CyclicArrayUtil {
  private CyclicArrayUtil() {
  }
  
  public static void cyclicarraycopy( final Object array, final int srcPos, final int destPos,
      final int length ) {
    if ( srcPos == destPos || length == 0 ) {
      return;
    }
    
    assert array != null;
    assert length > 0;
    
    final int arrayLength = Array.getLength( array );
    
    assert srcPos >= 0 && srcPos < arrayLength;
    assert destPos >= 0 && destPos < arrayLength;
    
    final int splitSrcLeft = arrayLength - srcPos;
    final int splitSrcRight = srcPos + length - arrayLength;
    final int splitDestLeft = arrayLength - destPos;
    final int splitDestRight = destPos + length - arrayLength;
    
    if ( splitSrcRight <= 0 ) {
      if ( splitDestRight <= 0 ) {
        System.arraycopy( array, srcPos, array, destPos, length );
      } else {
        if ( splitDestRight < srcPos ) {
          // right to left
          System.arraycopy( array, srcPos + splitDestLeft, array, 0, splitDestRight );
          System.arraycopy( array, srcPos, array, destPos, splitDestLeft );
        } else {
          assert destPos > srcPos + length;
          
          // left to right
          System.arraycopy( array, srcPos, array, destPos, splitDestLeft );
          System.arraycopy( array, srcPos + splitDestLeft, array, 0, splitDestRight );
        }
      }
    } else {
      if ( splitDestRight <= 0 ) {
        if ( splitSrcRight < destPos ) {
          // left to right
          System.arraycopy( array, srcPos, array, destPos, splitSrcLeft );
          System.arraycopy( array, 0, array, destPos + splitSrcLeft, splitSrcRight );
        } else {
          assert srcPos > destPos + length;
          
          // right to left
          System.arraycopy( array, 0, array, destPos + splitSrcLeft, splitSrcRight );
          System.arraycopy( array, srcPos, array, destPos, splitSrcLeft );
        }
      } else {
        if ( srcPos <= destPos ) {
          assert destPos + length - arrayLength < srcPos;
          
          // right to left
          System.arraycopy( array, 0, array, splitDestRight - splitSrcRight, splitSrcRight );
          System.arraycopy( array, srcPos + splitDestLeft, array, 0, splitSrcLeft - splitDestLeft );
          System.arraycopy( array, srcPos, array, destPos, splitDestLeft );
        } else {
          assert srcPos + length - arrayLength < destPos;
          
          // left to right
          System.arraycopy( array, srcPos, array, destPos, splitSrcLeft );
          System.arraycopy( array, srcPos + splitSrcLeft, array, 0, splitDestLeft - splitSrcLeft );
          System.arraycopy( array, splitSrcRight - splitDestRight, array, 0, splitDestRight );
        }
      }
    }
  }
}