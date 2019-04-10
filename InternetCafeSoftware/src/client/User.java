package client;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class User extends Person {
	private boolean guest;
	private String username;
	private byte[] encryptedPassword;
	private byte[] salt;
	private boolean loggedin;
	
	public User(boolean guest, String username) {
		this.guest = guest;
		if (guest) {
			this.setName("Guest", "User"); // 비회원/Guest User
		}
	}
	
	public User(
			String username, String password,
			String first, String last,
			int birthYear, int birthMonth, int birthDay,
			int gender, int tel,
			String email
			) {
		this.username = username;
		this.setName(first, last);
		this.setBirthdate(birthYear*10000 + birthMonth*100 + birthDay);
		this.setGender(gender);
		this.setEmail(email);
		this.setTel(tel);
		
		try {
			generateSalt();
			this.encryptedPassword = getEncryptedPassword(password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	/**
	 * login 기능
	 * 
	 * HashMap<String, User> userlist를 통해 뉴저네임을 찾아서
	 * 만약에 있으면 userlist.get("유저네임").login("패스워드")를 한다
	 * 
	 * @param password 암호(비밀번호)
	 * @return 로그인 성공 결과
	 */
	public boolean login(String password) {
		try {
			if (authenticate(password)) {
				loggedin = true;
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}
	
	public boolean authenticate(String attemptedPassword)
			   throws NoSuchAlgorithmException, InvalidKeySpecException {
		
			  byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword);
			  
			  return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
		}
	
	public byte[] getEncryptedPassword(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160;
		int iterations = 20000; // 더 크면 클수록 안전함 (하지만 속도가 느려짐)
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		return f.generateSecret(spec).getEncoded();
	}
	
	public void generateSalt() throws NoSuchAlgorithmException {
		  SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		  byte[] salt = new byte[8];
		  random.nextBytes(salt);
		  this.salt = salt;
	}
}
