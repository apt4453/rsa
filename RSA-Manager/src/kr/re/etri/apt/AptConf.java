package kr.re.etri.apt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ��Ʈ��ũ �м� ����� configure Ŭ�����ν�, MySql-Cluster �� ���� ����, ���� ����� �������� ������
 * 
 * @author JONG HOON
 *
 */
public class AptConf {

	//static int HADOOP_MAX = 100000;
	static String DbServer;
	static String DbUser;
	static String DbPort;
	static String DbPasswd;
	static String DbName;
	
	static String Local_Network;
	static int window;
	
	static int CRAB_PORT;
	static String CRAB_IP;
	
	static String Nodes;
	
	public AptConf(){
		
		//DbServer+":"
		//		+conf.DbPort+"/"+conf.DbCategoryName, conf.DbUser, conf.DbPasswd);
		
		Properties proc = new Properties();
		
		String path = System.getProperty("user.dir")+"/";
		//System.out.println("Current Working Dir = "+path);
		
		FileInputStream sf = null;
		try {
			sf = new FileInputStream(path+"apt.inf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			proc.load(sf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DbServer = proc.getProperty("DbServerIp").trim();
		DbUser = proc.getProperty("DbUser").trim();
		DbPort = proc.getProperty("DbPort").trim();
		DbPasswd = proc.getProperty("DbPassword").trim();
		DbName = proc.getProperty("NetworkDBName").trim();
		Local_Network = proc.getProperty("local_Network_prefix").trim();
		window = Integer.parseInt(proc.getProperty("RealtimeWindowSize").trim());
		
		CRAB_IP = proc.getProperty("CRAB_IP").trim();
		CRAB_PORT = Integer.parseInt(proc.getProperty("CRAB_PORT").trim());
		Nodes = proc.getProperty("Nodes").trim();
		
		

	}
	
	
}
