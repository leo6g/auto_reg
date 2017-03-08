
public class Student {
	private String name;
	public Student(){};
	public Student(String name){
		do1();
		this.name = name;
	}
	public void do1(){
		System.out.println("dododo");
		System.out.println(name);
		System.out.println(this);
	}
}
