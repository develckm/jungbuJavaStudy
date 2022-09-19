package java_study2.co.kr.jungbu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L18StreamAPIEx4 {

	public static void main(String[] args) {
		//Stream 최종 연산 종류
		//forEach(Consumer)  : 출력 
		//reduce(BinaryOperator)	: 소모 (요소들을 1개의 결과로 줄인다.)
		//sum(),average() : 연산 소모 (기본형)
		//count(), max, min(기본형), max, min(참조형 sort) : 집계, 통계 연산
		
		//any,all,none Match(Predicate) : 검사 (중간 연산 filter 유사함)
		
		//collect() : 수집 ( 주간연산 map 과 유사 ) 

		int [] scoreArrCKM= {88,60,70,100,30,90}; //최경민씨의 성적
		int [] scoreArrHKD= {90,100,99,100,85,92};//홍길동씨의 성적
		//성적관리 프로그램 
		//장학금 대상자 2명이 있는데 모든 과목의 성적이 80 이상이어야 장학금이 나온다. (검사식을 구현하세요) 

		long c=Arrays.stream(scoreArrCKM)
			.filter((s)->s<80)
			.count();
		System.out.println("최경민씨의 성적이 80점이하인 과목의 수 :"+c);
		
		boolean test=Arrays.stream(scoreArrCKM)
			.allMatch(s->s>=80);
		
		System.out.println("최경민씨의 성적은 모두 80이상이간요? :"+test);
		
		test=Arrays.stream(scoreArrHKD)
				.allMatch(s->s>=80);
		System.out.println("홍길동씨의 성적은 모두 80이상이간요? :"+test);
		
		//1개라도 60점 미만의 점수가 있으면 장학금 대상에서 탈락
		test=Arrays.stream(scoreArrCKM)
				.anyMatch(s->s<60);
		System.out.println("최경민씨의 성적이 60 미만인 것이 1개라도 있나? :"+test);

		
		test=Arrays.stream(scoreArrHKD)
				.anyMatch(s->s<60);
		System.out.println("홍길동씨의 성적이 60 미만인 것이 1개라도 있나? :"+test);
		
		Stream stream=Stream.of("a",1,"b",33.9,"c",'d' ); //(Object ...values) : 매개변수의 수 제한이 없게 선언됨
		Stream<String> stream2=Stream.of("11","1","55","33","100","-50");
		ArrayList<Integer> strList=
				stream2
				.map(Integer::parseInt) //정수로 바꾼다. 
				.collect(Collectors.toCollection(ArrayList<Integer>::new));
		System.out.println(strList);
		
		//stream2.forEach(System.out::println); //Stream은 방향성이 있는 자료구조로 한번 사용하면 다시 사용 불능
		Stream<String> stream3=Stream.of("11","1","55","33","100","-50");
		//int sum=stream3.collect(Collectors.summingInt((str)->Integer.parseInt(str)));
		int sum=stream3.mapToInt(Integer::parseInt).sum();
		System.out.println(sum);
		
		
		
	}
}
