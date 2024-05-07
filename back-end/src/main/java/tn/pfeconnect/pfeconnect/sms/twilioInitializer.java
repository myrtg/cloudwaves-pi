package tn.pfeconnect.pfeconnect.sms;



import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class twilioInitializer {


    public static final String ACCOUNT_SID = "AC7887a3935b4f726029d7f5ea66ff4d6f";
    public static final String AUTH_TOKEN = "1e9f1275a145831be7033addf2b4845a"; // Replace with your actual Auth Token


    public twilioInitializer() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
}