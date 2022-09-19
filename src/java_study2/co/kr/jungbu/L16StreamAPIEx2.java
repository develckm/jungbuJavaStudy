package java_study2.co.kr.jungbu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class L16StreamAPIEx2 {
	//{"products":[{"name":"책상","price":60000,"category":4,quantity=55},{"name":"의자","price":140000,"category":4,quantity=}]}
	//쇼핑몰에서 판매하는 상품 자료 type = Variable Object(VO), Data Transfer Object (DTO)
	//JSON 은 무엇인가요? -> JSON을 사용하는 이유!(통신 시 장점 1.문자열,2.가볍다,3.type을 정의가능,4.data traveling이 용이 .... )
	//key:value로 되어 있는 가장 인기 있는 자료구죠! JavaScriptObjectNotation : 자바스크립트 객체 명세서(객체 설명서) => 리터러 표기법으로 만들어짐 => 문자열 통신으로 사용
	
	//enum Cate{식품,의류,가구,문구,침구} //각 필드가 고유값을 갖는 상수기 때문에 수를 사용하는 다른 상수와 섞어 쓰지 않는다.(단점 자바 내부에서만 사용가능)
	interface Cate{ //db에 저장하는 분류가 보통 수로 되어 있기 때문에 수로 된 상수를 사용
		public static int 식품=0;
		public static int 의류=1;
		public static int 가구=2;
		public static int 문구=3;
		public static int 침구=4;
	}
	
	class ProductDTO{
		//class의 전역에 선언된 변수 : 전역변수, 객체의 필드로 생성됨(속성)
		String name=null;
		int price=0;
		int category; //0:식품,1:의류,2:가구,3:문구류,4:침구류 (약속)
		int quantity=10; //수량
		//생성자로 필드를 초기화 : 생성자는 new 연산자로 호출하면 객체를 반환하는데 이름이 반환하는 타입인 함수다.
		public ProductDTO( String name,int price, int category) {
			//필드 접근자! this
			this.name=name;
			this.price=price;
			this.category=category;
		}
		public ProductDTO( String name,int price, int category, int quantity) {
			//필드 접근자! this
			this.name=name;
			this.price=price;
			this.category=category;
			this.quantity=quantity;
		}
		@Override
		public String toString() {
			return "ProductDTO:{name:" + name + ", price:" + price + ", category:" + category + ", quantity:" + quantity
					+ "}";
		}		
	}
	public static void main(String[] args) {
		L16StreamAPIEx2 ex2=new L16StreamAPIEx2();
		List<ProductDTO> products=new ArrayList<ProductDTO>();
		products.add(ex2.new ProductDTO("허쉬 초콜릿", 1000, Cate.식품));
		products.add(ex2.new ProductDTO("허쉬 초콜릿 우유", 1200, Cate.식품, 5));
		products.add(ex2.new ProductDTO("바나나 단지 우유", 1500, Cate.식품, 3));
		products.add(ex2.new ProductDTO("돼지바", 900, Cate.식품, 15));
		products.add(ex2.new ProductDTO("하겐다즈 바닐라", 5000, Cate.식품,11));
		products.add(ex2.new ProductDTO("소세지 도시락", 3500, Cate.식품,3));
		products.add(ex2.new ProductDTO("도시락 컵라면", 800, Cate.식품,50));
	
		products.add(ex2.new ProductDTO("hp 연필", 500, Cate.문구,200));
		products.add(ex2.new ProductDTO("지우개", 500, Cate.문구,50));
		products.add(ex2.new ProductDTO("볼펜", 1000, Cate.문구,100));
		products.add(ex2.new ProductDTO("화이트 지우개", 2500, Cate.문구,3));
		
		products.add(ex2.new ProductDTO("흰색 셔츠", 10000, Cate.의류,0));
		products.add(ex2.new ProductDTO("나이키 반팔 티", 25000, Cate.의류,5));
		products.add(ex2.new ProductDTO("나이키 신발", 110000, Cate.의류,2));
		products.add(ex2.new ProductDTO("아디다스 신발", 95000, Cate.의류,2));
		
		products.add(ex2.new ProductDTO("여름용 이불", 25000, Cate.침구,1));
		products.add(ex2.new ProductDTO("솜 베게", 2000, Cate.침구,10));
		products.add(ex2.new ProductDTO("대나무 베게", 2000, Cate.침구,0));
		products.add(ex2.new ProductDTO("침대 커버 퀸", 15000, Cate.침구,3));
		
		products.add(ex2.new ProductDTO("행거", 60000, Cate.가구,5));
		products.add(ex2.new ProductDTO("책상", 45000, Cate.가구,10));
		products.add(ex2.new ProductDTO("듀오백의자", 145000, Cate.가구,3));
		
		System.out.println(products.toString()); //Object.toString() : 객체 정보
		//편의점 관리 프로그램 
		//편의점의 상품 저장
		//편의점 상품 분류, 상품의 수가 없는 것을 확인, 분류 별로 상품의 가격 평균 => 관리(자료를 다루는 방법)
		
		//식품 중에 2000원 이상이 것을 검색 하고 싶다,
		List<ProductDTO> foods= new ArrayList<ProductDTO>();
		for(ProductDTO p : products) {
			if(p.category==Cate.식품) {
				if(p.price>=2000) {
					foods.add(p);					
				}
			}
		}//외부 반복문 검사식 (함수형 언어가 지원하는 내부반복문 보다 복잡도가 높다)
		System.out.println(foods);
		Optional<String> namesOpt=products.stream()
			.filter((p)->p.category==Cate.식품) //중간연산 (stream을 다시 반환 -> 이어서 중간연산 가능)
			.filter((p)->p.price>=2000)  //중간연산을 수정 삭제하기 편하고 재사용 할 수 있다.
			.map((p)->p.name)  //반환하는 것으로 자료를 변환
			.reduce((s,s1)->s+", "+s1); //최종연산 BiOperator : (s1,s2)->{return s3}
			//.forEach(System.out::println); //최종연산
		//reduce 동작 원리  : nums={10,20,30,40,50,60,70} result=0
		// (s1,s2)->s1+s2;    
		//0: result=nums[0]+nums[1];
		//1: result+=nums[2];
		//1: result+=nums[3];
		// ....
		//end : result+= nums[nums.length-1] => return result;
				
		System.out.println(namesOpt.get());
		
		//카테고리가 가구인 것의 가격의 합을 구하세요! (reduce)
		Optional<Integer> sumOpt=products.stream()
			.filter(p->p.category==Cate.가구)
			.map(p->p.price*p.quantity) //중간 연산이 IntStream을 반환 (기본형 스트림의 장점: 1.null없음,2.연산하는 함수를 제공)
			.reduce( (p1,p2)-> p1+p2);
			//.forEach(System.out::println);
		System.out.println("가구의 가격 총합: "+sumOpt.get());
		
		//내부반복문을 사용하면 stream 자료를 query 처럼 제어할 수 있다.
		
		Integer i=null;
		//System.out.println(i+10); //오류 null.intValue()
		i=30;
		System.out.println(i+10);
		
		//Optional : data가 null일 수 있으니 처리하도록 명시하는 타입
		Optional<Integer> i_opt=Optional.empty(); //Optional 선언법
		i_opt=Optional.ofNullable(99); //Optional data를 참조하는 방법
		//i_opt=Optional.of(null); //null이 아닌 객체만 참조 (사용하지 않는다)
		//System.out.println(i_opt+20);
		//System.out.println(i_opt.get()+20);
		if(i_opt.isPresent()) {
			System.out.println(i_opt.get()+20);
		}else {
			System.out.println("isPresent() 는 값이 있을 때만 True를 반환 , isEmpty() 는 Null일 때만 True 반환");
		}
		//반환되는 데이터가 Null일 수 있음을 type으로 명시하는 것
//		i_opt.ifPresent((num)->{
//			System.out.println("값이 있을 때만 실행되는 함수");
//		});
		i_opt.ifPresentOrElse((num)->{
			System.out.println("값이 있을 때만 실행");
		}, ()->{
			System.out.println("null일때 실행");
		}); 
		
		///////////////////////steam groupingBy ////////////////////////////////
		Map<Integer, List<ProductDTO>> groupingList=products.stream()
			.collect(Collectors.groupingBy(p->p.category)); 
		//해당 객체의 특정 필드로 반환하면 해당 필드를 key로 하는 list로 그룹핑된다. 
		
		System.out.println(groupingList);
		System.out.println(groupingList.get(Cate.가구));
		//////////////////////stream partitioningBy true or false 의 2 그룹으로 나누는 것
	}
}



