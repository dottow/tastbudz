package com.tastbudz.model.id;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;


public final class ID implements Serializable {
	private byte[] id;
	
	
	public ID() {
		id = toByteArray(java.util.UUID.randomUUID());
	}
	
	public byte[] getBytes() {
		return id;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + Arrays.hashCode(id);
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    ID other = (ID) obj;
	    if (!Arrays.equals(id, other.id))
	      return false;
	    return true;
	}
	  
	@Override
	public String toString() {
		return toUUID(id).toString();
	}
	
	public static ID fromBytes(byte[] b) {
		ID id = new ID();
		id.id = b;
		return id;
	}
		
	private static byte[] toByteArray(java.util.UUID uuid) {
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits()); // order is important here!
	    bb.putLong(uuid.getLeastSignificantBits());
	    return bb.array();
	}
	
	private static java.util.UUID toUUID(byte[] byteArray) {
	    long msb = 0;
	    long lsb = 0;
	    for (int i = 0; i < 8; i++)
	            msb = (msb << 8) | (byteArray[i] & 0xff);
	    for (int i = 8; i < 16; i++)
	            lsb = (lsb << 8) | (byteArray[i] & 0xff);
	    java.util.UUID result = new java.util.UUID(msb, lsb);
	    return result;
	  }
}
