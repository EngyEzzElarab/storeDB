package Functionalities;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import dbConnection.MySQLConn;

public class Parser {
	
					public static void parseCommand(Connection conn,String input) throws  SQLException
					{
								
								String command = input.split(" ")[0];
								String[] params=null;
								String date="";
								if(command.equals("report"))
									   date = input.split(" ")[1];
								if(!command.equals("report") && input.split(" ").length>1)
									 params = input.split(" ")[1].split(",");
							
								
							//	System.out.println(Arrays.toString(params));
								
								switch(command)
								{
											case "insert": itemInsertion.insertNewItem(conn, Integer.parseInt(params[0]), params[1],params[2],Double.parseDouble(params[3]), Double.parseDouble(params[4]), Integer.parseInt(params[5])); ;break;
											case "add":  addQuantity.addQuantityToStore(conn, Integer.parseInt(params[0]),  Integer.parseInt(params[1]),  Integer.parseInt(params[2]));;break;
											case "purchase":purchase.makePurchase(conn,Integer.parseInt(params[0]),Integer.parseInt(params[1]),Integer.parseInt(params[2]));break;
											case "zero": emptyStock.displayZeroQuantity(conn);break;
											case "report": DailyReports.outputReports(conn,date);break;
											case "mostPurchased": bestSellingItem.getMostPurchased(conn);break;
											case "multiPurchase": multipurchase.batchPurchase(conn,Integer.parseInt(params[0]),Integer.parseInt(params[1]),Integer.parseInt(params[2]));break;

								}
					}
					public static void main(String[] args) throws SQLException, ParseException {
						
						Connection connection = MySQLConn.connectToDB();
						Scanner sc = new Scanner(System.in);
						String in = sc.nextLine();
						parseCommand(connection,in);
						connection.close();
						 
					        
					    
					       
						
						 

					}

}
