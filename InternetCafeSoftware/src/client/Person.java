package client;

public class Person implements PersonalInfo {

    private String name, email;
    private int birthdate, tel, gender;
    private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

    public void setName(String fullname) {
        this.name = fullname;
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
        return "이름: " + name
                + "\n이메일 주소: " + email
                + "\n나이: " + getKoreanAge() + "세 (만" + getAge() + "세"
                + "\n전화번호: " + tel
                + "\n성별: " + ((gender == 1) ? "남" : "여");
    }
}