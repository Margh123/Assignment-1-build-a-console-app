package enrolment;

import java.io.IOException;

public class Tester {

	public static void main(String[] args) throws IOException {
		App obj = App.getApp();
		//Student.add("S123456", "HuyMai", "11/02/2001");
		//obj.add();
		System.out.println(StudentEnrolment.getList("s3836278").size());
		obj.printOffered();
		
	}


}
