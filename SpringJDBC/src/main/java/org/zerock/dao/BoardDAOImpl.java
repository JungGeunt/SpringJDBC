package org.zerock.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.zerock.command.BoardVO;

//DAO의 객체 주입은 일반적으로 Component나 Repository 어노테이션을 사용
//component-scan에 꼭 추가해야 함.

@Repository
public class BoardDAOImpl implements BoardDAO {

	//DB로 가정할 ArrayList 필요함.
	//List<BoardVO> DB = new ArrayList<BoardVO>();
	
	//DB연결하기 
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// table 생성 .... 후
	
	//테이블에 정보 넣기
	@Override
	public void boardInsert(String name, String title, String content) {
		
		System.out.println("----- DAO 계층 ------");
		System.out.println(name);
		System.out.println(title);
		System.out.println(content);
		
		//쿼리로 사용할 sql 작성
		String sql = "insert into board01 (num, name, title, content)"
					+"values(borard01_seq.nextval,?,?,?)";
		
//		//첫번쨰 방법
//		int result =jdbcTemplate.update(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, name);
//				ps.setString(2, title);
//				ps.setString(3, content);
//			}
//		});
//		
		//두번쨰 방법
		int result = jdbcTemplate.update(sql , new Object[] {name ,title, content});
		
		if(result== 1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	@Override
	public List<BoardVO> boardSelect() { //getList()에서 실행될 예정 ..DB모든 정보 불러서 반환...
		/*
		 * query()의 파라미터의 의미
		 * sql :sql쿼리, ?를 사용하는 preparedStatement용 쿼리.
		 * new Object[] {값 , 값} :sql에 ? 에 대한 셋팅 값을 저장(바인디)
		 * new RowMapper<Type>() : 조회 결과 ResultSet으로부터 데이터를 읽어와 Type데이터를 생성.
		 * 				->익명 클래스로 동작 , mapRow()메서드에서 ResultSet에서 읽어온 값을 처리 후 리턴
		 */
	
		String sql = "select * from board01 order by num desc";
		
		
		//첫번째 방법
//		List<BoardVO> list = jdbcTemplate.query(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				// TODO Auto-generated method stub
//				
//			}
//		}, new RowMapper<BoardVO>() {
//			@Override
//			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardVO vo = new BoardVO();
//				vo.setNum(rs.getInt("num"));
//				vo.setName(rs.getString("name"));
//				vo.setTitle(rs.getString("title"));
//				vo.setContent(rs.getString("content"));
//				return vo;
//			}
//		});
		
		//두번쨰 방법 
		List<BoardVO> list = jdbcTemplate.query(sql, new Object[] {} , 
				new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					BoardVO vo = new BoardVO();
					vo.setNum(rs.getInt("num"));
					vo.setName(rs.getString("name"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					return vo;
			}
		});
		return list;
	}

	
	@Override
	public void boardDelete(String num) {
		//DB.remove(Integer.parseInt(num));
		
		String sql = "delete from board01 where num=?";
		int result =jdbcTemplate.update(sql, new Object[] {num} );
		if(result== 1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

}
