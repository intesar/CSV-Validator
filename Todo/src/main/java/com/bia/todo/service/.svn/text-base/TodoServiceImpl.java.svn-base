package com.bia.todo.service;

import com.bia.todo.domain.Item;
import com.bia.todo.domain.Lists;
import com.bia.todo.domain.Product;
import com.bia.todo.domain.Users;
import com.bia.todo.repository.ItemRepository;
import com.bia.todo.repository.ListsRepository;
import com.bia.todo.repository.ProductRepository;
import com.bia.todo.repository.UsersRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Intesar Mohammed
 */
@Service(value = "todoService")
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {
    
    private static final Logger log = Logger.getLogger(TodoServiceImpl.class);

    @Autowired
    private ListsRepository listsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public Users findUser(String email) {
        Users user = usersRepository.findByEmail(email);
        return user;
    }

    @Override
    @Transactional
    public void saveUserProfile(String email, String name, String accessCode) {
        saveUserProfile(email, accessCode, null, null, null, name, null, null, Integer.SIZE, null, null);
    }

    @Override
    @Transactional
    public void saveUserProfile(String email, String accessCode, String fn, String ln, String mi,
            String name, String gender, String city, Integer tz, String fbId, String fbLink) {

        Users user = new Users();
        user.setEmail(email);
        user.setAccessCode(accessCode);
        user.setFn(fn);
        user.setLn(ln);
        user.setMi(mi);
        user.setName(name);
        user.setGender(gender);
        user.setCity(city);
        user.setTimezone(tz);
        user.setFbId(fbId);
        user.setFbLink(fbLink);

        usersRepository.save(user);

        // TODO - Do this stuff in a seperate EmailUtil object
        // email user with welcome message
        // TODO -- use StringBuilder and no + operator for concating strings
        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        // TODO -- Proper subject & body
        subject.append("Thanks for joining Todo");

        /*Dear user
         * Thanks for joining Todo. 
         * 
         * Todo helps you create a grocery list or other tasks and share it with your family
         * instantly. You can also create a list of all bills due every month.  
         * 
         * Thanks again for joining!
         * Todo Team
         * 
         * Now you can create a grocery list and 
         * share it with your family.  You can also create a list of bills that are
         * due periodically every month.  
         * 
         * 
         * 
         */

        body.append("Dear ").append(name).append(", <br><br>");
        body.append("Don't miss a thing while shopping nor get late with your payments.");
        body.append("With Todo you can easily manage and share your tasks and everyone will be notified instantly.");
        body.append("<br><br>");
        body.append("Thanks again!<br>");
        body.append("Todo team");

        emailService.sendEmail(email, subject.toString(), body.toString());

        createList(email);

    }

    @Override
    @Transactional
    public String createList(String email) {

        Random r = new Random(3);
        // mdshannan@gmail.com --> mdshannan + random
        String accessCode = email.split("@")[0];

        List<char[]> chars = Arrays.asList(accessCode.toCharArray());
        Collections.shuffle(chars);

        Lists lists = new Lists();
        lists.setName(accessCode);
        listsRepository.save(lists);


        Users user = usersRepository.findByEmail(email);
        user.setList(lists);
        usersRepository.save(user);

        lists.setName(accessCode + user.getId());
        listsRepository.save(lists);

        // also send access code to join others to his list        
        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        // TODO -- Proper subject & body

        subject.append("Share Todo list with me");
        body.append("Hi ").append(user.getName()).append(",<br><br>");
        body.append("I created ").append(lists.getName()).append(" list and we can easily maintain our shopping list and bill payments together.").append("The best thing is it gets updated immediately.<br>");
        emailService.sendEmail(email, subject.toString(), body.toString());

        return accessCode;
    }

    @Override
    public String myAccessCode(String email) {
        Users user = usersRepository.findByEmail(email);

        Lists lists = user.getList();
        String accessCode = lists != null ? lists.getName() : "";

        return accessCode;
    }

    @Override
    @Transactional
    public void joinList(String accessCode, String email) {
        // load both users

        //Users user1 = usersRepository.findByEmail(from);
        Lists lists = listsRepository.findByName(accessCode);

        Users user = usersRepository.findByEmail(email);
        user.setList(lists);
        usersRepository.save(user);

        List<Users> users = usersRepository.findByListId(lists.getId());

        String[] emails = new String[users.size()];
        int count = 0;
        for (Users u : users) {
            emails[count++] = u.getEmail();
        }

        // email user for the confirmation
        // TODO - How can we determine the name of the user who accepted to join, is it user?
        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        // TODO -- Proper subject & body
        subject.append(user.getName()).append(" is sharing your list!");
        body.append("Congratulations, ").append(user.getName()).append(" for joining list # ").append(accessCode);
        emailService.sendEmail(emails, subject.toString(), body.toString());
    }

    @Override
    @Transactional
    public void unshareWith(String from, String email) {
        // load both users
        Users user1 = usersRepository.findByEmail(from);
        Users user2 = usersRepository.findByEmail(email);

        // create a new list with duplicate items for the second user
        Lists lists = new Lists();
        lists.setName(from);
        listsRepository.save(lists);

        List<Item> items = getList(email);
        for (Item item : items) {
            Item i = item.clone();
            i.setList(lists);
            itemRepository.save(i);
        }
        user1.setList(lists);
        // update users with the email
    }

    @Override
    public void emailListToOwners(String message, String email) {
        // find user by email
        Users user = usersRepository.findByEmail(email);
        // find all other users with same listId
        List<Users> users = usersRepository.findByListId(user.getListId());

        String[] emails = new String[users.size()];
        int count = 0;
        for (Users u : users) {
            emails[count++] = u.getEmail();
        }

        // load due items from the list
        List<Item> items = itemRepository.findByListIdAndDue(user.getListId(), true);
        // format email and send to all users
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.getName()).append("\n");
        }
        // send email with all due items to all owners        
        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        // TODO -- Proper subject & body
        subject.append("Your Todo list has been updated");
        body.append(message).append(" -- ").append(user.getName()).append("<br><br>");
        body.append("Your list has been updated with new items: <br><br>").append(items);
        /*TODO -- how to present updated items with an existing list?  
         * Make it bold or checkmark?
         */
        body.append("<br><br>");
        body.append("Thanks,<br>");
        body.append("Todo team");
        emailService.sendEmail(emails, subject.toString(), body.toString());
    }

    @Override
    @Transactional
    public List<Item> addItem(String name, String email) {
        // add item and return the complete list
        Users user = usersRepository.findByEmail(email);
        for (String n : name.split(",")) {
            Item item = new Item(n, "default", Boolean.TRUE, 1, null, null);
            item.setList(user.getList());
            attachTag(item);
            itemRepository.save(item);
        }
        return getList(email);
    }

    @Override
    @Transactional
    public List<Item> deleteItem(Integer itemId, String email) {
        // delete item and return the complete list
        Users user = usersRepository.findByEmail(email);

        Item item = itemRepository.findOne(itemId);

        if (user.getListId().equals(item.getListId())) {
            itemRepository.delete(item);
        } else {
            throw new RuntimeException(" Invalid access! ");
        }

        return getList(email);
    }

    @Override
    @Transactional
    public List<Item> changeItemStatus(Integer itemId, Boolean due, String email) {
        // update item and return complete list

        Users user = usersRepository.findByEmail(email);

        Item item = itemRepository.findOne(itemId);

        if (user.getListId().equals(item.getListId())) {
            item.setDue(due);
            if (item.getTag() == null || item.getTag().length() == 0 || item.getTag().equals("default")) {
                attachTag(item);
            }
            // set action user & date
            item.setModifiedUser(user);
            item.setModifiedDate(new Date());
            
            itemRepository.save(item);
        } else {
            throw new RuntimeException(" Invalid access! ");
        }

        return getList(email);
    }

    @Override
    public List<Item> getList(String email) {
        // return list
        return itemRepository.findByUserListId(email);
    }

    private void attachTag(Item item) {
        try {
            Product product = this.productRepository.findByNameLower(item.getName().toLowerCase());
            if (product != null) {
                item.setTag(product.getCategory().toLowerCase());
            }
        } catch (RuntimeException re) {
            log.warn(re);
        }
    }
}
