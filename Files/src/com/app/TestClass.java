package com.app;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

	public static void main(String[] args) {

		List<String> l= new ArrayList<>();
				l.add("avinash");
				l.add("naveen");
				l.add("nani");
				l.add("sai");
				
				for(String s:l){
					System.out.println(s);
				}
				System.out.println(l);
}
}