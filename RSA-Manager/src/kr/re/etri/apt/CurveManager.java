package kr.re.etri.apt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurveManager extends Thread{
	
	DbConnection dbm;
	
	String host_ip;
	
	ResultSet rs;
	
	public CurveManager(DbConnection d, String ip){
		
		
		this.dbm = d;
		
		host_ip = ip;
		
		
	}

	
	/**
	 * 
	 */
	
	public void run(){
		
		
		while(true){
			int choice = (int)(Math.random() * 100) + 1;
			
			if( choice < 65){
				
				updateLength();
			}
			else {
				updateSpeed();
			}
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			AnalysisHost();
			
			checkColor();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}


	private void AnalysisHost() {
		// TODO Auto-generated method stub
		/*
		String query = new String("SELECT host_ip, count(*) FROM apt_dashboard.killchain_info group by host_ip;");
		int count = 0;
		System.out.println(query);
		
		try {
			rs = dbm.stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String update = new String();
		while(rs.next()){
			
			String ip = rs.getString(1);
			String cnt = rs.getString(2);
			
			if(cnt > 10){
				update = new String("")
			}
			
		}*/
		
		if(host_ip.equals("192.168.120.41") ||host_ip.equals("192.168.120.42")){
			String uc = new String("UPDATE mainview_curve SET color = 2 WHERE line_name='second' and host_ip = '"+host_ip+"\';");
			try {
				dbm.stmt.executeUpdate(uc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(host_ip.equals("192.168.120.46")){
			String uc = new String("UPDATE mainview_curve SET color = 3 WHERE line_name='second' and host_ip = '"+host_ip+"\';");
			try {
				dbm.stmt.executeUpdate(uc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	
	private void checkColor() {
		// TODO Auto-generated method stub
		
		String query = new String("SELECT count(*) FROM process_list_info WHERE state = 1 and host_ip = '"+host_ip+"\';");
		
		int count = 0;
		System.out.println(query);
		
		try {
			rs = dbm.stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			if(rs != null && rs.next()){
				
				count = Integer.parseInt(rs.getString(1));
				System.out.println("COUNT : "+count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count > 0){
			
			String uc = new String("UPDATE mainview_curve SET color = 3 WHERE line_name='second' and host_ip = '"+host_ip+"\';");
			try {
				dbm.stmt.executeUpdate(uc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		query = new String("SELECT count(*) FROM corelation_stat WHERE occur_host_ip = '"+host_ip+"\';");
		
        int co_count = 0;
		System.out.println(query);
		
		try {
			rs = dbm.stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			if(rs != null && rs.next()){
				
				co_count = Integer.parseInt(rs.getString(1));
				System.out.println("COUNT : "+count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		if(co_count > 0 ){
			
			String uc = new String();
			if(host_ip.equals("192.168.120.46")){
				uc = new String("UPDATE mainview_curve SET color = 3 WHERE line_name='second' and host_ip = '"+host_ip+"\';");
			}
			else {
				uc = new String("UPDATE mainview_curve SET color = 4 WHERE line_name='second' and host_ip = '"+host_ip+"\';");
			}
			try {
				dbm.stmt.executeUpdate(uc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String uc2 = new String("UPDATE mainview_curve SET color = 3 WHERE line_name='first' and host_ip = '"+host_ip+"\';");
			try {
				dbm.stmt.executeUpdate(uc2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	private void updateSpeed() {
		// TODO Auto-generated method stub
		
	
		
		int speed = getSpeed();
		
		//"UPDATE dashboard_detect SET unknownConnection="+uc+", unknownService="+uk+", innerConnection="+ic+" WHERE ipHost='192.168.10.12'"
		
		String sql = new String("UPDATE mainview_curve SET speed=" + speed +" WHERE line_name='first' and host_ip = '"+host_ip+"\';");
		
		System.out.println(sql);
		try {
			dbm.stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		speed = getSpeed();
		
		//"UPDATE dashboard_detect SET unknownConnection="+uc+", unknownService="+uk+", innerConnection="+ic+" WHERE ipHost='192.168.10.12'"
		
		sql = new String("UPDATE mainview_curve SET speed=" + speed +" WHERE line_name='second' and host_ip = '"+host_ip+"\';");
		
		System.out.println(sql);
		try {
			dbm.stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}


	private void updateLength() {
		// TODO Auto-generated method stub
		
		int length = getLength();
		
		String sql = new String("UPDATE mainview_curve SET length=" + length +" WHERE line_name='first' and host_ip = '"+host_ip+"\';");
		
		System.out.println(sql);
		try {
			dbm.stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		length = getLength();
		
		sql = new String("UPDATE mainview_curve SET length=" + length +" WHERE line_name='second'and host_ip = '"+host_ip+"\';");
		
		System.out.println(sql);
		try {
			dbm.stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


	private int getSpeed() {
		// TODO Auto-generated method stub
		int speed = (int)(Math.random() * 5) + 1;
		
		return speed;
	}


	private int getLength() {
		// TODO Auto-generated method stub
		int length = (int)(Math.random() * 5) + 1;
		
		return length;
	}
}
