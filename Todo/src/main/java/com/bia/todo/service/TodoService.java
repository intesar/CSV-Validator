package com.bia.todo.service;

import com.bia.todo.domain.Item;
import com.bia.todo.domain.Users;
import java.util.List;

/**
 *
 * @author Intesar Mohammed
 */
public interface TodoService {

    Users findUser(String email);

    void saveUserProfile(String email, String accessCode, String fn, String ln, String mi,
            String name, String gender, String city, Integer tz, String fbId, String fbLink);

    String createList(String email);

    String myAccessCode(String email);

    void joinList(String from, String email);

    void unshareWith(String from, String email);

    void emailListToOwners(String message, String email);

    List<Item> addItem(String name, String email);

    List<Item> deleteItem(Integer itemId, String email);

    List<Item> changeItemStatus(Integer itemId, Boolean due, String email);

    List<Item> getList(String email);

    public void saveUserProfile(String email, String name, String accessCode);
}
