package importData;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class ImportToPRES {
	private String filePath = "D:\\ljq\\PDC\\20140422_PDC_Resource.xls";
	
	public static void main(String args[]){
		ImportToPRES iport = new ImportToPRES();
		List list = iport.read();
		for(java.util.Iterator it = list.iterator();it.hasNext();){
			Map map = (Map) it.next();
			System.out.println(map);
		}
		iport.importData(list);
	}
	
	@SuppressWarnings("deprecation")
	public Date convertToDate(String s){
		if((!"".equals(s))&& s!= null){
			System.out.println(s);
			String[] str = s.split("-");
			int year = Integer.parseInt("20"+str[2]);
			int month = Integer.parseInt(str[1]);
			int day = Integer.parseInt(str[0]);
			Date date = new Date(year, month, day);
			return date;
		}else
			return null;
		
		
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
	public List read(){
		List press = new ArrayList();
		File file = new File(filePath);
		 
		try {

			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			for(int row=3;row<22;row++){
				Map values = new HashMap();
				for(int column=0;column<27;column++){
					Cell cell = sheet.getCell(column, row);
					String value = cell.getContents();
					switch(column){
					case 0: if("Newtouch".equals(value.trim())){
								values.put("suppId", 1);
							}else if("Wistron".equals(value.trim())){
								values.put("suppId", 2);
							}
						break;
					case 1:values.put("presChiName", value);
						break;
					case 2:values.put("presPinyin", value);
						break;
					case 3: String firstName = (String) values.get("presPinyin");
							String pinyin = firstName+value;
							values.put("presPinyin", pinyin);
						break;
					case 4:values.put("presPsaId", value);
						break;
					case 5:values.put("presSex", value);
						break;
					case 6:if("yes".equals(value.trim())){
								values.put("presCritical", "Y");
							}else if("No".equals(value.trim())){
								values.put("presCritical", "N");
							}else{
								values.put("presCritical", "");
							}
						
						break;
					case 7:values.put("presSite", value);
						break;
					case 8:values.put("presDimissionDate", value);
						break;
					case 9:if("Dev".equals(value.trim())){
								values.put("roleId", 1);
							}else if("Int.".equals(value.trim())){
								values.put("roleId", 2);
							}else if("TL".equals(value.trim())){
								values.put("roleId",3);
							}
						break;
					case 10:if("Senior".equals(value.trim())){
								values.put("leveId", 1);
							}else if("Semi-senior".equals(value.trim())){
								values.put("leveId", 2);
							}else {
								values.put("leveId", 3);
							}
						break;
					case 11:if("".equals(value)){
								values.put("presExpYear", 1);
							}else
								values.put("presExpYear", Integer.parseInt(value));
						break;
					case 12:if("".equals(value)){
								values.put("presExpPsa", 0);
							}else if("Dev".equals(value.trim())){
								values.put("presExpPsa", 1);
							}else
								values.put("presExpPsa", Integer.parseInt(value));
						break;
					case 13:
						if("Dev".equals(value.trim())){
							values.put("supfId", 1);
						}else if("Int.".equals(value.trim())){
							values.put("supfId", 2);
						}else if("PM".equals(value.trim())){
							values.put("supfId", 3);
						}else if("TL".equals(value.trim())){
							values.put("supfId", 4);
						}
						break;
					case 14:values.put("presMissionOnSite", value);
						break;
						
					case 15:values.put("presExpSifa", value);
						break;
					case 16:values.put("presExpSipp", value);
						break;
					case 17: values.put("presExpSpcd", value);
						break;
					case 18: values.put("presExpIbs", value);
						break;
					case 19: values.put("presExpSicv", value);
						break;
					case 20: values.put("presExpSibs", value);
						break;
					case 21: values.put("presExpSdga", value);
						break;
					case 22: values.put("presOther", value);
						break;
					case 23: values.put("presRpt", value);
						break;
					case 24: values.put("presCriActionPlan", value);
						break;
					case 25: values.put("presComment", value);
						break;
					case 26: if("Liu Yi".equals(value.trim())){
								values.put("cpiId", 1);
							}else if("Liu Xing".equals(value.trim())){
								values.put("cpiId", 2);
							}else if("Zeng Dihao".equals(value.trim())){
								values.put("cpiId", 3);
							}else if("Hao HAN".equals(value.trim())){
								values.put("cpiId", 4);
							}else{
								values.put("cpiId", "");
							}
						break;
					}
					
				}
				press.add(values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return press;
	}
	
	
	public void importData(List list){
		Connection conn = getConn();
		String sql = "Insert into PDCPRES (PRES_MISSION_ON_SITE,SUPP_ID,PRES_CHI_NAME,PRES_PINYIN,PRES_PSA_ID,PRES_SEX,"+
			"PRES_SITE,PRES_DIMISSION_DATE,ROLE_ID,LEVE_ID,PRES_EXP_YEAR,SUPF_ID,"+
			"PRES_EXP_SIFA,PRES_EXP_SIPP,PRES_EXP_SPCD,PRES_EXP_SICV,PRES_EXP_IBS,PRES_EXP_SIBS,"+
			"PRES_EXP_SDGA,PRES_EXP_OTHER,PROJ_ID,PRES_CRITICAL,PRES_COMMENT,PRES_RPT,"+"" +
					"PRES_CRI_ACTION_PLAN,PRES_ID,PRES_EXP_PSA,PRES_EXP_DASI)"+
					" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				Map value = (Map) list.get(i);
				pstm.setString(1, (String) value.get("presMissionOnSite"));
				pstm.setInt(2, Integer.parseInt(value.get("suppId").toString()) );
				pstm.setString(3, (String) value.get("presChiName"));
				pstm.setString(4, (String) value.get("presPinyin"));
				pstm.setString(5, (String) value.get("presPsaId"));
				pstm.setString(6, (String) value.get("presSex"));
				pstm.setString(7, (String) value.get("presSite"));
				pstm.setDate(8, convertToDate( (String)value.get("presDimissionDate")));
				pstm.setInt(9, Integer.parseInt(value.get("roleId").toString()) );
				pstm.setInt(10, Integer.parseInt(value.get("leveId").toString()) );
				pstm.setInt(11, Integer.parseInt(value.get("presExpYear").toString()) );
				pstm.setInt(12, Integer.parseInt(value.get("supfId").toString()) );
				pstm.setString(13, (String) value.get("presExpSifa"));
				pstm.setString(14, (String) value.get("presExpSipp"));
				pstm.setString(15, (String) value.get("presExpSpcd"));
				pstm.setString(16, (String) value.get("presExpSicv"));
				pstm.setString(17, (String) value.get("presExpIbs"));
				pstm.setString(18, (String) value.get("presExpSibs"));
				pstm.setString(19, (String) value.get("presExpSdga"));
				pstm.setString(20, (String) value.get("presExpOther"));
				pstm.setString(21, "");
				pstm.setString(22, (String) value.get("presCritical"));
				pstm.setString(23, (String) value.get("presComment"));
				pstm.setString(24, (String) value.get("presRpt"));
				pstm.setString(25, (String) value.get("presCriActionPlan"));
				pstm.setInt(26, i+71);
				/*if(value.get("cpiId")!=null){
					String cpiId = value.get("cpiId").toString();
					if("".equals(cpiId)){
						pstm.setNull(27, Types.NUMERIC);
					}else{
						pstm.setInt(27,  Integer.parseInt(value.get("cpiId").toString()));
					}
				}else{
					pstm.setNull(27, Types.NUMERIC);
				}*/
				
				
				pstm.setInt(27, Integer.parseInt(value.get("presExpPsa").toString()) );
				pstm.setString(28, "");
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
		} finally{
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
}






