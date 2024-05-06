package tn.pfeconnect.pfeconnect.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SmsRequest {
    private final String tel;
    private final String smsmessage;


    @Override
    public String toString() {
        return "SmsRequest{" +
                "tel='" + tel + '\'' +
                ", smsmessage='" + smsmessage + '\'' +
                '}';
    }
}