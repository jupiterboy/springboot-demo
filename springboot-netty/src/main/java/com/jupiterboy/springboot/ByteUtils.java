package com.jupiterboy.springboot;

public final class ByteUtils {
	
	public static String toHex(byte[] bytes) {
		return toHex(bytes," ");
	}

	public static String toHex(byte[] bytes,String split) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
			sb.append(split);
		}
		return sb.toString().trim();
	}
	
	public static String toHex(byte value) {
		byte b1 = (byte)(value & 0xFF);
		return toHex(new byte[]{b1});
	}

	
	public static String toHex(short value) {
		byte b1 = (byte)((value & 0xFF00) >> 8);
		byte b2 = (byte)(value & 0xFF);
		return toHex(new byte[]{b1,b2});
	}
	
	public static String toHex(int value) {
		byte b1 = (byte)((value & 0xFF000000) >> 24);
		byte b2 = (byte)((value & 0x00FF0000) >> 16);
		byte b3 = (byte)((value & 0x0000FF00) >> 8);
		byte b4 = (byte)( value & 0x000000FF);
		return toHex(new byte[]{b1,b2,b3,b4});
	}
	
	public static String toHex(long value) {
		byte b1 = (byte)((value & 0xFF00000000000000L) >> 56);
		byte b2 = (byte)((value & 0x00FF000000000000L) >> 48);
		byte b3 = (byte)((value & 0x0000FF0000000000L) >> 40);
		byte b4 = (byte)((value & 0x000000FF00000000L) >> 32);
		byte b5 = (byte)((value & 0x00000000FF000000L) >> 24);
		byte b6 = (byte)((value & 0x0000000000FF0000L) >> 16);
		byte b7 = (byte)((value & 0x000000000000FF00L) >> 8);
		byte b8 = (byte)( value & 0x00000000000000FFL);
		return toHex(new byte[]{b1,b2,b3,b4,b5,b6,b7,b8});
	}
	
	public static String toHex(float value) {
		int v = Float.floatToIntBits(value);
		return toHex(v);
	}
	
	public static String toHex(double value) {
		long v = Double.doubleToLongBits(value);
		return toHex(v);
	}
	
	public static String toHex(int value,String split) {
		byte b1 = (byte)((value & 0xFF000000) >> 24);
		byte b2 = (byte)((value & 0x00FF0000) >> 16);
		byte b3 = (byte)((value & 0x0000FF00) >> 8);
		byte b4 = (byte)( value & 0x000000FF);
		return toHex(new byte[]{b1,b2,b3,b4},split);
	}
	
	public static String format(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		int MAX_SIZE = 256*16;
		int len = bytes.length > MAX_SIZE ? MAX_SIZE : bytes.length;
		int row = (int)(Math.ceil(len/16.0));
		sb.append("\n");
		sb.append("   | ");
		for(int i=0;i<16;i++){
			sb.append(toHex((byte)i)).append(" ");
		}
		sb.append("\n");
		for(int i=0;i<52;i++){
			sb.append("-");
		}
		sb.append("\n");
		boolean overflow = false;
		for(int i=0;i<row;i++){
			if(i >= 5 && i < (row-5)) {
				if(!overflow) {
					sb.append("    。\n");
					sb.append("    。\n");
					sb.append("    。\n");
					overflow = true;
				}
				continue;
			}
			sb.append(toHex((byte)i)).append(" | ");
			int count = (i+1)*16 <= bytes.length ? 16 : bytes.length % 16;
			for(int j=0;j<count;j++){
				sb.append(toHex(bytes[i*16+j])).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
