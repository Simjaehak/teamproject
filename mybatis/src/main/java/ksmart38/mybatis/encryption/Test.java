package ksmart38.mybatis.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Test {

	public static void main(String[] args) {
		StandardPBEStringEncryptor stringPBEConfig = new StandardPBEStringEncryptor();
	      stringPBEConfig.setPassword("ksmart38"); 			//대칭키(암호화키)
	      stringPBEConfig.setAlgorithm("PBEWithMD5AndDES"); 	//사용할 암호화 알고리즘	
	        
	        
	      //암호화문 평문 대상  
	      String jdbcUrl = "jdbc:log4jdbc:mysql://kangk0269.cafe24.com:3306/kangk0269?serverTimezone=UTC&characterEncoding=UTF8";
	      String userName = "kangk0269";
	      String passWord = "kang0269";
	      
	      System.out.println("평문(jdbcUrl):" + jdbcUrl);
	      System.out.println("암호문(jdbcUrl):" + stringPBEConfig.encrypt(jdbcUrl));
	      System.out.println("평문(userName):" + userName);
	      System.out.println("암호문(userName):" + stringPBEConfig.encrypt(userName));
	      System.out.println("평문(passWord):" + passWord);
	      System.out.println("암호문(passWord):" + stringPBEConfig.encrypt(passWord));
	      System.out.println("복호화(jdbcUrl):" + stringPBEConfig.decrypt("FiUQv3ldyEK6brIKJFHq3E8N87CPi36T34nf+3Ys+NJMga4wSJk24I/xpW5/02RkzL9XUM6Xzzofc9YRaYUVEUvtbSE9oZbNwzzaxTSatWh7LwKtJ5eGZyAi2niOJW68Fzwm62da/IJyv4vjIU77nw=="));
	      System.out.println("복호화(userName):" + stringPBEConfig.decrypt("1Lx7hMn2XK6W4QBcfUyWFUWzQfUP/U/n"));
	      System.out.println("복호화(passWord):" + stringPBEConfig.decrypt("kjGrp4LOdXKIu2D40kpMdFABrqZWLoyF"));
	      //암호화는 계속 바뀐다. 하지만 복호화에 넣어준 값은 어떻게된 복호화된다.
	}

}
