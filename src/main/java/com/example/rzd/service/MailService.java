package com.example.rzd.service;

import com.example.rzd.entity.MailType;
import com.example.rzd.entity.User;

import java.util.Properties;

public interface MailService {
    void sendMail(User user, MailType type, Properties properties);
}
