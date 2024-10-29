package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool{

	public MVCBoardDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int selectCount(Map<String, Object>map) {
		int totalCount=0;
		
		String query="select count(*) from mvcboard";
		if(map.get("searchWord")!=null) {
			query+=" where "+map.get("searchField")+" "+"like '%"+map.get("serachWord")+"%'";
		}
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			rs.next();
			totalCount=rs.getInt(1);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		
		String query="select * from ( select Tb.*,"
				+ " ROWNUM rNum from( select * from mvcboard ";
		
		if(map.get("searchWord") !=null) {
			query+=" where "+map.get("searchFiled")+" like '%"+map.get("searchField")
			+" like '%"+map.get("searchWord")+"%'";
		}
		query+=" order by idx desc ) Tb ) where rNum between ? and ?";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs=psmt.executeQuery();
			
			while (rs.next()) {
				MVCBoardDTO dto=new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));
                
                board.add(dto);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return board;
		
	}
	
	public int insertWrite(MVCBoardDTO dto) {
		int result=0;
		
		try {
			String qString="insert into mvcboard ( idx,name,title,content,ofile,sfile,pass) values( seq_board_num.NEXTVAL,?,?,?,?,?,?)";
			
			psmt=con.prepareStatement(qString);
			psmt.setString(1, dto.getName());
            psmt.setString(2, dto.getTitle());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getPass());
            result = psmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		
		String q="select * from mvcboard where idx=?";
		try {
			psmt=con.prepareStatement(q);
			psmt.setString(1, idx);
			rs=psmt.executeQuery();
			
			if (rs.next()) {  // 결과를 DTO 객체에 저장
                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));
            }
			
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
            e.printStackTrace();
		}
		
		return dto;
		
	}
	
	public void updateVisitCount(String idx) {
		String qString="update mvcboard set visitcount=visitcount+1 where idx=?";
		try {
			
			psmt=con.prepareStatement(qString);
			psmt.setString(1, idx);
			psmt.executeQuery();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public void downCountPlus(String idx) {
		String sql="update mvcboard set downcount=downcount+1  where idx=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean comfirmPassword(String pass,String idx) {
		
		boolean isCorr=true;
		try {
			String sql="select count(*) from mvcboard where pass=? and idx=?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1,pass);
			psmt.setString(2,idx);
			rs=psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr=false;
			}
		}catch (Exception e) {
			isCorr=false;
			e.printStackTrace();
		}
		return isCorr;
		
	}
	
	public int deletePost(String idx) {
		int result=0;
		try {
			String query="delete from mvcboard where idx=?";
			psmt=con.prepareStatement(query);
			psmt.setString(1,idx);
			result=psmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	 public int updatePost(MVCBoardDTO dto) {
	        int result = 0;
	        try {
	            // 쿼리문 템플릿 준비
	            String query = "UPDATE mvcboard"
	                         + " SET title=?, name=?, content=?, ofile=?, sfile=? "
	                         + " WHERE idx=? and pass=?";

	            // 쿼리문 준비
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, dto.getTitle());
	            psmt.setString(2, dto.getName());
	            psmt.setString(3, dto.getContent());
	            psmt.setString(4, dto.getOfile());
	            psmt.setString(5, dto.getSfile());
	            psmt.setString(6, dto.getIdx());
	            psmt.setString(7, dto.getPass());

	            // 쿼리문 실행
	            result = psmt.executeUpdate();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 수정 중 예외 발생");
	            e.printStackTrace();
	        }
	        return result;
	    }

}

