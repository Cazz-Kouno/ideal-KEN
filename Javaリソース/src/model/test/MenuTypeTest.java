// 河野
package model.test;

import java.util.ArrayList;

import model.IdealException;
import model.MenuType;

public class MenuTypeTest {
	public static void main(String[] args) throws IdealException {
		ArrayList<MenuType> almt = new ArrayList<>();
		almt = MenuType.getAllType();
		
		for(MenuType mt: almt) {
			System.out.println("mt.getName(): " + mt.getTypeName());
		}
	}
}
