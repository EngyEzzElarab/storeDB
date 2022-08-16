package dbConnection;

import java.sql.*;

import Functionalities.addQuantity;
import Functionalities.itemInsertion;
public class MySQLConn {
	
	
				public static Connection connectToDB() {
					
					Connection connection = null;
							try 
							{
								 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/STORE", "root", "ya33");
								
								System.out.println("Connected With the database successfully");
							

							}
							catch(SQLException e)
							{
								System.out.println("Error while connecting to the database");
							}
							return connection;
				}

//		public static void main(String[] args) {
//			try 
//			{
//				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/STORE", "root", "ya33");
//				
//				System.out.println("Connected With the database successfully");
////				Statement st = connection.createStatement();
//				//itemInsertion.insertNewItem(connection,11,"bataa","hbbty",2.0,3.0,1);
//				addQuantity.addQuantityToStore(connection, 1, 2, 9);
//				//st.close();      
//				connection.close();
//
////				ResultSet result = st.executeQuery("SELECT * FROM Item;");
////				String id = "";
////				String name = "";
////				String location = "";
////				ResultSetMetaData rsMetaData = result.getMetaData();
////
////			      int count = rsMetaData.getColumnCount();
////			      for(int i = 1; i<=count; i++) {
////			         System.out.print(rsMetaData.getColumnName(i)+" ");
////			      }
////			      System.out.println();
////				while(result.next())
////				{
////					id = result.getString(1);
////					name = result.getString(2);
////					location = result.getString(3);
////					
////					 System.out.println( id + "   "+name+"   "+location);
////				}
////				result.close();                       
//			}
//			catch(SQLException e)
//			{
//				System.out.println("Error while connecting to the database");
//			}
//			
//		}
}
