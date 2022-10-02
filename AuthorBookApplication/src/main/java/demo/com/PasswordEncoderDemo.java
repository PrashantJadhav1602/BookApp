package demo.com;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderDemo {

	public static void main(String[] args) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedStr = encoder.encode("demo@123");
		System.out.println(encodedStr);
	}
	
}
