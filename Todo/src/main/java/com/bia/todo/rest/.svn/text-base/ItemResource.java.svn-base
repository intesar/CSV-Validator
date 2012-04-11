package com.bia.todo.rest;

import com.bia.todo.domain.Item;
import com.bia.todo.domain.Users;
import com.bia.todo.repository.ProductRepository;
import com.bia.todo.rest.converter.ItemConverter;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import com.bia.todo.rest.converter.ItemsConverter;
import com.bia.todo.service.TodoService;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.Path;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author intesar
 */
@Component
@Path("/item")
public class ItemResource {

    @Autowired
    protected TodoService todoService;
    @Autowired
    protected ProductRepository productRepository;

    /** Creates a new instance of ItemResource */
    public ItemResource() {
    }

    /**
     * Get method for retrieving a collection of Item instance in XML format.
     * http url http://localhost:8080/rest/item?email=mdshannan@gmail.com
     * 
     * @return an instance of ItemsConverter
     */
    @GET
    @Produces({"application/json"})
    public Collection<ItemConverter> getItems(
            @QueryParam("email") String email,
            @QueryParam("start") @DefaultValue("0") int start,
            @QueryParam("max") @DefaultValue("200") int max) {



        // TODO
        List<Item> list = null;

        list = todoService.getList(email);

        if (list == null || list.isEmpty()) {
            Users user = null;
            try {
                user = todoService.findUser(email);
            } catch (RuntimeException re) {
            }

            if (user == null) {
                String accessCode = System.nanoTime() + "";
                todoService.saveUserProfile(email, email, accessCode);
            }

            list = todoService.getList(email);

        }
        return new ItemsConverter(list).getItem();
    }

    private void print() {
        CacheManager cacheManager = CacheManager.getInstance();
        Cache cache = cacheManager.getCache("com.bia.todo.domain.Item");
        cache.setStatisticsAccuracy(Statistics.STATISTICS_ACCURACY_GUARANTEED);
        cache.setStatisticsEnabled(true);
        // store, read etc ... 
        System.out.println ( "Item count : " + cache.getStatistics().getMemoryStoreObjectCount() );
    }

    @Path("/suggest")
    @GET
    @Produces({"application/json"})
    public JSONArray suggest(
            @QueryParam("email") String email,
            @QueryParam("name") String name) {



        // TODO
        //List<Item> list = null;
        //List<String> strings = null;

        //list = todoService.getList(email);
        List<String> lists = this.productRepository.searchByName(name.toLowerCase() + "%");

        return new JSONArray(lists);


        //return strings;
    }

    /**
     * Put method for updating an instance of Item identified by id using XML as the input format.
     *
     * http://localhost:8080/rest/item/add?email=mdshannan@gmail.com&name=coffee
     * 
     * @param id identifier for the entity
     * @param data an ItemConverter entity that is deserialized from a XML stream
     */
    @Path("/add")
    @GET
    @Produces({"application/json"})
    public Collection<ItemConverter> put(
            @QueryParam("email") String email,
            @QueryParam("name") String name) {

        todoService.addItem(name, email);
        return getItems(email, 0, 200);
    }

    /**
     * http://localhost:8080/rest/item/update?email=mdshannan@gmail.com&id=12&due=false
     * @param email
     * @param id
     * @param status
     * @return 
     */
    @Path("/update")
    @GET
    @Produces({"application/json"})
    public Collection<ItemConverter> update(
            @QueryParam("email") String email,
            @QueryParam("id") Integer id,
            @QueryParam("due") Boolean due) {

        todoService.changeItemStatus(id, due, email);
        return getItems(email, 0, 200);
    }

    /**
     * Delete method for deleting an instance of Item identified by id.
     * 
     * http://localhost:8080/rest/item/delete?email=mdshannan@gmail.com&id=12
     *
     * @param id identifier for the entity
     */
    @Path("/delete")
    @GET
    @Produces({"application/json"})
    public Collection<ItemConverter> delete(
            @QueryParam("email") String email,
            @QueryParam("id") Integer id) {

        todoService.deleteItem(id, email);
        return getItems(email, 0, 200);
    }
}
