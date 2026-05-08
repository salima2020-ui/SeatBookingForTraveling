package com.example.demo.services;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Service;

@Service
public class PhoneUtil {
    public boolean isRealPhoneNum(String number){
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try{
            Phonenumber.PhoneNumber numberr = phoneUtil.parse(number, "ZZ" );
            return phoneUtil.isValidNumber(numberr);
        }catch (Exception e){
            return false;
        }
    }
}
