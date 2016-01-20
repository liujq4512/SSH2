package importData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InsertToInterview {
	private String filePath = "D:\\ljq\\PDC\\20140327_PDC_Interview_FollowingUp.xls";
	
	public static void main(String args[]){
		InsertToInterview view = new InsertToInterview();
		List list = view.read();
		
		view.insert(list);
	}
	public void insert(List list){
		List data = list;
		String sql = "insert into pdcview(view_id,proj_id,bu_id,view_year,view_position,poty_id,view_candidate,supp_id,cpi_id,view_passed,view_comments)values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = this.getConn();
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				Map map = (Map) list.get(i);
				System.out.println(map);
				pstm.setLong(1, Long.parseLong(String.valueOf(i+1)));
				pstm.setLong(2, Long.parseLong(String.valueOf(map.get("proj_id"))) );
				pstm.setLong(3, Long.parseLong(String.valueOf(map.get("bu_id"))));
				pstm.setString(4, (String) map.get("Year"));
				pstm.setString(5,(String)map.get("Position"));
				pstm.setLong(6, Long.parseLong(String.valueOf(map.get("poty_id"))));
				pstm.setString(7,(String)map.get("Candidate"));
				pstm.setLong(8, Long.parseLong(String.valueOf(map.get("supp_id"))));
				pstm.setLong(9, Long.parseLong(String.valueOf(map.get("cpi_id"))));
				pstm.setInt(10, Integer.parseInt(String.valueOf(map.get("passed"))));
				pstm.setString(11, (String)map.get("Comments"));
				pstm.executeUpdate();
			}
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List read(){
		List data = new ArrayList();
		File file = new File(filePath);
		try {
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet("Data");
			Connection conn = this.getConn();
			
			for(int row=1;row<72; row++){
				Map rowData = new HashMap();
				for(int column=0;column<12; column++){
					Cell cell = sheet.getCell(column, row);
					Cell celltitle = sheet.getCell(column,0);
					String title = celltitle.getContents();
					String value = cell.getContents();
					Long key = 0l;
					if(column==0){
						if("LIPR".equals(value)){
							key = 9l;
						}else if("GPRO".equals(value)){
							key = 2l;
						}else if("RJAV".equals(value)){
							key = 6l;
						}else if("ARCAD".equals(value)){
							key = 7l;
						}else if("INDUS".equalsIgnoreCase(value)){
							key = 10l;
						}
						rowData.put("proj_id", key);					
					}else if(column==1){
						String sql = "select BU_ID from PDCBU where BU_name =?";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1, value);
						ResultSet set = pstm.executeQuery();
						while(set.next()){
							key = set.getLong("BU_ID");
						}
						pstm.close();
						rowData.put("bu_id", key);
					}else if(column == 4){
						String sql = "select POTY_ID from PDCPOTY WHERE POTY_NAME=?";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1, value);
						ResultSet set = pstm.executeQuery();
						while(set.next()){
							key = set.getLong("POTY_ID");
						}
						pstm.close();
						rowData.put("poty_id", key);
					}else if(column == 6){
						if("Newtouch".equalsIgnoreCase(value)){
							key = 1l;
						}else if("Wistron".equalsIgnoreCase(value)){
							key = 2l;
						}
						rowData.put("supp_id", key);
					}else if(column==7){
						if("ZENG Dihao".equalsIgnoreCase(value)){
							key = 3l;
						}else if("LIU Yi".equalsIgnoreCase(value)){
							key = 1l;
						}else if("Shu Laang CHANTHALA".equalsIgnoreCase(value)){
							key = 4l;
						}
						rowData.put("cpi_id", key);
					}else if(column ==8){
						if("yes".equalsIgnoreCase(value)){
							key = 1l;
						}else if("no".equalsIgnoreCase(value)){
							key = 2l;
						}
						rowData.put("passed", key);
					}else {
						rowData.put(title, value);
					}
					
				}
				data.add(rowData);
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return data;
	}
	public Connection getConn(){
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@b801581:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,"PDC","PDC");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
