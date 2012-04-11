package com.bia.ajax;

import com.bia.todo.domain.Item;
import com.bia.todo.domain.Users;
import com.bia.todo.service.TodoService;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Intesar Mohammed
 */
@Component
public class TodoDWRServiceImpl implements TodoDWRService {

    @Autowired
    //@Qualifier(value="todoService")
    private TodoService todoService;
    private static final Logger log = Logger.getLogger(TodoDWRServiceImpl.class);
    private static final String EMAIL = "email";

    @Override
    public int login(String accessToken, int c, HttpServletRequest request) {
        int code = c == -1 ? -2 : -1;
        try {
            //String clientIP = request.getRemoteAddr();

            Facebook facebook = (Facebook) new FacebookTemplate(accessToken);

            FacebookProfile profile = facebook.userOperations().getUserProfile();
            String username = profile.getEmail();

            Users user = null;

            try {
                user = todoService.findUser(username);
            } catch (RuntimeException re) {
            }

            if (user == null) {
                String email = profile.getEmail();
                String name = profile.getName();
                String gender = profile.getGender();
                String fbId = profile.getId();
                //String dob = profile.getBirthday();
                String fn = profile.getFirstName();
                String ln = profile.getLastName();
                String mi = profile.getMiddleName();
                String fbLink = profile.getLink();
                Locale locale = profile.getLocale();
                Integer timezone = profile.getTimezone();
                String city = profile.getLocation() != null ? profile.getLocation().getName() : "";

                todoService.saveUserProfile(email, System.nanoTime()+"", fn, ln, mi, name, gender, city, timezone, fbId, fbLink);
            }
            // save info to db

            HttpSession session = request.getSession(true);
            session.setAttribute(EMAIL, username);

            //this.usersService.fbAccessToken(accessToken, clientIP);
            code = 1;
        } catch (RuntimeException ex) {
            log.error(ex);
            throw new RuntimeException("Error, please try again!");
        }
        return code;
    }

    @Override
    public int isLoggedIn(HttpSession session) {
        int code = 0;
        try {
            String email_ = getEmailFromSession(session);
            if (email_ != null && email_.length() > 0) {
                code = 1;
            }
        } catch (RuntimeException re) {
            code = -1;
        }
        return code;
    }

    @Override
    public void logout(HttpSession session) {
        // invalida session 
        session.invalidate();
    }

    @Override
    public String createList (HttpSession session) {
        String email_ = getEmailFromSession(session);
        return todoService.createList(email_);
    }
    
    @Override
    public String shareWith(String accessCode, HttpSession session) {
        String email_ = getEmailFromSession(session);
        
        if (accessCode != null && accessCode.length() > 0) {
            todoService.joinList(accessCode, email_); 
        } else {
            return todoService.myAccessCode(email_);
        }
        return accessCode;
    }

    @Override
    public void unshareWith(String emailTo, HttpSession session) {
        String email_ = getEmailFromSession(session);
        todoService.unshareWith(email_, emailTo);
    }

    @Override
    public void emailListToOwners(String message, HttpSession session) {
        String email_ = getEmailFromSession(session);
        todoService.emailListToOwners(message, email_);
    }

    @Override
    public void addItem(String name, HttpSession session) {
        String email_ = getEmailFromSession(session);
        todoService.addItem(name, email_);
    }

    @Override
    public void deleteItem(Integer itemId, HttpSession session) {
        String email_ = getEmailFromSession(session);
        todoService.deleteItem(itemId, email_);
    }

    @Override
    public void changeItemStatus(Integer itemId, Boolean due, HttpSession session) {
        String email_ = getEmailFromSession(session);
        todoService.changeItemStatus(itemId, due, email_);
    }

    @Override
    public List<Item> getList(HttpSession session) {
        String email_ = getEmailFromSession(session);
        return todoService.getList(email_);
    }
    
    private String getEmailFromSession(HttpSession session) {
        String email_ = null;
        try {
            email_ = (String) session.getAttribute(EMAIL);
        } catch (RuntimeException re){
            
        }
        if ( email_ == null || email_.length() == 0) {
            throw new RuntimeException("Invalid Session, Please login again!");
        }
        
        return email_;
    }
}
