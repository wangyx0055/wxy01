package com.longersec.blj.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;

import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;

public class AdOperate {
	/** connect to ldap */
	LdapTemplate ldapTemplate = null; 
	
	public AdOperate(String ldapURL, String adminName, String adminPassword, String baseDn) {
		
		LdapContextSource cs = new LdapContextSource();
        cs.setCacheEnvironmentProperties(false);
        cs.setUrl(ldapURL);
        cs.setBase(baseDn);
        cs.setUserDn(adminName);
        cs.setPassword(adminPassword);
        cs.afterPropertiesSet();
        ldapTemplate = new LdapTemplate(cs);
        ldapTemplate.setIgnorePartialResultException(true);
	}
	
	/** add user to AD */
	public void addUser(String newUserName, String userpassword, String groupDN) throws NamingException {
		LdapName userDn = LdapNameBuilder.newInstance("cn="+newUserName+",OU=apppubusers").build();
		try {
			//ldapTeplate的lookup方法是根据dn进行查询，此查询的效率超高
			DirContextAdapter obj = (DirContextAdapter) ldapTemplate.lookup(userDn);
			
		} catch (NameNotFoundException e) {
			DirContextAdapter context = new DirContextAdapter(userDn);
	        context.setAttributeValues("objectclass", new String[] {"user", "organizationalPerson", "person", "top"});
	        context.setAttributeValue("userAccountControl", "512");
	        context.setAttributeValue("sn", newUserName); 
	        context.setAttributeValue("cn", newUserName);
	        context.setAttributeValue("name", newUserName);
	        context.setAttributeValue("displayName", newUserName);
	        context.setAttributeValue("givenName", newUserName);
	        //context.setAttributeValue("userPassword", "lsabcBLSDDJ12");
	        context.setAttributeValue("sAMAccountName", newUserName);
	        context.setAttributeValue("userPrincipalName", newUserName+"@lsblj.cn");
	        context.setAttributeValue("description", newUserName);
	        ldapTemplate.bind(context);

	        LdapName groupDn = LdapNameBuilder.newInstance(groupDN).build();  // 组的dn
	        DirContextOperations ctxGroup = ldapTemplate.lookupContext(groupDn);
	        DirContextOperations ctxUser = ldapTemplate.lookupContext(userDn);
	        ctxGroup.addAttributeValue("member", ctxUser.getStringAttribute("distinguishedname"));
	        ldapTemplate.modifyAttributes(ctxGroup);
		}

	}
	
	public void addUser(String newUserName, String userpassword) {
		String SUN_JNDI_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory"; 
		
		String keystore = "/Library/Java/JavaVirtualMachines/jdk1.8.0_241.jdk/Contents/Home/jre/lib/security/cacerts";   
        System.setProperty("javax.net.ssl.trustStore", keystore);   
  
        Properties env = new Properties();   
  
        env.put(Context.INITIAL_CONTEXT_FACTORY, SUN_JNDI_PROVIDER);// java.naming.factory.initial   
        env.put(Context.PROVIDER_URL, "ldaps://WIN-NH8NPMU1DK2.lsblj.cn:636");// java.naming.provider.url   
        env.put(Context.SECURITY_AUTHENTICATION, "simple");// java.naming.security.authentication   
        env.put(Context.SECURITY_PRINCIPAL, "administrator@lsblj.cn");// java.naming.security.principal   
        env.put(Context.SECURITY_CREDENTIALS, "ls@BLJ1");// java.naming.security.credentials   
        env.put(Context.SECURITY_PROTOCOL, "ssl");   
  
        String userName = "CN="+newUserName+",OU=apppubusers,DC=lsblj,DC=cn";   
        String groupName = "CN=apppub,DC=lsblj,DC=cn";   
  
        
        
		try {
			LdapContext ctx = new InitialLdapContext(env, null);   
			  
	        // Create attributes to be associated with the new user   
	        Attributes attrs = new BasicAttributes(true);   
	  
	        // These are the mandatory attributes for a user object   
	        // Note that Win2K3 will automagically create a random   
	        // samAccountName if it is not present. (Win2K does not)   
	        attrs.put("objectClass", "user");   
	        attrs.put("sAMAccountName", newUserName);   
	        attrs.put("cn", newUserName);   
	  
	        // These are some optional (but useful) attributes   
	        attrs.put("sn", newUserName);   
	        attrs.put("displayName", newUserName);   
	        attrs.put("description", newUserName);   
	        attrs.put("userPrincipalName", newUserName+"@lsblj.cn");   
	  
	        // some useful constants from lmaccess.h   
	        int UF_ACCOUNTDISABLE = 0x0002;  //禁用账户 
	        int UF_PASSWD_NOTREQD = 0x0020;   //用户不能修改密码
	        int UF_PASSWD_CANT_CHANGE = 0x0040;   
	        int UF_NORMAL_ACCOUNT = 0x0200;   //正常用户
	        int UF_DONT_EXPIRE_PASSWD = 0x10000;   //密码永不过期
	        int UF_PASSWORD_EXPIRED = 0x800000;   //密码已经过期
	  
	        // Note that you need to create the user object before you can   
	        // set the password. Therefore as the user is created with no   
	        // password, user AccountControl must be set to the following   
	        // otherwise the Win2K3 password filter will return error 53   
	        // unwilling to perform.   
	  
	        attrs.put("userAccountControl", Integer.toString(UF_NORMAL_ACCOUNT   
	                + UF_PASSWD_NOTREQD + UF_PASSWORD_EXPIRED + UF_ACCOUNTDISABLE));   
	  
	        // Create the context   
	        
			Context result = ctx.createSubcontext(userName, attrs);   
	        System.out.println("Created disabled account for: " + userName);   
	  
	        ModificationItem[] mods = new ModificationItem[2];   
	  
	        // Replace the "unicdodePwd" attribute with a new value   
	        // Password must be both Unicode and a quoted string   
	        String newQuotedPassword = userpassword;   
	        byte[] newUnicodePassword;
	        
			newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
			 
			  
	        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,   
	                new BasicAttribute("unicodePwd", newUnicodePassword));   
	        mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,   
	                new BasicAttribute("userAccountControl", Integer   
	                        .toString(UF_NORMAL_ACCOUNT + UF_PASSWORD_EXPIRED)));   
	  
	        // Perform the update   
	        ctx.modifyAttributes(userName, mods);   
	        System.out.println("Set password & updated userccountControl");   
	        
	        ModificationItem member[] = new ModificationItem[1];   
            member[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE,   
                    new BasicAttribute("member", userName));   
  
            ctx.modifyAttributes(groupName, member);   
            System.out.println("Added user to group: " + groupName);  
            ctx.close();   
            System.out.println("Successfully created User: " + userName);   
		} catch (UnsupportedEncodingException | NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        // now add the user to a group.   
  
	}
}
