package basic;

public class Student {
	private int studentId;
	private String name;
	private String address;
	
	Student(int studentId,String name,String address){
		this.studentId = studentId;
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", address=" + address + "]";
	}
	
}

enum Biryani{
	CB,MB,FB,PB,VG
}

class Testrt{
	public static void main(String args[]) {
		Student student = new Student(1001,"name","address");
		System.out.println("Student details are: " + student);// refernce will be printed
		Biryani b = Biryani.CB;
		System.out.println(b);// direct value is given since toString method is internaly implemnted by enum
		Biryani[] biryanis  = Biryani.values();
		for(Biryani bir: biryanis ) {
		System.out.println(bir+" index::"+bir.ordinal());
	}
	}
}
