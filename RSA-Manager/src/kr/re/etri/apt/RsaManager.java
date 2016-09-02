package kr.re.etri.apt;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RsaManager extends Thread{

	DbConnection db;
	
	
	public RsaManager() {
		// TODO Auto-generated constructor stub
		int type = 2;
		
		db = new DbConnection(type);
		
		
	}
	
	public void run(){
		
		String update1 = new String("UPDATE apt_dashboard.corelation_stat SET c_time = date_add(c_time, interval 30 minute) WHERE id < 50;");
		String update2 = new String("UPDATE apt_dashboard.killchain_info SET c_time = date_add(c_time, interval 30 minute) WHERE id < 50;");

		String update3 = new String("UPDATE Host_CollectDB.AlertTable SET time = date_add(time, interval 30 minute) WHERE alert_id < 100;");
		
		while(true){
			
			long time = System.currentTimeMillis();
		    SimpleDateFormat ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    String CurrentTime = ctime.format(new Date(time));
		
		    System.out.println("TIME : " + CurrentTime );
		    
			try {
				db.stmt.executeUpdate(update1);
				System.out.println(update1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				db.stmt.executeUpdate(update2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				db.stmt.executeUpdate(update3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000 * 60 * 30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("RSA Manager start !!");
		RsaManager rsa = new RsaManager();
		rsa.start();
	}

}
