package tn.pfeconnect.pfeconnect.sms;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class twilioService {


    private final twilioSmsSender twilioSmsSender;

    public void sendsms(SmsRequest smsRequest) {
        twilioSmsSender.sendsms(smsRequest);
    }
}