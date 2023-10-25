package org.zerock.mybatistest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.command.ScoreVO;
import org.zerock.mapper.ScoreMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MyBatisScore {

	@Autowired
	ScoreMapper mapper;
	
	//mapper 설정...
	//1)테스트 메서드는 mapper인터페이스를 출력하는 메서드 구현...
	// root -context -> mapper 관련 설정을 추가해야함.

		@Test 
		public void test1() {
			System.out.println(">>>>>>>>>>>>mapper :" + mapper );
		}
	//메서드 테스트 
		@Test
		public void insertTest() {
			for (int i = 1 ; i<=5; i++) {
				ScoreVO vo = new ScoreVO();
				vo.setName("홍제동");
				vo.setKor("92");
				vo.setEng("92");
				vo.setMath("92");
				boolean result=mapper.insert(vo);
			
				System.out.println(">>>>>>>>>>>>>결과" + result);
		}
		}
		
		@Test
		public void selectTest() {
			List<ScoreVO> list = mapper.select();
			for(ScoreVO vo : list) {
				System.out.println(vo);	
			}
			
		}
		
		@Test
		public void deleteTest() {
			Scanner scan = new Scanner(System.in);
			System.out.print("삭제할 게시글 번호 : ");
			String num = scan.next();
			mapper.delete(num);
			System.out.println("결과 확인");
			List<ScoreVO> list = mapper.select();
			for(ScoreVO vo : list)System.out.println(vo);
			
			scan.close();
		}
		
	
		
}
