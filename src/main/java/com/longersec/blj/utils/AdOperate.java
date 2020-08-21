package com.longersec.blj.utils;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;

import com.longersec.blj.domain.ConfigLdapAd;

import com.longersec.blj.domain.User;

import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

@Component
public class AdOperate {

	/** connect to ldap */
	LdapTemplate ldapTemplate = null;

//	public AdOperate(String ldapURL, String adminName, String adminPassword, String baseDn) {
//
//		LdapContextSource cs = new LdapContextSource();
//        cs.setCacheEnvironmentProperties(false);
//        cs.setUrl(ldapURL);
//        cs.setBase(baseDn);
//        cs.setUserDn(adminName);
//        cs.setPassword(adminPassword);
//        cs.afterPropertiesSet();
//        ldapTemplate = new LdapTemplate(cs);
//        ldapTemplate.setIgnorePartialResultException(true);
//	}

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
        env.put(Context.PROVIDER_URL, "ldap://WIN-NH8NPMU1DK2.lsblj.cn:636");// java.naming.provider.url
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

	//连接测试
	public static boolean checkConnect(ConfigLdapAd configLdapAd){

		Hashtable<String, String> env = new Hashtable<String, String>();


		//判断字符串格式
		String adminName = "";
		String isADorLDAP = configLdapAd.getAdministrator();
		if(isADorLDAP.indexOf("DC=")!=-1){
			adminName = configLdapAd.getAdministrator();//username@domain LDAP认证格式
		}else{
			adminName = configLdapAd.getAdministrator()+"@"+configLdapAd.getDomain();
		}

//		String adminName = "administrator@lsblj.cn";//username@domain AD认证格式
//		String adminName = "CN=Administrator,CN=Users,DC=lsblj,DC=cn";//username@domain LDAP认证格式
//		String adminName = configLdapAd.getAdministrator()+"@"+configLdapAd.getDomain();//username@domain AD认证格式
//		String adminName = configLdapAd.getAdministrator();//username@domain LDAP认证格式


//		String adminPassword = "ls@BLJ1";//password
		String adminPassword = configLdapAd.getPassword();//password
//		String ldapURL = "ldap://39.96.69.249:389";//ip:port
		String ldapURL = "ldap://"+configLdapAd.getIp()+":"+configLdapAd.getPort();//ip:port

		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");//"none","simple","strong"
		env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_CREDENTIALS, adminPassword);
		env.put(Context.PROVIDER_URL, ldapURL);
		try {
			DirContext ctx = new InitialDirContext(env);
			System.out.println("认证成功");
			ctx.close();
			return true;
		} catch (Exception e) {
			System.out.println("认证失败");
			return false;
		}
	}

	//获取结果查询集
	public ArrayList<User> searchUser(ConfigLdapAd configLdapAd) {

		ArrayList<User> userArrayList = new ArrayList<>();

		String returnValue = configLdapAd.getUsername();

		Properties env = new Properties();

		//判断字符串格式
		String adminName = "";
		String isADorLDAP = configLdapAd.getAdministrator();
		if(isADorLDAP.indexOf("DC=")!=-1){
			adminName = configLdapAd.getAdministrator();//username@domain LDAP认证格式
		}else{
			adminName = configLdapAd.getAdministrator()+"@"+configLdapAd.getDomain();
		}

//		String adminName = "administrator@lsblj.cn";//username@domain AD认证格式
//		String adminName = "CN=Administrator,CN=Users,DC=lsblj,DC=cn";//username@domain LDAP认证格式
//		String adminName = configLdapAd.getAdministrator()+"@"+configLdapAd.getDomain();//username@domain AD认证格式
//		String adminName = configLdapAd.getAdministrator();//username@domain LDAP认证格式

//		String adminPassword = "ls@BLJ1";//password
		String adminPassword = configLdapAd.getPassword();//password
//		String ldapURL = "ldap://39.96.69.249:389";//ip:port
		String ldapURL = "ldap://"+configLdapAd.getIp()+":"+configLdapAd.getPort();//ip:port
		String searchBase = configLdapAd.getBasedn();
		String searchFilter = "(&(objectCategory=person)(objectClass=user)(name=*))";
		String returnedAtts[] = {"distinguishedName","cn","sn","uid"};//自定义返回集

		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");//"none","simple","strong"
		env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_CREDENTIALS, adminPassword);
		env.put(Context.PROVIDER_URL, ldapURL);
		try {
			LdapContext ctx = new InitialLdapContext(env, null);
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			searchCtls.setReturningAttributes(returnedAtts);
			NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter,searchCtls);
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
				User user = new User();
				if (Attrs != null) {

					String distinguishedname = Attrs.get("distinguishedname").toString();
					String distinguishedName = distinguishedname.substring(0, distinguishedname.indexOf(":"));
					String newDistinguishedName = distinguishedname.substring(distinguishedName.length()+2,distinguishedname.length());


					if (Attrs.get("cn")!=null){
						if(returnValue.equals("CN")||returnValue.equals("")) {
						String cn = Attrs.get("cn").toString();
						String CN = cn.substring(0, cn.indexOf(":"));
						String newCN = cn.substring(CN.length() + 2, cn.length());
							user.setUsername(newCN);
							user.setRealname(newCN);
							user.setRole_id(1);
							user.setLdap_dn(newDistinguishedName);
							userArrayList.add(user);
						}
					}
					if (Attrs.get("sn")!=null){
						if(returnValue.equals("SN")){
						String sn = Attrs.get("sn").toString();
						String SN = sn.substring(0, sn.indexOf(":"));
						String newSN = sn.substring(SN.length()+2,sn.length());
							user.setUsername(newSN);
							user.setRealname(newSN);
							user.setRole_id(1);
							user.setLdap_dn(newDistinguishedName);
							userArrayList.add(user);
						}
					}
					if (Attrs.get("uid")!=null){
						if(returnValue.equals("UID")) {
						String uid = Attrs.get("uid").toString();
						String UID = uid.substring(0, uid.indexOf(":"));
						String newUID = uid.substring(UID.length()+2,uid.length());
							user.setUsername(newUID);
							user.setRealname(newUID);
							user.setRole_id(1);
							user.setLdap_dn(newDistinguishedName);
							userArrayList.add(user);
						}
					}
				}//if
			}
		ctx.close();
		}catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Problem searching directory: " + e);
		}
		return userArrayList;
	}

	//对获取的DN进行处理
	public List<String> dnOperate(ConfigLdapAd configLdapAd,String dnStr){

		List<String> baseDList = new ArrayList();
		List<String> dnList = new ArrayList();

		//获取"DC="字符串的出现次数
		int strlen=dnStr.length();
		String afterstr=dnStr.replace("DC=","dc");
		int afterlen=afterstr.length();
		int endlen = strlen-afterlen;

		//排除无关干扰字符串
		String dnStr1 = dnStr.replace("DC=","");
		String dnStr2 = dnStr1.replace("CN=","");
		String dnStr3 = dnStr2.replace("OU=","");

		//按照","分割字符串
		String[] newDnStr = dnStr3.split(",");

		//根据"DC="出现次数获取所有"DC="字段后的字符
		for(int i=newDnStr.length-1;i>newDnStr.length-endlen-1;i--){
			baseDList.add(newDnStr[i]);
		}
		//将处理后的字符串拼接成域名格式 例如：“lsbjlj.cn”
		String Str = "";
		for(int i = baseDList.size()-1;i>=0;i--){
			String Str1 = baseDList.get(i);
			Str = Str + Str1+".";
		}
		Str = Str.substring(0,Str.length()-1);

		//将拼接好的域名格式部门名，放入dnList数组第一个字段
		dnList.add(Str);

		//获取次级的部门列表
		for(int i=newDnStr.length-3;i>0;i--){
			dnList.add(newDnStr[i]);
		}

		return dnList;

	}


}

