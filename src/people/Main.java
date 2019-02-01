package people;

public class Main {

	public static void main(String[] args) {
		Person p1 = new Person("first","last",20);
		System.out.println("p1's name is "+p1.getFirstName()+" "+p1.getLastName());
		Student student = new Student("Max","Shi",18,3.9);
		System.out.println(student.getFirstName()+ " " + student.getLastName());
	}

}
