/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/domain/Entity.e.vm.java
 */
package com.bia.todo.domain;

import com.google.common.base.Objects;

import com.bia.todo.domain.PersistableHashBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Persistable;
import org.hibernate.annotations.Cache;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;


@Entity
@Cache(usage = NONSTRICT_READ_WRITE)
@Table(name = "lists")
public class Lists implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(Lists.class);

    // Raw attributes
    private Integer id; // pk
    private String name;

    // ---------------------------
    // Constructors
    // ---------------------------

    public Lists() {
    }

    public Lists(Integer primaryKey) {
        setId(primaryKey);
    }

    @Override
    @Transient
    @XmlTransient
    public boolean isNew() {
        return getId() == null;
    }

    // -------------------------------
    // Getter & Setter
    // -------------------------------

    // -- [id] ------------------------

    @Column(nullable = false, unique = true, precision = 10)
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // -- [name] ------------------------

    @Size(max = 45)
    @Column(length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the default values.
     */
    public void initDefaultValues() {
    }

    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Lists && hashCode() == other.hashCode());
    }

    //private PersistableHashBuilder persistableHashBuilder = new PersistableHashBuilder();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    /**
     * Construct a readable string representation for this {@link Lists} instance.
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", getId()) //
                .add("name", getName()) //
                .toString();
    }
}