package importData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
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

public class InsertProject {
	public void insertToPres(List list){
		Connection conn = ImportToPSTE.getConn();
		String sql1 = "select proj_id from pdcproj where proj_name=?";
		String sql = "update PDCPRES SET PROJ_ID=? WHERE PRES_CHI_NAME = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement p1 = conn.prepareCall(sql1);
			PreparedStatement pstm = conn.prepareCall(sql);
			for(int i=0;i<list.size();i++){
				Map map = (Map) list.get(i);
				System.out.println(map);
				p1.setString(1, (String) map.get(0));
				ResultSet rs = p1.executeQuery();
				Long proj_id = 0l ;
				while(rs.next()){
					proj_id = rs.getLong(1);
					System.out.println(proj_id);
				}
				if(proj_id != 0l){
					pstm.setLong(1, proj_id);
					pstm.setString(2, (String) map.get(1));
					pstm.execute();
				}
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public List getPersonProject(){
		List list = new ArrayList();
		String filepath = "D:\\ljq\\PDC\\20140422_PDC_Resource.xls";
		File file = new File(filepath);
		try {
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet("All technical levels");
			for(int i=4;i<23;i++){
				Cell cell = sheet.getCell(1, i);
				String project = cell.getContents();
				if(project!=null && !project.isEmpty()){
					Cell cellName = sheet.getCell(2, i);
					String name = cellName.getContents();
					Map map = new HashMap();
					map.put(0, project);
					map.put(1, name);
					list.add(map);
				}
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args){
		InsertProject ip = new InsertProject();
		List list = ip.getPersonProject();
		ip.insertToPres(list);
	}
}
