package org.zerock.mybatistest;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.command.ScoreVO;
import org.zerock.testmapper.TestMapper;

@RunWith(SpringJUnit4ClassRunner.class) //객체 자체를 불러와야되서 class를 붙임
@ContextConfiguration(value= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MyBatisSelect {
	//MyBatis는 DAO를 대체...
	//마이바티스 매퍼 ...(mapper 스캔 또는 직접 지정. 매퍼 인터페이스, 매퍼설정(xml)이 존재해야 함.)
	
	//실제 쓸때에는 sqlfactory를 만든지 않아도 됨 (중요객체 일뿐 ->생성되었는지만 확인해도됨)
	//TestMapper.java를 부름. -> 구현체는 xml 파일을 찾아서 불러옴
	@Autowired
	private TestMapper mapper;
	
	@Test
	public void getTime() {
		String time =mapper.getTime();
		System.out.println(time); //select sysdate from dual의 결과
	}
	
	//select
	@Test
	public void select() {
		try {
			List<ScoreVO> list = new ArrayList<>();
			list=mapper.select();	
			System.out.println(list);
			
			// for(ScoreVO vo :list){
			//  System.out.println(vo);               }
			
		} catch (Exception e) {
			
		}
	}
	
}
