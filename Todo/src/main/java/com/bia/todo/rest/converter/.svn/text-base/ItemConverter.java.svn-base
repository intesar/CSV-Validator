package com.bia.todo.rest.converter;

import com.bia.todo.domain.Item;
import com.bia.todo.domain.Users;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author intesar
 */
@XmlRootElement()
public class ItemConverter {

    private Item entity;

    /** Creates a new instance of ItemConverter */
    public ItemConverter() {
        entity = new Item();
    }

    /**
     * Creates a new instance of ItemConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ItemConverter(Item entity, boolean isUriExtendable) {
        this.entity = entity;
    }

    /**
     * Creates a new instance of ItemConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ItemConverter(Item entity) {
        this(entity, false);
    }

    /**
     * Getter for id.
     *
     * @return value for id
     */
    @XmlElement
    public Integer getId() {
        return entity.getId();
    }

    /**
     * Setter for id.
     *
     * @param value the value to set
     */
    public void setId(Integer value) {
        entity.setId(value);
    }

    /**
     * Getter for name.
     *
     * @return value for name
     */
    @XmlElement
    public String getName() {
        return entity.getName();
    }

    /**
     * Setter for name.
     *
     * @param value the value to set
     */
    public void setName(String value) {
        entity.setName(value);
    }

    /**
     * Getter for tag.
     *
     * @return value for tag
     */
    @XmlElement
    public String getTag() {
        return entity.getTag();
    }

    /**
     * Setter for tag.
     *
     * @param value the value to set
     */
    public void setTag(String value) {
        entity.setTag(value);
    }

    /**
     * Getter for due.
     *
     * @return value for due
     */
    @XmlElement
    public Boolean getDue() {
        return entity.getDue();
    }

    /**
     * Setter for due.
     *
     * @param value the value to set
     */
    public void setDue(Boolean value) {
        entity.setDue(value);
    }

    public void setVersion(int version) {
        entity.setVersion(version);
    }

    @XmlElement
    public int getVersion() {
        return entity.getVersion();
    }

    /**
     * Getter for frequency.
     *
     * @return value for frequency
     */
    @XmlElement
    public Integer getFrequency() {
        return entity.getFrequency();
    }

    /**
     * Setter for frequency.
     *
     * @param value the value to set
     */
    public void setFrequency(Integer value) {
        entity.setFrequency(value);
    }

    /**
     * Getter for monthDay.
     *
     * @return value for monthDay
     */
    @XmlElement
    public Integer getMonthDay() {
        return entity.getMonthDay();
    }

    /**
     * Setter for monthDay.
     *
     * @param value the value to set
     */
    public void setMonthDay(Integer value) {
        entity.setMonthDay(value);
    }

    @XmlElement
    public String getModifiedDate() {
        Date dt = entity.getModifiedDate();
        Date now = new Date();

        if (dt == null) {
            dt = now;
        }

        int days = (int) (now.getTime() - dt.getTime()) / (1000 * 60 * 60 * 24);
        return days + " days";
    }

    public void setModifiedDate(String modifiedDate) {
        //this.modifiedDate = modifiedDate;
    }

    @XmlElement
    public String getModifiedUser() {
        Users user = entity.getModifiedUser();
        if (user == null) {
            return "";
        }
        
        String name = user.getName();
        
        if ( name == null || name.length() == 0 ) {
            return user.getEmail().split("@")[0];
        }
       
        String tokens[] = user.getName().split(" ");
        if ((tokens[0].contains(".") || tokens[0].length() < 2) && tokens.length > 1) {
            return tokens[1];
        }
        return tokens[0];
    }

    public void setModifiedUser(String modifiedUser) {
        //this.modifiedUser = modifiedUser;
    }

    /**
     * Returns the Item entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Item getEntity() {
        if (entity.getId() == null) {
            ItemConverter converter = null;//UriResolver.getInstance().resolve(ItemConverter.class);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemConverter)) {
            return false;
        }
        ItemConverter other = (ItemConverter) object;


        if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.entity.hashCode();
    }
}
