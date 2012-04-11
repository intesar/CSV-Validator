/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.todo.service;

import com.bia.ajax.TodoDWRService;
import com.bia.todo.domain.Users;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Intesar Mohammed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml"})
@Transactional
public class TodoDWRServiceImplTest {
    
    @Autowired
    private TodoDWRService instance;
    
    public TodoDWRServiceImplTest() {
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

    @Test
    public void testLoadInstance() {
       instance.toString();
    }
    
   
    
}
