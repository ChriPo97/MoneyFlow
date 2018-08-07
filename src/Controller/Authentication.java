/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


/**
 *
 * @author Christoph
 */
public class Authentication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("cfg/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        token.setRememberMe(true);

        try {

            currentUser.login(token);
            if(currentUser.isAuthenticated()) {
                System.out.println("Angemeldet");
            };
            if(currentUser.hasRole("Administrator")) {
                System.out.println("Sie sind admin");
            };
            if(currentUser.hasRole("User")) {
                System.out.println("Sie sind user");
            };

        } catch (UnknownAccountException uae) {

        } catch (IncorrectCredentialsException ice) {

        } catch (LockedAccountException lae) {

        } catch (AuthenticationException aex) {

        }

    }
}
