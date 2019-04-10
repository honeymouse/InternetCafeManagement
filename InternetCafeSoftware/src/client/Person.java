package client;

public class Person implements PersonalInfo {

	private String name, email;
	private int birthdate, tel, gender;
	private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

	public void setName(String first, String last) {
		if (Character.UnicodeScript.of(first.charAt(0)) == Character.UnicodeScript.HANGUL)
			this.name = last + first;
		else
			this.name = first + " " + last;
	}
	
	public static boolean containsHanScript(String s) {
	    return s.codePoints().anyMatch(
	            codepoint ->
	            Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HANGUL);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getBirthdate() {
		return birthdate;
	}

	public int getKoreanAge() {
		return year - (birthdate / 10000) + 1;
	}

	public int getAge() {
		return year - (birthdate / 10000);
	}

	public int getTel() {
		return tel;
	}

	public int getGender() {
		return gender;
	}
	
	public String toString() {
		return "�̸�: " + name
				+ "\n�̸��� �ּ�: " + email
				+ "\n����: " + getKoreanAge() + "�� (��" + getAge() + "��" 
				+ "\n��ȭ��ȣ: " + tel
				+ "\n����: " + ((gender == 1) ? "��" : "��");
	}
}