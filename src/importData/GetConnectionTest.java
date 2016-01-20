package importData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class GetConnectionTest {
	public static void main(String[] args){
		String s = new String();
		if(s==null){
			System.out.println("null value");
		}else if(s.equals("")){
			System.out.println("empty value");
			System.out.println(s.isEmpty());
		}else{
			Long n = Long.parseLong(s);
			System.out.println(n);
		}
		
		/*String url = "jdbc:oracle:thin:@b801581:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, "PDC", "PDC");
			String sql = "select * from PDCCATE where ? =?";
			PreparedStatement pstm = null;
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "PDCCATE");
			pstm.setString(1, "CATE_NAME");
			pstm.setString(2, "Conception");
		
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("CATA_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
