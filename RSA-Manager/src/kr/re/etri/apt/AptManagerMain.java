package kr.re.etri.apt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AptManagerMain extends Thread{
	
	
	DbConnection db;
	
	
	CurveManager[] curve;
		
	ProcessManager[] host;
	
	
	//ExtConnManager ext1;
	//ExtConnManager ext2;
	ExtConnManager[] ext;
	
	ArrayList<String> hList;
		
	public AptManagerMain(){
		
		int type = 2;
		
		db = new DbConnection(type);
		//curve = new CurveManager(db);
		
		//host1 = new ProcessManager(db, "192.168.120.50");
		
		
		hList = getHostList();
		
		host = new ProcessManager[hList.size()];
		
		for(int i = 0 ; i<hList.size() ; i++){
			
			host[i] = new ProcessManager(db, hList.get(i));
			
			
		}
		
		ext = new ExtConnManager[hList.size()];
		
		for(int i = 0 ; i<hList.size() ; i++){
			
			ext[i] = new ExtConnManager(db, hList.get(i));
			
			
		}
		
		curve = new CurveManager[hList.size()];
		
		for(int i = 0 ; i<hList.size() ; i++){
			
			curve[i] = new CurveManager(db, hList.get(i));
			
			
		}
		
		
		
		
		//ext1 = new ExtConnManager(db, "192.168.120.50");
		//ext2 = new ExtConnManager(db, "192.168.120.45");
		
		
		//curve.start();
		
	}
	
	
	public void run(){
		
		//curve.start();
		for(int i = 0 ; i< ext.length ; i++){
			curve[i].start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//host1.start();
		for(int i = 0 ; i< ext.length ; i++){
			
			host[i].start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0 ; i< ext.length ; i++){
			ext[i].start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		//ext1.start();
		//ext2.start();
	}
	
	
	public ArrayList<String> getHostList(){
		
		String qry = new String("SELECT host_ip FROM host_list_info WHERE type = 'host';");
		
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			db.rs = db.stmt.executeQuery(qry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(db.rs.next()){
				
				list.add(db.rs.getString("host_ip"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub

		/*
		dashboard dash = new dashboard(2);
		
		networkGraph ng = new networkGraph(2);
		
		dash.start();
		//ng.start();
		*/
		
		AptManagerMain apt = new AptManagerMain();
		
		apt.run();
		
		
	}
	
	
}

