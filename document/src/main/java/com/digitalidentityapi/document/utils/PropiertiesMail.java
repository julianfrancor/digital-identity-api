package com.digitalidentityapi.document.utils;

import java.util.Properties;

public class PropiertiesMail {
    public static Properties getPropierties() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", Constants.HOST);
        props.put("mail.smtp.port", Constants.PORT);
        return props;
    }
}
