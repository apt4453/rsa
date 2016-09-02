package kr.re.etri.apt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
AptConf conf ;
	
	
	/**
	 * statement;
	 */
	public Statement stmt;
	
	/**
	 * DB Connection
	 */
	public Connection conn;
	
	/**
	 * result set
	 */
	public ResultSet rs;
	
	
	public DbConnection(int type){
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (Exception ex) {
					//System.out.println(ex);
				}
		          
				
				conf = new AptConf();
				
				
				
				try{
					if(type == 1){
						
						//String database = "apt";
						String database = "network";
						String user = "apt";
						String passwd = "apt";
						String nodes = conf.Nodes;

						
						conn = DriverManager.getConnection("jdbc:mysql:loadbalance://" + nodes + "/" + database + "?user=" + user
			                + "&password=" + passwd + "&loadBalanceBlacklistTimeout=5000");
					}
					
				
					else{
						//conn = DriverManager.getConnection("jdbc:mysql://"+DbInfo.DB_URL+":3306/"+DbInfo.DB_TAG, DbInfo.username, DbInfo.passwd);
						conn = DriverManager.getConnection("jdbc:mysql://"+conf.DbServer+":"
								+conf.DbPort+"/"+conf.DbName, conf.DbUser, conf.DbPasswd);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					stmt = conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
	}

}
