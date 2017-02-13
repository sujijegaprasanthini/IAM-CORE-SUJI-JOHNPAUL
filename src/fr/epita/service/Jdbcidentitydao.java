/**
 * 
 */
package fr.epita.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epita.datamodel.Identity;

/**
 * @author SUJI JOHN
 *
 */
public class Jdbcidentitydao {


	
	
	private Connection connection;
	
	
	public  Jdbcidentitydao() throws SQLException { // return throw exception 
		this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/suji;create=true","John","John");
		System.out.println(connection.getSchema());
	}
	
	
	public void writeIdentity(Identity identity) throws SQLException {
		String insertStatement = "insert into IDENTITIES (IDENTITIES_DISPLAYNAME, IDENTITIES_EMAIL, IDENTITIES_BIRTHDATE) "
				+ "values(?, ?, ?)";
		PreparedStatement pstmt_insert = connection.prepareStatement(insertStatement);
		pstmt_insert.setString(1, identity.getDisplayName()); // insert display name 
		pstmt_insert.setString(2, identity.getEmail()); // insert email
		Date now = new Date(); // display date 
		pstmt_insert.setDate(3, new java.sql.Date(now.getTime()));
		pstmt_insert.execute();
	}

	
///////////////////////////////////////////////*
/* code for modify identity 
*///////////////////////////////////

	
	public void modifyidentity(Identity identity) throws SQLException {
		String modifystatement=	"update IDENTITIES set IDENTITIES_EMAIL=?,IDENTITIES_DISPLAYNAME=? where IDENTITIES_UID=?";
		//c.getdel_id();

		PreparedStatement pstmt_modify = connection.prepareStatement( modifystatement);
		pstmt_modify.setString(1, identity.getEmail() ); // modify email
		pstmt_modify.setString(2, identity.getDisplayName() ); // modify display name 
		pstmt_modify.setString(3, identity.getUid()); //  modify by UID numbers 
		pstmt_modify.execute();
		}
	
	
	
///////////////////////////////////////////////*
/* code for delete identity 
*///////////////////////////////////
	public void deleteidentity(Identity identity) throws SQLException {
	    String deletestatement=	"delete from IDENTITIES where IDENTITIES_UID=?";
	//c.getdel_id();

	PreparedStatement pstmt_delete = connection.prepareStatement(deletestatement);
	pstmt_delete.setString(1, identity.getUid()); // delete by UID number
	pstmt_delete.execute();
	}
	
	// creating table 
	public List<Identity> readAll() throws SQLException {
		List<Identity> identities = new ArrayList<Identity>();

		PreparedStatement pstmt_select = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = pstmt_select.executeQuery();
		while (rs.next()) {
			String displayName = rs.getString("IDENTITIES_DISPLAYNAME"); //display name
			String uid = String.valueOf(rs.getString("IDENTITIES_UID")); // UID numbers
			String email = rs.getString("IDENTITIES_EMAIL"); // list of email
			Identity identity = new Identity(uid, displayName, email );
			identities.add(identity);
		}
		return identities;

	}

}


