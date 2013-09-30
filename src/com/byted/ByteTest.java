package com.byted;

public class ByteTest {

	/**
	 * int to byte
	 * @param res
	 * @return
	 */
	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	/**
	 * byte to int
	 * @param res
	 * @return
	 */
	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) | ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}
	
	/**
	 * short to byte
	 * @param b
	 * @param s
	 * @param index
	 */
	public static void short2byte(byte b[], short s, int index) {
		b[index + 1] = (byte) (s >> 8);
		b[index + 0] = (byte) (s >> 0);
	}

	/**
	 * byte to short
	 * @param b
	 * @param index
	 * @return
	 */
	public static short byte2short(byte[] b, int index) {
		return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
	}
	
	public static void char2byte(byte[] bb, char ch, int index) {
		int temp = (int) ch;
		for (int i = 0; i < 2; i++) {
			// 将最高位保存在最低位
			bb[index + i] = new Integer(temp & 0xff).byteValue();
			temp = temp >> 8; // 向右移8位
		}
	}

	public static char byte2char(byte[] b, int index) {
		int s = 0;
		if (b[index + 1] > 0)
			s += b[index + 1];
		else
			s += 256 + b[index + 0];
		s *= 256;
		if (b[index + 0] > 0)
			s += b[index + 1];
		else
			s += 256 + b[index + 0];
		char ch = (char) s;
		return ch;
	}
	
	public static void float2byte(byte[] bb, float x, int index) {
		int l = Float.floatToIntBits(x);
		for (int i = 0; i < 4; i++) {
			bb[index + i] = new Integer(l).byteValue();
			l = l >> 8;
		}
	}

	public static float byte2float(byte[] b, int index) {
		int l;
		l = b[index + 0];
		l &= 0xff;
		l |= ((long) b[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 3] << 24);
		return Float.intBitsToFloat(l);
	}

	public static void double2byte(byte[] bb, double x, int index) {
		long l = Double.doubleToLongBits(x);
		for (int i = 0; i < 4; i++) {
			bb[index + i] = new Long(l).byteValue();
			l = l >> 8;
		}
	}

	public static double byte2double(byte[] b, int index) {
		long l;
		l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[7] << 56);
		return Double.longBitsToDouble(l);
	}


	public static void main(String[] args) {
		byte[] targets = int2byte(123456789);
		System.out.println(targets[0]);
		System.out.println(targets[1]);
		System.out.println(targets[2]);
		System.out.println(targets[3]);
		
		System.out.println(targets[0] & 0xff);
		System.out.println((targets[1] << 8) & 0xff00);
		System.out.println((targets[2] << 24) >> 8);
		System.out.println(targets[3] << 24);
		
		System.out.println((int)'a');
	}
}