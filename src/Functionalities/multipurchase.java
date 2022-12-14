package Functionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multipurchase {
	
						public static int getActualQuantityOfItem(Connection conn,int itemID) throws SQLException {
							String query = "SELECT SUM(Quantity) FROM ItemStoreRelation GROUP BY ItemID HAVING ItemID="+itemID+";";
							Statement st = conn.createStatement();
							 ResultSet result = st.executeQuery(query);
							 result.next();
							 String amount = result.getString(1);
							 System.out.println("ACTUAL QUANTITY OF ITEM "+amount);
							return Integer.parseInt(amount);
						}
					
						public static int getNumberOfCustomers(Connection conn) throws SQLException {
							
							String query ="SELECT COUNT(*) FROM Customer;";
							System.out.println(query);
							 Statement st = conn.createStatement();
							 ResultSet result = st.executeQuery(query);
							 result.next();
							 String count = result.getString(1);
							// System.out.println(count);
							return Integer.parseInt(count);
						}

				public static void batchPurchase(Connection conn,int numOfCustomers, int itemID, int quantity) throws SQLException
				{
						int actualNUmOfCust = getNumberOfCustomers(conn);
						int n = numOfCustomers-actualNUmOfCust;
						
						for(int i=0;i<n;i++)
						{
							//create customers
							String query = "INSERT INTO Customer VALUES( ?, ?, ?);";

						    PreparedStatement preparedStatement = conn.prepareStatement(query);
						    preparedStatement.setInt(1, actualNUmOfCust+i+1);
						    preparedStatement.setString(2, "testName");
						    preparedStatement.setString(3, "testAddress");
						    preparedStatement.executeUpdate(); 
							System.out.println("A new customer has been inserted successfully");
						}
						
						int requiredQuantity = quantity*numOfCustomers;
						int actuaQuantity = getActualQuantityOfItem(conn,itemID);
						System.out.println(requiredQuantity);
						System.out.println(actuaQuantity);
						if(requiredQuantity>actuaQuantity)
								addQuantity.addQuantityToStore(conn, itemID, 1, requiredQuantity-actuaQuantity);
						// make purchase requiredQuantity times
						ExecutorService executorService = null;
						 executorService = Executors.newFixedThreadPool(numOfCustomers);
						 CountDownLatch latch = new CountDownLatch(numOfCustomers);
						for(int i=0;i<numOfCustomers;i++)
						{
							//purchase.makePurchase(conn, i+1, itemID, quantity);
						    	int x = i+1;
							  executorService.execute(()->{
								  	purchase p = new purchase(conn,x,itemID,quantity,latch);
									p.start();
							  });		
						}		
							  try {
								latch.await();
							} catch (InterruptedException e) {
								System.out.println("Exception from latch");
							}
							  executorService.shutdown(); 
				}

				
}
