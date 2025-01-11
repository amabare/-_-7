package AmabareWorkspace;

public class DriHashPassword {
    public static void main(String[] args) {
        String password = "password";
        String hashedPassword = HashPassword.HashString(password);
        System.out.println("ハッシュ化されたパスワード: " + hashedPassword);
    }
}
