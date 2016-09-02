package kr.re.etri.apt;

import java.sql.SQLException;

public class ProcessManager extends Thread{

	DbConnection dbm;
	
	String[] pList;
	
	String host_ip;
	
	public ProcessManager(DbConnection d, String ip){
		
		
		this.dbm = d;
		
		host_ip = ip;
		
		/*
		pList = {"svchost.exe","vmtoolsd.exe","TPAutoConnSvc.exe","Explorer.EXE",
				"SearchIndexer.exe","wininit.exe","lsass.exe","taskhost.exe","winlogon.exe","services.exe",
				"lsm.exe","spoolsv.exe","dllhost.exe","msdtc.exe","Dwm.exe","TPAutoConnect.exe","conhost.exe",
				"mscorsvw.exe","sppsvc.exe","wmiprvse.exe","cmd.exe","SearchProtocolHost.exe","SearchFilterHost.exe","Nmdeputy.exe",
				"tvnserver.exe","vmnat.exe","vmnetdhcp.exe","vmware-usbarbitrator64.exe","vmware-authd.exe","vmware-hostd.exe","WUDFHost.exe",
				"vmware-tray.exe","jusched.exe","OutProcSite.exe","ComBack.exe","YiStart.exe","WMIADAP.EXE","jucheck.exe","javaw.exe"};
		*/
		
		
		String[] p= {"svchost.exe","vmtoolsd.exe","TPAutoConnSvc.exe","Explorer.EXE",
				"SearchIndexer.exe","wininit.exe","lsass.exe","taskhost.exe","winlogon.exe","services.exe","lsm.exe",
				"spoolsv.exe","dllhost.exe","msdtc.exe","Dwm.exe","TPAutoConnect.exe","conhost.exe","mscorsvw.exe","sppsvc.exe","wmiprvse.exe",
				"cmd.exe","SearchProtocolHost.exe","SearchFilterHost.exe","Nmdeputy.exe","tvnserver.exe","vmnat.exe","vmnetdhcp.exe","vmware-usbarbitrator64.exe",
				"vmware-authd.exe","vmware-hostd.exe","WUDFHost.exe","vmware-tray.exe","jusched.exe","OutProcSite.exe","ComBack.exe","YiStart.exe","WMIADAP.EXE",
				"jucheck.exe","javaw.exe","svchost.exe","TPAutoConnSvc.exe","vmtoolsd.exe","Explorer.EXE","SearchIndexer.exe","mscorsvw.exe","lsass.exe","taskhost.exe",
				"services.exe","wininit.exe","winlogon.exe","lsm.exe","spoolsv.exe","dllhost.exe","msdtc.exe","Dwm.exe","TPAutoConnect.exe","conhost.exe","sppsvc.exe","cmd.exe",
				"WMIADAP.EXE","wmiprvse.exe","SearchProtocolHost.exe","SearchFilterHost.exe","consent.exe","IEInstal.exe","InstallFlashPlayer.exe", "Hwp.exe", "MSWord.exe"};
		
		
		pList = p;
		
	}
	
	public void run(){
		
		while(true){
			
			
			//DELETE FROM network_traffic_graph WHERE currentTIme <= date_add(now(), interval -3 minute);
			String delete = new String("DELETE FROM process_list_info WHERE host_ip=\'"+host_ip+"\' and process_name not like '320%';");
			System.out.println(delete);
			
			try {
				dbm.stmt.executeUpdate(delete);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//입력할 갯
			int r = (int)(Math.random()*5)+15;
			
			//int k = pList.length;
			
			for(int i = 0; i < r ; i++){
				int pl = (int)(Math.random()* pList.length);
		
				String insert = new String();
				
				//11.12 delete
				/*
				if( i == 2 && host_ip.equals("192.168.120.45")){
					insert = new String("INSERT process_list_info SET process_name = \'320Virus3.exe\', state =2, host_ip=\'"+host_ip+"\';");
				}
				*/
				
				if( i == 5 && host_ip.equals("192.168.120.38")){
					insert = new String("INSERT process_list_info SET process_name = \'nateon.exe\', state =2, host_ip=\'"+host_ip+"\';");
				}
				
				else if( i == 7 && host_ip.equals("192.168.120.46")){
					insert = new String("INSERT process_list_info SET process_name = \'lsscs.exe\', state =2, host_ip=\'"+host_ip+"\';");
				}
				
				else if( i == 3 && host_ip.equals("192.168.120.60")){
					insert = new String("INSERT process_list_info SET process_name = \'svchost.exe\', state =2, host_ip=\'"+host_ip+"\';");
				}
				
				else {
					insert = new String("INSERT process_list_info SET process_name = \'"+pList[pl]+"\', host_ip=\'"+host_ip+"\';");
				}
				System.out.println(insert);
				
				try {
					dbm.stmt.executeUpdate(insert);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	private void check_alert() {
		// TODO Auto-generated method stub
		
	}
	
	
}
