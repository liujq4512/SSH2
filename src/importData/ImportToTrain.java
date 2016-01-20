package importData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportToTrain {

	/**
	 * @param args
	 */
	private String filePath = "D:\\ljq\\PDC\\20140422_PDC_Training_Plan.xls";
	
	public List read(){
		List list = new ArrayList();
		File file = new File(filePath);
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet("PlanActionFormation");
			System.out.println(sheet.getColumns());
			System.out.println(sheet.getRows());
			for(int row=3; row<10; row++){
				Map map = new HashMap();
				for(int column=0;column<12;column++){
					Cell cell = sheet.getCell(column, row);
					map.put(column, cell.getContents());
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void importData(List list){
		Connection conn = ImportToPSTE.getConn();
		String sql = "insert into PDCTRAIN (TRAIN_ID,TRAIN_TRAINING,TRAIN_PROJECT,TRAIN_PRIORITY,TRAIN_START_DATE,TRAIN_END_DATE,TRAIN_STATUS,TRAIN_DURATION,TRAIN_PERIODE,TRAIN_TRAINER,TRAIN_TRAINEE,TRAIN_REFERENCE,TRAIN_COMMENTS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareCall(sql);
			for(int i=0; i<list.size();i++){
				Map map = (Map) list.get(i);
				pstm.setInt(1, i+30);
				for(int column=0;column<12;column++){
					pstm.setString(column+2, (String) map.get(column));
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
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImportToTrain train = new ImportToTrain();
		List list = train.read();
		for(int i=0;i<list.size();i++){
			Map map = (Map) list.get(i);
			System.out.println(map);
			
		}
		train.importData(list);
	}

}
