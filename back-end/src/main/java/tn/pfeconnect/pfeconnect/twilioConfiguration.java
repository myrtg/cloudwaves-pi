package tn.pfeconnect.pfeconnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class twilioConfiguration {
    private String accountSid = "AC7887a3935b4f726029d7f5ea66ff4d6f";
    private String authToken = "f7be4e19fb960bd37adf6e027d05f82f";
    private String trialNumber = "+12512501092";

}