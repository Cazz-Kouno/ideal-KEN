package model.test;

import java.util.ArrayList;

import model.Course;
import model.Coursectl;
import model.IdealException;

public class CourseTest {

	public static void main(String[] args) throws IdealException {

//				System.out.println("crs.getCourseName(): "
//						+ Course.getCourse(1).getCourseName());
//
		ArrayList<Course> alCrs = new ArrayList<>();
//		
//				alCrs = Course.getOneCourse(1); // 引数 c_Id
//				
//				for(Course al : alCrs) {
//					System.out.println(al.getCourseId());
//					System.out.println(al.getCourseName());
//					System.out.println(al.getDetail());
//					System.out.println(al.getMenuId());
//					System.out.println(al.getMenuName());
//					System.out.println(al.getOrderFlg());
//					System.out.println(al.getPrice());
//					System.out.println(al.getTypeId());
//					System.out.println(al.getTypeName());
//					System.out.println("--------------------------------");
//		           
//				}
//
//		System.out.println("Course.getCourseList().size(): "
//				+ Course.getCourseList().size());
//
//		for (Course crs1 : Course.getCourseList()) {
//			System.out.println(crs1.getTypeName());
//			System.out.println(crs1.getCourseName());
//		}
//		
//		alCrs = Course.getTypeCourseList(100); // 引き数：t_id
//		for(Course crs2 : alCrs) {
//			System.out.println(crs2.getCourseId() + ",");
//			System.out.println(crs2.getCourseName() + ",");
//			System.out.println(crs2.getDetail() + ",");
//			System.out.println(crs2.getOrderFlg() + ",");
//			System.out.println(crs2.getPrice() + ",");
//			System.out.println(crs2.getTypeName());
//			System.out.println("--------------------------------");
//		}
//		
		ArrayList<Coursectl>  alCctl = new ArrayList<>();
		Course.getTypeCourseList(0);
	
		Course crs = new Course();
		Course.updateCourse(crs, 11, alCctl);
	}

}