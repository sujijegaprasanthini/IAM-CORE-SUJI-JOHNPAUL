package fr.epita.launcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.datamodel.Identity;
//import fr.epita.service.Fileidentitydao;
import fr.epita.service.Jdbcidentitydao;

public class Mainlauncher {	private static Jdbcidentitydao dao;



	public static void main(String[] args) 	throws IOException, SQLException { // args, throws IOexception,  throws sqlexception
		System.out.println("Hello, welcome to the IAM application"); // welcome messsage 
		Scanner scanner = new Scanner(System.in);
		dao = new Jdbcidentitydao();
		
		
		
		//authentication
		System.out.println("Please enter your login"); // code for enter your login id
		String login = scanner.nextLine();
		System.out.println("Please enter your password"); // code for enter your password 
		String password = scanner.nextLine();
		
		if(!authenticate(login, password)){  // if it is wrong it will closed
			scanner.close();
			return;
		}
		
		// menu
		String answer = menu(scanner);
		
		switch (answer) {
		case "a":
			// creation
			createIdentity(scanner);
			break;
		case "b":
			// modifying
			modifyidentity(scanner);
			break;
		case "c":
			// delete
			deleteidentity(scanner);
			break;
		case "d":
			// display 
			listIdentities();
			break;
		default:
			System.out.println("This option is not recognized ("+ answer + ")");
			break;
		}
		
		scanner.close();

	}

	
	private static void listIdentities() throws SQLException {  // throws sql exception
		System.out.println("This is the list of all identities in the system");
		List<Identity> list = dao.readAll();
		int size = list.size();
		for(int i = 0; i < size; i++){
			System.out.println( i+ "." + list.get(i));
		}
		
	}

	// create identity 
	private static void createIdentity(Scanner scanner) throws SQLException {  // scanner and throws sqlexception
		System.out.println("You've selected : Identity Creation"); // code for option a 
		System.out.println("Please enter the Identity display name"); // for create identity you need to enter your display name
		
		String displayName = scanner.nextLine();
		System.out.println("Please enter the Identity email"); // enter your email
		String email = scanner.nextLine(); 
		Identity newIdentity = new Identity(null, displayName, email); // your identity will display like this order
		dao.writeIdentity(newIdentity);
		System.out.println("you successfully created this identity :" + newIdentity); // message of successful creation 
	}
	
	
	
	// modify identity 
	private static void modifyidentity(Scanner scanner) throws SQLException{
		System.out.println("you have selected modify identity"); // code for option b  
		
		System.out.println("enter the user id you want to modify"); 

		String id= scanner.nextLine();
		
		
		
		 
		 System.out.println("enter the user name"); // enter your new name 

			String name= scanner.nextLine();
			
			 System.out.println("enter the user mail"); // enter your new mail

				String mail= scanner.nextLine();
			
			
			Identity newIdentity = new Identity(id, name, mail);  // it will display like this order
		dao.modifyidentity(newIdentity);
		System.out.println("successfully modified");  // successful message  
		}
	
	
	// delete identities 
	 private static void deleteidentity(Scanner scanner) throws SQLException{
		 System.out.println("you have selected delete identity");  // code for option c 
		 System.out.println("enter the id which you want to delete"); 

		 	
		 String id= scanner.nextLine();
		 Identity newIdentity = new Identity(id, null, null);
		
		 
		 dao.deleteidentity(newIdentity);
		 System.out.println("successfully deleted"); // successful message 

		 }
	
	
	
	 
	 // list of actions 
	private static String menu(Scanner scanner) {   // parameter scanner 
		System.out.println("You're authenticated");
		System.out.println("Here are the actions you can perform :");
		System.out.println("a. Create an Identity");
		System.out.println("b. Modify an Identity");
		System.out.println("c. Delete an Identity");
		System.out.println("d. List Identities");
		System.out.println("e. quit");
		System.out.println("your choice (a|b|c|d|e) ? : ");
		String answer = scanner.nextLine();
		return answer;
	}

	
	// parameter login and password 
	private static boolean authenticate(String login, String password) {

		// TODO replace this hardcoded check by the real authentication method
		return "adm".equals(login) && "pwd".equals(password);
	}

}
