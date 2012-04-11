/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.bia.todo.domain.Item;
import com.bia.todo.domain.Users;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml"})
@Transactional
public class TodoServiceImplTest {
    
    @Autowired
    @Qualifier(value="todoService")
    private TodoService instance;
    
    public TodoServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findUser method, of class TodoServiceImpl.
     */
    @Test
    public void testFindUser1() {
        System.out.println("findUser");
        String email = "";
        Users expResult = null;
        Users result = instance.findUser(email);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFindUser2() {
        System.out.println("findUser");
        String email = null;
        Users expResult = null;
        Users result = instance.findUser(email);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFindUser3() {
        System.out.println("findUser");
        instance.saveUserProfile("xxx@xx.com", null,"fn", "ln", "mi", "name", "male", "Santa Clara", -3, "123", "facebook.com");
        String email = "xxx@xx.com";
        Users result = instance.findUser(email);
        assertNotNull(result);
    }
    

    /**
     * Test of saveUserProfile method, of class TodoServiceImpl.
     */
    //@Test
    public void testSaveUserProfile() {
        System.out.println("saveUserProfile");
        instance.saveUserProfile("xxx@xx.com", null,"fn", "ln", "mi", "name", "male", "Santa Clara", -3, "123", "facebook.com");
    }

    /**
     * Test of createList method, of class TodoServiceImpl.
     */
    //@Test
    public void testCreateList() {
        System.out.println("createList");
        String email = "xxx@xx.com";
        instance.createList(email);
    }

    /**
     * Test of joinList method, of class TodoServiceImpl.
     */
    //@Test
    public void testJoinList() {
        System.out.println("joinList");
        String accessCode = "xxx@xx.com";
        String email = "xxx@xx.com";
        instance.joinList(accessCode, email);
    }

    /**
     * Test of unshareWith method, of class TodoServiceImpl.
     */
    //@Test
    public void testUnshareWith() {
        System.out.println("unshareWith");
        String from = "";
        String email = "";
        instance.unshareWith(from, email);
    }

    /**
     * Test of emailListToOwners method, of class TodoServiceImpl.
     */
    //@Test
    public void testEmailListToOwners() {
        System.out.println("emailListToOwners");
        String email = "xxx@xx.com";
        instance.emailListToOwners("test", email);
    }

    /**
     * Test of addItem method, of class TodoServiceImpl.
     */
    //@Test
    public void testAddItem() {
        System.out.println("addItem");
        Integer id = null;
        Item item = new Item("milk", "default", Boolean.TRUE, 1, 0, id);
        
        String email = "xxx@xx.com";
        List result = instance.addItem("milk", email);
        assertTrue(result.size() > 0 );
    }

    /**
     * Test of deleteItem method, of class TodoServiceImpl.
     */
    //@Test
    public void testDeleteItem() {
        System.out.println("deleteItem");
        Integer id = null;
        Item item = new Item("milk", "default", Boolean.TRUE, 1, 0, id);
        
        String email = "xxx@xx.com";
        
        List result = instance.deleteItem(null, email);
        assertTrue(result.size() > 0 );
    }

    /**
     * Test of changeItemStatus method, of class TodoServiceImpl.
     */
    //@Test
    public void testChangeItemStatus() {
        System.out.println("changeItemStatus");
        Integer itemId = null;
        Boolean due = null;
        String email = "";
        List expResult = null;
        List result = instance.changeItemStatus(itemId, due, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getList method, of class TodoServiceImpl.
     */
    //@Test
    public void testGetList() {
        System.out.println("getList");
        String email = "";
        List expResult = null;
        List result = instance.getList(email);
        assertEquals(expResult, result);
    }
}
