package cn.movinginfo.tztf.releasechannel;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
    String name;
    String password;
     
    public MyAuthenticator(String name,String password){
        this.name = name;
        this.password = password;
         
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(name,password);
    }
}