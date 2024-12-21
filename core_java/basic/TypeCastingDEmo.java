package basic;

public class TypeCastingDEmo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 130; // -128 to +127 3: -128 2 -127 1 -126
		byte b = (byte)x;
		System.out.println(b);
		int y = b;
		System.out.println(y);
	}

}
