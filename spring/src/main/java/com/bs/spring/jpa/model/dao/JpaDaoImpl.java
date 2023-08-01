package com.bs.spring.jpa.model.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;
import com.bs.spring.jpa.entity.Address;
import com.bs.spring.jpa.entity.BoardEntity;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.LockerEntity;
import com.bs.spring.jpa.entity.StudentEntity;
@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basicTest(EntityManager em) {
		//em메소드 이용
		//1. JpaMember클래스 영속성 등록
		JpaMember m=JpaMember.builder()
				.memberId("202001201")
				.memberPwd("rhrhrh22")
				.age(new BigDecimal(27))
				.height(178.2)
				.level(Level.DIAMOND)
				.role(Role.ADMIN)
				.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
				.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
				.addr(Address.builder().statement("경기도").detailAdress("시흥시 배곧동")
						.zipcode("123-456").build())
				.build(); //-> 비영속

		//영속화 처리
		em.persist(m); //매개변수로 전달된 객체가 영속성 컨택스트에 저장되고 영속성 컨텍스트에 새로 저장된 객체면 insert문을 자동 생성한다. 실행X
		//insert문 실행 시점은 commit() 메소드 실행시
		JpaMember m2=JpaMember.builder()
				.memberId("김현빵빵")
				.memberPwd("빵빵빵으악으악")
				.age(new BigDecimal(27))
				.height(165.2)
				.level(Level.GOLD)
				.role(Role.USER)
				.build();
		em.persist(m2);
		System.out.println(m);

		
		//저장된 객체 불러오기
		JpaMember selectM=em.find(JpaMember.class, 1L);
		System.out.println(selectM);
	}

	@Override
	public void manyToOne(EntityManager em) {
		JpaMember member=JpaMember.builder()
				.memberId("202001201")
				.memberPwd("rhrhrh22")
				.age(new BigDecimal(27))
				.height(178.2)
				.level(Level.DIAMOND)
				.role(Role.ADMIN)
				.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
				.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
				.addr(Address.builder().statement("경기도").detailAdress("시흥시 배곧동")
						.zipcode("123-456").build())
				.build();
		em.persist(member); //영속화를 먼저 실행시켜야 한다. 영속성 컨텍스트에 있는 것들만 가능
		BoardEntity b=BoardEntity.builder().boardTitle("첫번째 게시글").boardContent("첫 게시글 내용")
				.writeDate(java.sql.Date.valueOf(LocalDate.now()))
				.boardWriter(member).build();
		//영속성 컨텍스트를 비우지 않으면 영속성 컨텍스트에 있는 데이터를 불러오기 때문에 알맞은 데이터를 불러오지 않을 수 있다.
		//연관 관계를 설정 해줘야함.
		member.setBoards(new ArrayList());
		member.getBoards().add(b);
		em.persist(b);
	}

	@Override
	public void boardById(EntityManager em, long no) {
//		BoardEntity b=em.find(BoardEntity.class, no);
//		System.out.println("board 조회 결과");
//		System.out.println(b);
		JpaMember m=em.find(JpaMember.class, no);
		System.out.println("Member 조회 결과");
		System.out.println(m);
		//member만 select문 사용하고 board는 proxy 객체로 넣어둠
		
		System.out.println("JpaMember 등록 게시글 조회");
		System.out.println(m.getBoards());
	}

	@Override
	public void insertStudent(EntityManager em) {
		StudentEntity s=StudentEntity.builder().studentNo(1)
				.studentName("유병승").grade(1).classNumber(3).build();
		LockerEntity l=LockerEntity.builder().lockerNo(1).lockerPosition("3층").lockerColor("파랑").build();
		s.setMylocker(l);
		em.persist(s);
		
		StudentEntity search=em.find(StudentEntity.class, 1L);
		System.out.println(search);
	}

	@Override
	public void selectStudentId(EntityManager em, long no) {
		StudentEntity search=em.find(StudentEntity.class, no);
		System.out.println(search);	
	}

	@Override
	public void deleteStudent(EntityManager em, long no) {
		em.remove(em.find(LockerEntity.class, no));
		
	}
	

}
