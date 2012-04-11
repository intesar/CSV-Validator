package com.bia.ajax;

import com.bia.todo.domain.Item;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Intesar Mohammed
 */
public interface TodoDWRService {

    int login(String accessToken, int c, HttpServletRequest request);

    int isLoggedIn(HttpSession session);

    void logout(HttpSession session);

    String createList(HttpSession session);

    String shareWith(String email, HttpSession session);

    void unshareWith(String email, HttpSession session);

    void emailListToOwners(String message, HttpSession session);

    void addItem(String name, HttpSession session);

    void deleteItem(Integer itemId, HttpSession session);

    void changeItemStatus(Integer itemId, Boolean due, HttpSession session);

    List<Item> getList(HttpSession session);
}
