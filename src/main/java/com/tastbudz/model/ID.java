package com.tastbudz.model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.tastbudz.json.IDSerializer;


@JsonSerialize(using=IDSerializer.class, include = Inclusion.NON_NULL)
public final class ID implements Serializable {
	private static final long serialVersionUID = -3177806549263911864L;
	private byte[] id;
	
	
	public ID() {
		id = toByteArray(UUID.randomUUID());
	}
	
	private ID(String s) {
		id = toByteArray(UUID.fromString(s));
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
	
	public String getDetails() {
		StringBuffer buffer = new StringBuffer();
		for (byte b : id) {
			if (buffer.length() > 0) buffer.append(" ");
			buffer.append(b);
		}
		return buffer.toString();
	}
	
	public static ID fromBytes(byte[] b) {
		ID id = new ID();
		id.id = b;
		return id;
	}
		
	public static ID fromString(String s) {
		return new ID(s);
	}

	private static byte[] toByteArray(UUID uuid) {
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits()); // order is important here!
	    bb.putLong(uuid.getLeastSignificantBits());
	    return bb.array();
	}
	
	private static UUID toUUID(byte[] byteArray) {
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
