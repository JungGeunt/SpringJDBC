package org.zerock.JDBCTest;

import org.junit.Test;

//Car 인터페이스 생성
interface Car{
	public void run();
}

public class AnonymousTest {

	@Test //테스트할 대상 메서드에 추가합니다.
	public void good() {
		Car car = new Car() {
			@Override  //익명클래스
			public void run() {
				System.out.println("끼릭끼릭 ~~~");
			}
		}; //인터페이스 Car를 사용해서 객체를 생성 
		
		//보통의 경우 구현하는 별도의 클래스를 생성하여 사용하지만,
		//재사용할 일이 없다면 익명클래스로 선언하여 1회성으로 사용할 수 있음.
		
		car.run();//오버라이드 된 메서드 실행
		//-------------------------------------------
		DB.query(new Car() {
			@Override
			public void run() {
				System.out.println("오버라이딩된 메서드 실행");
				
			}
		});
	}
}
	
//DB 클래스
class DB{
	static void query(Car car) {
		car.run();
		System.out.println("여기서 전달받은 Car객체로 SQL문을 처리합니다.");
	}
}