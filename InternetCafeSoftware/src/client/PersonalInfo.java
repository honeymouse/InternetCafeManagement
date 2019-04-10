package client;

public interface PersonalInfo {
	public void setName(String first, String last);
	public void setEmail(String email);
	public void setBirthdate(int birthdate);
	public void setTel(int tel);
	public void setGender(int gender);
	public String getName();
	public String getEmail();
	public int getBirthdate();
	public int getKoreanAge();
	public int getAge();
	public int getTel();
	public int getGender();
	
}
