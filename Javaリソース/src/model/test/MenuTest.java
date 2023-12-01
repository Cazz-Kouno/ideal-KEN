// 伊藤、河野
package model.test;

import java.util.ArrayList;

import model.IdealException;
import model.Menu;

public class MenuTest {

	public static void main(String[] args) throws IdealException {
		Menu m = new Menu();

		Menu.getOneMenu(0, 0);
		int i = Menu.updateMenu(m, 12);

		ArrayList<Menu> al = new ArrayList<Menu>();
		al = Menu.getMenu(200);
		System.out.println("al: " + al.get(0).getMenuName());

		ArrayList<Menu> al1 = Menu.getMenuList();
		for (Menu mm : al1) {
			System.out.println("name=" + mm.getMenuName());
		}

		int num = Menu.updateMenu(m, 12);
		System.out.println("num ;" + num);
	}

}
