
public class  Student extends RobotTest{
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
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		System.out.println("clone");
		return super.clone();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		System.out.println("equals");
		return super.equals(obj);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("finalize");
		super.finalize();
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		System.out.println("hashCode");
		return super.hashCode();
	}
	
}
