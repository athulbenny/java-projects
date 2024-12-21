package basic;

public class Person {

	private String firstName;
	private String middleName;
	private String lastName;
	
	Person(String firstName, String middleName, String lastName){
		this.firstName= firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public String fullName() {
		return firstName + " " + middleName + " " + lastName;
	}
	
	public static void main(String args[]) {
		Person person = new Person("Athul","","Benny");
		System.out.println("full name : " + person.fullName());
	}
	
}
