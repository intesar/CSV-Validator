/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/domain/Entity_.e.vm.java
 */
package com.bia.todo.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Users.class)
public abstract class Users_ {

    // Raw attributes
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> email;

    // Technical attributes for query by example
    public static volatile SingularAttribute<Users, Integer> listId;

    // Many to one
    public static volatile SingularAttribute<Users, Lists> list;
}