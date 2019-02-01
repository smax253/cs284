package people;

public class Student extends Person {
	private double gpa;
	public Student(String firstName, String lastName, int age, double gpa) {
		super(firstName, lastName, age);
		this.gpa = gpa;
	}
	
	public double getGpa() {
		return this.gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	@Override
	public void printOut() {
		System.out.println(getFirstName() + " " + getLastName() + ": "+getAge()+" "+gpa);
	}
	@Override
	public String getFirstName() {
		return super.getFirstName() + "hello";
	}
	
}
