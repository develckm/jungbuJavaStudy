package java_study2.co.kr.jungbu;
import java.lang.*;
import java.util.Scanner; // 개발에 유용한 클래스의 집합
import java.util.ArrayList; //HashMap .. 컬렉션 프레임워크(유용한 자료구조) 

public class InnerClass { 
	//class 생성 : java -> class로 컴파일 (javac)
	//패키지 생성 : 클랙스에 고유 이름을 제공, 비슷한 성격의 클래스의 모음
	//public class : java 문서와 동일한 이름의 class, 다른 패키지에서 Import 해서 사용가능한 class 
	//main 메소드 (앱) : jre를 호출해서 작성한 순서대로 코드를 실행 => 순차적 언어
	
	public static void main(String[] args) {
		System.out.println("안녕!");
		java.lang.String s="dd"; //기본?? 참조??(0)
		java.lang.Integer i= 13; //??랩퍼클래스 
	
		int i2=44; 
		int i3=44;
		int i4=44;
		int i5=44;
		
		
		char []arrC={'a','b','c','d','e'}; //배열(리터럴하게 선언가능) => 참조형
		char [] arrC2=new char[5]; //{0,0,0,0,0}
		Character [] arrC3=new Character[5]; //{null,null,null,null,null}
		String s3="abcde"; //마치 기본형 처럼 선언되지만 문자의 배열이기 때문에 참조형
		
		
		//기본형(원시형)데이터 타입의 특징 Primitive type (원시적 -> 기계와 가까울 수록) 원시적 언어??
		//1. 원시형 데이터  int i=4; => 메모리 4byte 공간을 할당(주소) 부호, 2^0, 2^1, 2^2=>1 ..... 2^30 => 데이터 원본(필드가 없다)
		//2. 리터럴하게 선언 (문자그대로) : 대부분 수와 관련되어 있기 때문 , 자주 사용하기 때문
		//3. 스택 메모리에 생성됨 : 자주 사용하기 때문 (i2~5까지 같은 44를 참조)
	
		//참조형데이터 타입의 특징 
		Object o=new Object(); 
		// 1. {"경민","길동","둘리"} data를 참조하고 있어서 참조형 데이터 타입이다.
		// 2. new 연산자로 생성자를 호출하면 참조형 데이터(객체) 생성  (생성자 class와 동일한 이름을 갖는 존재)
		// 3. 객체가 힙 메모리에 생성이 되고 가비지 컬렉션에 의해 정리=> 포인터가 필요없어짐 => 인스턴스 객체
				
	}
}
class 정욱채{
	//전역에 선언하는 것은 필드가 된다. (객체가 참조하는 data)
	String name; //null
	String[]hands= {};
	int birth; //0
	
}


