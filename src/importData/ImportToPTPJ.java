package importData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
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

public class ImportToPTPJ {

	/**
	 * @param args
	 */
	
	private String filePath = "D:\\ljq\\spec\\123.xls";
	
	public List read(){
		List list = new ArrayList();
		File file = new File(filePath);
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet1 = workbook.getSheet("ARCTEC project level min");
			Sheet sheet2 = workbook.getSheet("RJAV project level min");
			Sheet sheet3 = workbook.getSheet("ARCAD level min");
			Sheet sheet4 = workbook.getSheet("SOPI level min");
			Sheet sheet5 = workbook.getSheet("Sidauto level");
			Sheet sheet6 = workbook.getSheet("Italauto level");
			Sheet sheet7 = workbook.getSheet("GPRO Min level");
			Sheet sheet8 = workbook.getSheet("ARCTEC calcul");
			Sheet sheet9 = workbook.getSheet("RJAV project calcul");
			Sheet sheet10 = workbook.getSheet("ARCAD calcul");
			Sheet sheet11 = workbook.getSheet("SOPI calcul");
			Sheet sheet12 = workbook.getSheet("Sidauto calcul");
			Sheet sheet13 = workbook.getSheet("Italauto calcul");
			Sheet sheet14= workbook.getSheet("GPRO calcul");
			Sheet sheets[] = new Sheet[]{sheet1,sheet2,sheet3,sheet4,sheet5,sheet6,sheet7,sheet8,sheet9,sheet10,sheet11,sheet12,sheet13,sheet14};
			
		
			
			Connection conn = ImportToPSTE.getConn();
			String sql = "select PRES_ID from PDCPRES WHERE PRES_CHI_NAME = ?";
			String getPSTE = "select PSTE_ID FROM PDCPSTE WHERE PRES_ID = ?";
			String getPJTE = "SELECT PJTE_ID FROM PDCPJTE WHERE PROJ_ID = ? AND TECH_ID=?";
			
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			PreparedStatement pstmPSTE = conn.prepareStatement(getPSTE);
			PreparedStatement pstmPJTE = conn.prepareStatement(getPJTE);
			
			for(int row=4;row<75;row++){
				for(int i=0;i<7;i++){
					Cell nameCell = sheets[i].getCell(2, row);
					String name = nameCell.getContents().trim();
					pstm.setString(1, name);
					ResultSet rs = pstm.executeQuery();
					int pres_id = 0;
					while(rs.next()){
						pres_id = rs.getInt("PRES_ID");
					}
					
					if(pres_id != 0){
						
						pstmPSTE.setInt(1, pres_id);
						ResultSet pste = pstmPSTE.executeQuery();
						int pste_id = 0 ;
						
						while(pste.next()){
							pste_id = pste.getInt("PSTE_ID");
						}
						if(pste_id != 0){
							for(int column=6;column<126;column++){
								HashMap map = new HashMap();
								
								if(column!=120){
									int projId = 0;
									switch(i){
									case 0: projId = 9; break;
									case 1: projId = 6; break;
									case 2: projId = 7; break;
									case 3: projId = 1; break;
									case 4: projId = 3; break;
									case 5: projId = 4; break;
									case 6: projId = 2; break;
									}
									int techId = column-5;
									pstmPJTE.setInt(1, projId);
									pstmPJTE.setInt(2, techId);
									ResultSet pjte = pstmPJTE.executeQuery();
									int pjteId =0;
									while(pjte.next()){
										pjteId = pjte.getInt("PJTE_ID");
									}
									
									if(pjteId !=0){
										
										Cell differenceCell = sheets[i].getCell(column, row);
										String difference = differenceCell.getContents().trim();
										if(difference!=null && !difference.equals("")){
											if(difference.startsWith("#")){
												difference = "";
											}
										}
										Cell coreCell = sheets[i+7].getCell(column, row);
										String core = coreCell.getContents().trim();
										if(core!=null && !core.equals("")){
											if(core.startsWith("#")){
												core = "";
											}
										}
										map.put("pjteId", String.valueOf(pjteId));
										map.put("psteId", String.valueOf(pste_id));
										map.put("ptpjCore", core);
										map.put("ptpjDifference", difference);
										
										list.add(map);
									}
								}
							}
						}
					}else{
						System.out.println(name);
					}
				}
			}
			
			for(int i=0;i<sheets.length;i++){
				System.out.println(sheets[i].getRows());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void importData(List list){
		Connection conn = ImportToPSTE.getConn();
		String insert = "insert into PDCPTPJ(PTPJ_ID,PJTE_ID,PSTE_ID,PTPJ_DIFFERENT,PTPJ_CORE)VALUES(?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(insert);
			for(int i=0;i<list.size();i++){
				Map map = (Map) list.get(i);
				pstm.setInt(1, i+1);
				pstm.setInt(2, Integer.parseInt((String) map.get("pjteId")));
				pstm.setInt(3, Integer.parseInt((String) map.get("psteId")));
				String different = (String) map.get("ptpjDifference");
				String core = (String) map.get("ptpjCore");
				if(different!=null && !different.equals("")){
					pstm.setFloat(4, Float.parseFloat(different));
				}else{
					pstm.setNull(4, Types.FLOAT);
				}
				if(core!=null && !core.equals("")){
					pstm.setFloat(5, Float.parseFloat(core));
				}else{
					pstm.setNull(5, Types.FLOAT);
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
			// TODO Auto-generated catch block
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
	}
	
	public static void main(String[] args) {
		ImportToPTPJ ptpj = new ImportToPTPJ();
		List list = ptpj.read();
		int size = list.size();
		
		for(int i=0;i<size;i++){
			HashMap map = (HashMap) list.get(i);
			System.out.println(map);
		}
		System.out.println(size);
		
		//ptpj.importData(list);
	}

}
