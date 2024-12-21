package basic;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Engine engine = new Engine("1200HP0","12V");
		
		Car car = new Car("RED",200,engine);
		
		car.getCarInfo();
	}

}


class Car{
	private String name="CAAR";
	private String color;
	private int maxSpeed;
	private Engine engine;
	
	Car(String color, int maxSpeed, Engine engine){
		this.color = color;
		this.maxSpeed = maxSpeed;
		this.engine = engine;
	}
	
	public void getCarInfo() {
		System.out.println("Car name::" + this.name);
		System.out.println("colcor::" + this.color);
		System.out.println("Max speed::" + this.maxSpeed);
		System.out.println("");
		
		this.engine.getEngineInfo();
	}
}

class Engine{
	private String engineType;
	private String model;
	
	Engine(String engineType,String model){
		this.engineType = engineType;
		this.model = model;
	}
	
	public void start() {
		System.out.println("start"); 
	}
	
	public void stop() {
		System.out.println("stop");
	}

	public void getEngineInfo() {
		System.out.println("type: " + this.engineType);
		System.out.println("model " + this.model);
	}
}

