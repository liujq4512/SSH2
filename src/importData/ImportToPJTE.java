package importData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Scope("prototype")
@Component("importToPJTE")
public class ImportToPJTE {
	
	/**
	 * @param args
	 */
	
	private String filePath = "D:\\ljq\\PDC\\20140422_PDC_Resource.xls";
	
	@SuppressWarnings("unchecked")
	public List read(){
		List list = new ArrayList();
		File file = new File(filePath);
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet1 = workbook.getSheet("ARCAD skill list");
			Sheet sheet2 = workbook.getSheet("Indus skill");
//			Sheet sheet3 = workbook.getSheet("ARCAD skill list");
//			Sheet sheet4 = workbook.getSheet("SOPI skill list");
//			Sheet sheet5 = workbook.getSheet("Sidauto skill list");
//			Sheet sheet6 = workbook.getSheet("Italauto skill list");
//			Sheet sheet7 = workbook.getSheet("GPRO skill");
			for(int row=1;row<115;row++){
				Map map1 = new HashMap();
				Map map2 = new HashMap();
				Map map3 = new HashMap();
				Map map4 = new HashMap();
//				Map map5 = new HashMap();
//				Map map6 = new HashMap();
//				Map map7 = new HashMap();
//				Map map8 = new HashMap();
//				Map map9 = new HashMap();
//				Map map10 = new HashMap();
//				Map map11 = new HashMap();
//				Map map12 = new HashMap();
//				Map map13 = new HashMap();
//				Map map14 = new HashMap();
				
				Sheet[] sheets = new Sheet[]{sheet1,sheet2};
				Map[] maps = new Map[]{map1,map2,map3,map4};
				
				
				for(int i=0;i<2;i++){
					Cell techId = sheets[i].getCell(3, row);
					Cell weight = sheets[i].getCell(5, row);
					String wei = weight.getContents().trim();
					String impoId = weightToImport(wei);
					
					Cell coef = sheets[i].getCell(7, row);
					String teleId = coefToLevelId(coef.getContents().trim(),true);
					Cell mincore = sheets[i].getCell(8, row);
					String projectId = "";
					switch(i){
					case 0: projectId = "8"; break;
					case 1: projectId = "10"; break;
//					case 2: projectId = "7"; break;
//					case 3: projectId = "1"; break;
//					case 4: projectId = "3"; break;
//					case 5: projectId = "4"; break;
//					case 6: projectId = "2"; break;
					
					}
					maps[i].put("projId", projectId);
					maps[i].put("techId", techId.getContents().trim());
					maps[i].put("impoId", impoId);
					maps[i].put("teleId", teleId);
					maps[i].put("mincore", mincore.getContents().trim());
					list.add(maps[i]);
					if(row<6){
						Cell lanWeight = sheets[i].getCell(16, row);
						Cell lanCoef = sheets[i].getCell(18, row);
						Cell lanMincore = sheets[i].getCell(19, row);
						String lanImpoId = weightToImport(lanWeight.getContents().trim());
						String laleId = coefToLevelId(lanCoef.getContents().trim(),false);
						maps[i+2].put("projId", projectId);
						maps[i+2].put("techId", String.valueOf(114+row));
						maps[i+2].put("impoId", lanImpoId);
						maps[i+2].put("laleId", laleId);
						maps[i+2].put("mincore", lanMincore.getContents().trim());
						list.add(maps[i+2]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public String weightToImport(String wei){
		String impoId = "";
		if("0".equals(wei)){
			impoId = "4";
		}else if("2".equals(wei)){
			impoId = "3";
		}else if("6".equals(wei)){
			impoId="2";
		}else{
			impoId = "1";
		}
		return impoId;
	}
	
	public String coefToLevelId(String coef,boolean tech){
		String levelId = "";
		if("0.5".equals(coef)||"10".equals(coef)){
			levelId = "1";
		}else if("0.4".equals(coef)||"6".equals(coef)){
			levelId="2";
		}else if("0.3".equals(coef)||"4".equals(coef)){
			levelId="3";
		}else if("0.2".equals(coef)||"2".equals(coef)){
			levelId = "4";
		}else if("0.1".equals(coef)||("0".equals(coef)&&tech)){
			levelId ="5";
		}else {
			levelId = "6";
		}
		
		
		return levelId;
	}
	
	public void importData(List list){
		Connection conn = ImportToPSTE.getConn();
		String sql = "insert into PDCPJTE(PJTE_ID,PROJ_ID,TECH_ID,IMPO_ID,LALE_ID,TELE_ID) VALUES(?,?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				Map map = (Map) list.get(i);
				pstm.setInt(1, i+834);
				pstm.setInt(2, Integer.parseInt(map.get("projId").toString()));
				pstm.setInt(3, Integer.parseInt((String)map.get("techId")));
				pstm.setInt(4, Integer.parseInt((String)map.get("impoId")));
				String laleId = (String) map.get("laleId");
				String teleId = (String) map.get("teleId");
				if(laleId!=null && !laleId.equals("")){
					pstm.setInt(5, Integer.parseInt(laleId));
				}else{
					pstm.setNull(5, Types.INTEGER);
				}
				if(teleId != null && !teleId.equals("")){
					pstm.setInt(6, Integer.parseInt(teleId));
				}else{
					pstm.setNull(6, Types.INTEGER);
				}
				//pstm.setFloat(7, Float.parseFloat((String) map.get("mincore")));
				pstm.execute();
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		ImportToPJTE pjte = new ImportToPJTE();
		List list = pjte.read();
		for(java.util.Iterator it = list.iterator();it.hasNext();){
			Map map = (Map) it.next();
			System.out.println(map);
		}
		pjte.importData(list);
	}

}
