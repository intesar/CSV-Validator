package com.bia.todo.rest.converter;

import com.bia.todo.domain.Item;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author intesar
 */
@XmlRootElement()
public class ItemsConverter {
    private Collection<Item> entities;
    private Collection<ItemConverter> items;

    /** Creates a new instance of ItemsConverter */
    public ItemsConverter() {
    }

    /**
     * Creates a new instance of ItemsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ItemsConverter(Collection<Item> entities) {
        this.entities = entities;
    }

    /**
     * Returns a collection of ItemConverter.
     *
     * @return a collection of ItemConverter
     */
    @XmlElement
    public Collection<ItemConverter> getItem() {
        if (items == null) {
            items = new ArrayList<ItemConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Item entity : entities) {
                items.add(new ItemConverter(entity, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ItemConverter.
     *
     * @param a collection of ItemConverter to set
     */
    public void setItem(Collection<ItemConverter> items) {
        this.items = items;
    }

   

    /**
     * Returns a collection Item entities.
     *
     * @return a collection of Item entities
     */
    @XmlTransient
    public Collection<Item> getEntities() {
        entities = new ArrayList<Item>();
        if (items != null) {
            for (ItemConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemsConverter)) {
            return false;
        }
        ItemsConverter other = (ItemsConverter) object;
        
        
        if (this.items.size() != other.items.size()) {
            return false;
        }
        Set<ItemConverter> itemSet = new HashSet<ItemConverter>(this.items);
        if (!itemSet.containsAll(other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (ItemConverter item : this.items) {
            hash = 37 * hash + item.hashCode();
        }
        return hash;
    }
}
