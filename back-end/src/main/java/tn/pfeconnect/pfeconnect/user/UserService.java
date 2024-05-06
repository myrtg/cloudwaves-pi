package tn.pfeconnect.pfeconnect.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.email.EmailService;

import java.security.Principal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailSender;
//    public User forgotpassword(String email) throws MessagingException {
//
//        var user = userRepository.findByEmail(email).get();
//        String randomPassword = PasswordGenerator.generateRandomPassword(5);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedrandonpassword = passwordEncoder.encode(randomPassword);
//        user.setPassword(encodedrandonpassword);
//        user.setForgotpassword(1);
//        userRepository.save(user);
//        emailSender.sendEmail(email, user.getFullName(), null, null, randomPassword, "Password Reset OTP");
//        return user;
//    }

    public User forgotPassword(String email) throws MessagingException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate OTP
        String otp = generateOTP(8); // Implement your OTP generation logic here
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedOtp = passwordEncoder.encode(otp);

        // Update user data
        user.setPassword(encodedOtp); // Set the OTP as the password temporarily
        user.setForgotpassword(1);
        userRepository.save(user);

        // Send email with OTP
        emailSender.sendOtp(email, user.getFullName(), otp);

        return user;
    }


    // Add method to generate OTP
    private String generateOTP(int length) {
        // Define the characters that can be used in the OTP
        String characters = "0123456789";
        // Create a StringBuilder to store the OTP
        StringBuilder otp = new StringBuilder();

        // Create an instance of Random
        Random random = new Random();

        // Generate the OTP by appending random characters from the characters string
        for (int i = 0; i < length; i++) {
            // Generate a random index to select a character from the characters string
            int index = random.nextInt(characters.length());
            // Append the selected character to the OTP
            otp.append(characters.charAt(index));
        }

        // Convert the StringBuilder to a String and return the OTP
        return otp.toString();
    }


    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodedPassword);
        user.setForgotpassword(0);
        userRepository.save(user);
    }

}