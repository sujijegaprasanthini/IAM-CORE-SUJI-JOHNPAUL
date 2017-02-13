/**
 * 
 */
package fr.epita.datamodel;

/**
 * @author SUJI JOHN
 *
 */
public class Identity {private String uid;
private String displayName;
private String email;




public Identity(String uid, String displayName, String email) { // @param uid, displayname, email
	this.uid = uid;
	this.displayName = displayName;
	this.email = email;
	
}



// return UID
public String getUid() {
	return uid;
}


// return UID to set
public void setUid(String uid) {
	this.uid = uid;
}


// return the display 
public String getDisplayName() {
	return displayName;
}

// display to set 
public void setDisplayName(String displayName) {
	this.displayName = displayName;
}

// return the  email
public String getEmail() {
	return email;
}


// email to set 
public void setEmail(String email) {
	this.email = email;
}


/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Identity [uid=" + uid + ", displayName=" + displayName + ", email=" + email + "]";
}





}
