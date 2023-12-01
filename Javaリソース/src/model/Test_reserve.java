package model;

import java.util.ArrayList;

public class Test_reserve {
public static void main(String[] args) throws IdealException {
	ArrayList<Reserve> rl = Reserve.getReserveList(1);
	for(Reserve r:rl) {
		System.out.println(r);
	}
}
}
