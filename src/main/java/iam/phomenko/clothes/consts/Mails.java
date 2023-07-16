package iam.phomenko.clothes.consts;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Mails {
    @Bean
    public Mails get() {
        return new Mails();
    }

    private final Map<String, String> mails;

    private Mails() {
        this.mails = new HashMap<>();
        mails.put("Activation", "Hi, %s, to activate your account follow link below\t%s");
        mails.put("Activated", "Hi, %s, your account was activated.");
    }

    public String getMailTemplateByName(String name) {
        return mails.get(name);
    }
}
