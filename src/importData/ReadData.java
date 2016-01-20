package importData;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class ReadData {
	private String filePath = "D:\\ljq\\spec\\123.xls";
	
	public static void main(String args[]){
		int i= 1234567888;
		System.out.println(i);
	}
	
	@SuppressWarnings("unchecked")
	public List read(){
		List technicals = new ArrayList();
		Connection conn = null;
		File file = new File(filePath);
		int column = 6;
		int row = 0;
		
		try {
			Workbook workBook = Workbook.getWorkbook(file);
			Sheet sheet = workBook.getSheet("All technical levels");
			conn = getConn();
			String cate_id = "";
			String cate_name = "";
			String subc_id ="";
			do{
				Cell cell = sheet.getCell(column, row);
				
				String s = cell.getContents();
				if(!"".equals(s.trim())){
					String sql = "select * from PDCCATE where CATE_NAME='"+s+"'";
					System.out.println(sql);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					rs.next();
					cate_id = rs.getString("CATE_ID");
					cate_name = rs.getString("CATE_NAME");
				}
				Cell subCell = sheet.getCell(column, row+1);
				String subcate = subCell.getContents();
				
				if(!"".equals(subcate.trim())){
					String sqlSub = "select * from PDCSUBC where SUBC_NAME='"+subcate+"' and CATE_ID='"+cate_id+"'";
					System.out.println(sqlSub);
					Statement subcST = conn.createStatement();
					ResultSet subcateRS = subcST.executeQuery(sqlSub);
					subcateRS.next();
					subc_id = subcateRS.getString("SUBC_ID");
				}
				
				Cell techIdCell = sheet.getCell(column, row+2);
				Cell techCell = sheet.getCell(column, row+3);
				String techId = techIdCell.getContents();
				String tech = techCell.getContents();
				Map technical = new HashMap();
				technical.put("cate_id", cate_id);
				technical.put("subc_id", subc_id);
				technical.put("tech_id", techId);
				technical.put("tech", tech);
				technicals.add(technical);
				
				column++;
			}while( column<120);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return technicals;
	}
	
	public Connection getConn(){
		Connection conn = null;
		String url = "jdbc:oracle:thin:@b801581:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "PDC", "PDC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(conn != null){
			return conn;
		}else{
			System.out.println("get null connection!!!");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void importData(List list){
		Connection conn = getConn();
		String isLanguage = "0";
		String isVisible = "1";
		try {
			conn.setAutoCommit(false);
			String sql = "insert into PDCTECH(TECH_ID,TECH_NAME,CATE_ID,SUBC_ID,TECH_ISLANGUAGE,TECH_VISIBLE) values(?,?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			java.util.Iterator it = list.iterator();
			while(it.hasNext()){
				Map temp = (Map) it.next();
				String techId = (String) temp.get("tech_id");
				String subcId = (String) temp.get("subc_id");
				String cateId = (String) temp.get("cate_id");
				String techName = (String) temp.get("tech");
				pstm.setInt(1, Integer.parseInt(techId));
				pstm.setString(2, techName);
				pstm.setInt(3, Integer.parseInt(cateId));
				pstm.setInt(4, Integer.parseInt(subcId));
				pstm.setString(5, isLanguage);
				pstm.setString(6, isVisible);
				pstm.execute();
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}














