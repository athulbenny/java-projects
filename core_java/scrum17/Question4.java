package scrum17;


class Rectangle  implements Resizable{

	@Override
	public int resizeWidth(int width) {
		return width/5;
	}

	@Override
	public int resizeHeight(int height) {
		return height/5;
	}
	
}

public class Question4 {

	public static void main(String[] args) {
		
		Rectangle r = new Rectangle();
		int width = r.resizeWidth(25);
		int height = r.resizeHeight(50);
		
		System.out.println("new size: " + width + "X" + height);
	}

}
