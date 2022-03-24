package enrolment;


public class Tester {

	public static void main(String[] args) {
		Student a = Student.add("S346234", "Conheo", "02/23/2001");
		Student b = Student.add("S324612", "Adele", "06/12/2002");
		Student c = Student.add("S123458", "Coty", "03/14/2001");
		Student d = Student.add("S332165", "Aeon", "03/15/2002");
		
		Course c1 = Course.add("HuyMai", "Julie", (byte)4);
		Course c2 = Course.add("HuyNguyen", "Westfield", (byte)9);
		
		App ap = new App();
		ap.add();
		System.out.println(StudentEnrolment.getList("s3836278").get(0).getStd().getBirthday());
		
	}

}
