/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Huyen
 */


import inet.model.NewsDAO;

public class ImageBuffer implements java.io.Serializable {
    static final int NUM_OF_BUFFERS = 16; //2^n (n=4)
    static final ImageBuffer[] bufferArray = new ImageBuffer[NUM_OF_BUFFERS];
    static {
        System.out.print("[ImageBuffer] initializing " + NUM_OF_BUFFERS + " buffers...");
        for (int idx = bufferArray.length-1; idx >= 0; idx--) {
            bufferArray[idx] = new ImageBuffer();
        }
        System.out.println("OK");
    }

    // key: id.toString; value: byte[] content
    private Map hashmap = new HashMap();
    private NewsDAO dao = new NewsDAO();

    /* Private methods */
    private byte[] lookupInternal(String theKey) {
        byte[] theVal = null;
        //synchronized (hashmap) {
            theVal = (byte[]) hashmap.get(theKey);
        //}
        if (theVal != null) {
           // System.out.println("Du lieu tra ve tu cache");
            return theVal;
        }
        // Here theVal == null --> Load the value
        // theVal = ....
        theVal = dao.getImages(new BigDecimal(theKey));
        if (theVal != null) {
            synchronized (hashmap) {
                hashmap.put(theKey, theVal);
            }
        }
        return theVal;
    }
    private void updateInternal(String theKey, byte[] theVal) {
        if (theKey == null || theVal == null)
            return;
        synchronized (hashmap) {
            hashmap.put(theKey, theVal);
        }
    }
    private void removeInternal(String theKey) {
        if (theKey == null)
            return;
        synchronized (hashmap) {
            hashmap.remove(theKey);
        }
    }


    /* Public Class methods */
    public static byte[] lookup(String theKey) {
        int h = theKey.hashCode();
        int bucket = h & (NUM_OF_BUFFERS-1); // pick a buffer
        return bufferArray[bucket].lookupInternal(theKey);
    }
    public static void update(String theKey, byte[] theVal) {
        int h = theKey.hashCode();
        int bucket = h & (NUM_OF_BUFFERS - 1); // pick a buffer
        bufferArray[bucket].updateInternal(theKey, theVal);
    }
    public static void remove(String theKey) {
        int h = theKey.hashCode();
        int bucket = h & (NUM_OF_BUFFERS - 1); // pick a buffer
        bufferArray[bucket].removeInternal(theKey);
    }
}
