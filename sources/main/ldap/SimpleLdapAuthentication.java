/**
 * 
 */
package ldap;

/**
 * @author Joey
 *
 */
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleLdapAuthentication
{
	static User new_user=new User();



	public static JSONObject searchAndAuthenticate(String username,String password) throws NamingException{
		Hashtable<String, String> env = 
				new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.0.104:389");
		env.put(Context.SECURITY_AUTHENTICATION, "none");

		SearchControls searchCtrls = new SearchControls();
		searchCtrls.setReturningAttributes(new String[] {});
		searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		String filter = "(&(uid=" + username + "))";

		DirContext ctx = null;
		ctx = new InitialDirContext(env);
		NamingEnumeration<SearchResult> answer = ctx.search("dc=iiitk,dc=ac,dc=in", filter, searchCtrls);

		String fullDN = null;
		if (answer.hasMore()) {

			fullDN = answer.next().getNameInNamespace();

			ctx.close();
			ctx = null;

			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, fullDN);
			env.put(Context.SECURITY_CREDENTIALS, password);

			ctx = new InitialDirContext(env);
			Attributes attrs=ctx.getAttributes(fullDN);

			JSONObject credentials = new JSONObject();
			try {
				int index=fullDN.indexOf("ou=")+3;
				String ou=fullDN.substring(index, fullDN.indexOf(",",index));
				new_user.setRole(ou);
				if(ou.equals("students")){credentials.put("type","student");}
				if(ou.equals("faculty")){credentials.put("type","faculty");}
				if(ou.equals("office")){credentials.put("type","office");}
				new_user.setDepartment("computer_science");
				credentials.put("name",attrs.get("givenName").get(0));
				credentials.put("erpId",attrs.get("sn").get(0));
				new_user.setErp_id(attrs.get("sn").get(0).toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return credentials;
		}
		// Exception otherwise ...
		return null;

	}
	/**
	 * @param first_name
	 * @param last_name
	 * @param username
	 * @param password
	 * @param erp_id
	 */
	public static void addEntry(User new_user) {

		Hashtable<String, String> env = 
				new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.0.104:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=iiitk,dc=ac,dc=in");
		env.put(Context.SECURITY_CREDENTIALS, "iiitk_2013");

		try {

			DirContext context = new InitialDirContext(env);
			Attributes attributes = new BasicAttributes();
			Attribute attribute = new BasicAttribute("objectClass");
			attribute.add("inetOrgPerson");
			attributes.put(attribute);
			Attribute pwd = new BasicAttribute("userPassword");
			
			
			pwd.add(new_user.getPassword());
			attributes.put(pwd);
			Attribute sn = new BasicAttribute("sn");
			sn.add(new_user.getErp_id());
			attributes.put(sn);
			Attribute uid = new BasicAttribute("uid");
			uid.add(new_user.getUsername());
			attributes.put(uid);
			Attribute fname = new BasicAttribute("givenName");
			fname.add(new_user.getFirst_name()+" "+new_user.getLast_name());
			attributes.put(fname);
			try {

				context.createSubcontext(
						"cn="+new_user.getUsername()+",cn="+new_user.getDepartment()+",ou="+new_user.getRole()+",dc=iiitk,dc=ac,dc=in",attributes);

			} catch (NamingException e) {

				e.printStackTrace();

			}

			System.out.println("entered successfully");


			context.close();



		} catch (NamingException e) {



			e.printStackTrace();

		}



	}
	public static void addUser(DirContext context) {

		
	}

	public static void changePassword(String username,String new_password){
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL,"ldap://192.168.0.104:389");
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.SECURITY_PRINCIPAL,"cn=admin,dc=iiitk,dc=ac,dc=in");
		env.put(Context.SECURITY_CREDENTIALS,"iiitk_2013");

		try
		{
			// Create the initial directory context
			InitialDirContext initialContext = new InitialDirContext(env);
			DirContext ctx = (DirContext)initialContext;

			System.out.println("Context Sucessfully Initialized");

			ModificationItem[] mods = new ModificationItem[1];

			Attribute mod0 = new BasicAttribute("userPassword",new_password);

			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);

			ctx.modifyAttributes("cn="+username+",cn="+new_user.getDepartment()+",ou="+new_user.getRole()+",dc=iiitk,dc=ac,dc=in", mods);

		}
		catch(Exception e)
		{
			System.err.println(e);
		}

	}
 public static boolean resetPassword(String username,String old_password,String new_password){
	 try {
		searchAndAuthenticate(username,old_password);
		changePassword(username, new_password);
	} catch (NamingException e) {
		System.out.println("Error username or password incorrect");
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
 }
 
	public static void main(String[] args) 
	{
		try {
				searchAndAuthenticate("joeypinto", "megha");
			//resetPassword("joeypinto","joey","joey");
			//changePassword("joeypinto", "12345678");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}






