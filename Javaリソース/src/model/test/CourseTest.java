package model.test;

import java.util.ArrayList;

import model.Course;
import model.IdealException;

public class CourseTest {

	public static void main(String[] args) throws IdealException{
		Course crs = new Course();
		
		crs = Course.getCourse(2);
		System.out.println("crs.getCourseName(): " + crs.getCourseName());
	
		ArrayList<Course> alCrs = new ArrayList<>();
		alCrs = Course.getOneCourse(1);
		
		for(Course al : alCrs) {
			System.out.println("al.getCourseId(): " + al.getCourseId());
			System.out.println("al.getCourseName(): " + al.getCourseName());
			System.out.println("al.getDetail(): " + al.getDetail());
			System.out.println("al.getMenuId(): " + al.getMenuId());
			System.out.println("al.getmenuName(): " + al.getmenuName());
			System.out.println("al.getOrderFlg(): " + al.getOrderFlg());
			System.out.println("al.getPrice(): " + al.getPrice());
			System.out.println("al.getTypeId(): " + al.getTypeId());
			System.out.println("al.getTypeName(): " + al.getTypeName());
		}
		
		alCrs = Course.getCourseList(2);
	}
}
