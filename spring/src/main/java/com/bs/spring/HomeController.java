package com.bs.spring;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FunctionalTest;
import com.bs.spring.include.TargetComponent;

@Controller
//Spring Bean 등록함
public class HomeController {
	
	//springbean으로 등록된 객체는 필드에 가져와 사용할 수 있다.
	@Autowired
	//타입이 중복되게 등록되어있는 경우 @Qualifier 어노테이션을 이용해서 특정 bean을 지정할 수 있다.
	@Qualifier("dog")
	private Animal a;
	@Autowired
	@Qualifier("bbo")
	private Animal b;
	
	//spring bean으로 등록되지 않은 객체에 Autowired
	//org.springframework.beans.factory.NoSuchBeanDefinitionException 발생한다.
	@Autowired(required=false) //있으면 넣지만 없으면 실행하지 않음 ->프로그램 시작 시에는 없을 수 있는 객체에 활용. 일반적으로 사용x
	private Employee emp;
	
	@Autowired
	private Employee emp2;
	
	
	//자바 코드로 등록한 bean
	@Autowired
	@Qualifier("ani")
	private Animal c;
	
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	private List<Animal> animals; //등록한 bean들이 들어감
	
	@Autowired
	private TargetComponent tc;
	
	//어노테이션으로 bean 등록
	@Autowired
	private FunctionalTest tf;
	
	@Autowired
	//basepackage 외부에 있는 Component
	//org.springframework.beans.factory.NoSuchBeanDefinitionException 발생
	//bean으로 등록하는 범위를 벗어났기 때문에 찾을 수 없다
	private Test test;
	
	@RequestMapping("/test")
	public String home() {
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(emp);
//		System.out.println(emp2);
//		System.out.println(c);
//		System.out.println(sol);
//		animals.forEach(System.out::println);
//		System.out.println(tc);
		System.out.println("functionalTest 출력하기");
		System.out.println(tf);
		System.out.println(tf.getA());
		
		//sprint에서 file을 불러올 수 있는 Resource객체 제공
		Resource resource=new ClassPathResource("mydata.properties");
		try {
			Properties prop=PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			resource=new FileSystemResource("C:\\Users\\GDC10\\git\\Spring\\spring\\src\\main\\resources\\test.txt");
			Files.lines(Paths.get(resource.getURI()),Charset.forName("utf-8")).forEach(System.out::println);
			
//			resource=new UrlResource("http://naver.com");
//			InputStream is=resource.getInputStream();
//			int d=0;
//			StringBuffer sb=new StringBuffer();
//			while((d=is.read())!=-1){
//				sb.append((char)d);
//			}
//			System.out.println(sb);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ViewResolver가 알아서 실행함 ->servlet-context 에서 확인
		return "index";
	}
}
