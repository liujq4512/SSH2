package importData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportToPSTE {
	
	/**
	 * @param args
	 */
	private String filePath = "D:\\ljq\\PDC\\20140428_PDC.xls";
	
	@SuppressWarnings("unchecked")
	public List read(){
		List list = new ArrayList();
		File file = new File(filePath);
		Connection conn = null;
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet("All technical levels");
			Sheet sheetLan = workbook.getSheet("All language levels");
			for(int row=61;row<81 ;row++){
				
				
				Cell cell = sheet.getCell(2, row);
				String name = cell.getContents();
				System.out.println(name);
				conn = getConn();
				String sql = "select PRES_ID from PDCPRES WHERE PRES_CHI_NAME = ?";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, name);
				ResultSet rs = pstm.executeQuery();
				rs.next();
				int presId = rs.getInt("PRES_ID");
					
				for(int column=6;column<120;column++){
					Cell cellTech = sheet.getCell(column, row);
					Cell cellTechId = sheet.getCell(column, 2);
					String techLevel = cellTech.getContents();
					String teleId = "";
					String techId = cellTechId.getContents();
					if("Reference".equalsIgnoreCase(techLevel.trim())){
						teleId = "1";
					}else if("Mastery".equalsIgnoreCase(techLevel.trim())){
						teleId="2";
					}else if("Autonomy".equalsIgnoreCase(techLevel.trim())){
						teleId="3";
					}else if("Basic".equalsIgnoreCase(techLevel.trim())){
						teleId="4";
					}else{
						teleId = "5";
					}
					Map value = new HashMap();
					value.put("presId", presId);
					value.put("teleId", teleId);
					value.put("techId", techId);
					list.add(value);
					
					if(column <11){
						Map language = new HashMap();
						Cell cellLan = sheetLan.getCell(column, row);
						Cell cellLanId = sheetLan.getCell(column, 2);
						String lanId = cellLanId.getContents();
						String lale = cellLan.getContents();
						String laleId = "";
						if("Native or bilingual proficiency".equalsIgnoreCase(lale.trim())){
							laleId = "1";
						}else if("Full professional proficiency".equalsIgnoreCase(lale.trim())){
							laleId = "2";
						}else if("Professional working proficiency".equalsIgnoreCase(lale.trim())){
							laleId ="3";
						}else if("Limited working proficiency".equalsIgnoreCase(lale.trim())){
							laleId="4";
						}else if("Elementary proficiency".equalsIgnoreCase(lale.trim())){
							laleId = "5";
						}else {
							laleId = "6";
						}
						language.put("presId", presId);
						language.put("techId", lanId);
						language.put("laleId", laleId);
						list.add(language);
					}
				}
				
			}
			
		} catch (Exception e) {
			if(conn!= null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} 
		
		return list;
	}
	
	
	public static Connection getConn(){
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
	
	public void importData(List list){
		Connection conn = getConn();
		String sql = "insert into PDCPSTE(PSTE_ID,PRES_ID,TECH_ID,LALE_ID,TELE_ID)values(?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				Map map = new HashMap();
				map = (Map) list.get(i);
				pstm.setInt(1, i+8331);
				pstm.setInt(2, Integer.parseInt(map.get("presId").toString()));
				pstm.setInt(3, Integer.parseInt(map.get("techId").toString()));
				String teleId = (String) map.get("teleId");
				String laleId = (String) map.get("laleId");
				if("".equals(teleId)|| teleId == null){
					pstm.setNull(5, Types.INTEGER);
				}else{
					pstm.setInt(5, Integer.parseInt(teleId));
				}
				if("".equals(laleId)||laleId ==null){
					pstm.setNull(4, Types.INTEGER);
				}else{
					pstm.setInt(4, Integer.parseInt(laleId));
				}
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
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		
		ImportToPSTE pste = new ImportToPSTE();
		List list = pste.read();
		for (java.util.Iterator it = list.iterator();it.hasNext();){
			Map map = (Map) it.next();
			System.out.println(map);
			
		}
		long startTime = System.currentTimeMillis();
		pste.importData(list);
		long time = System.currentTimeMillis() - startTime;
		System.out.println("import 7330 records expends time:"+time);
	}
}
