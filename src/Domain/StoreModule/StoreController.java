package Domain.StoreModule;

import Domain.Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StoreController {
    private HashMap<Integer, Store> stores;
    private int store_ids;
    private int purchase_ids;
    private static StoreController instance = null;

    public static StoreController get_instance()
    {
        if (instance == null)
            instance = new StoreController();
        return instance;
    }
    private StoreController() {
        this.store_ids = 1;
        this.purchase_ids = 1;
        this.stores = new HashMap<Integer, Store>();
    }

    private int getInc_purchase_ids() {
        // TODO - incerment
        return this.purchase_ids++;
    }

    private int getInc_store_id() {
        // TODO: atomic integer
        this.store_ids++;
        return this.store_ids;
    }

    /**
     *
     * @param store_id represent the store we asked to close
     * @param user_email the user who asked to close the store
     * @throws if the user is not store founder OR the store or the user are not exist.
     * @return false if the store was already close, and true if we close the store temporarily
     */
    public void close_store_temporarily(String user_email, int store_id) throws IllegalAccessException {
        Store store = this.get_store_by_store_id(store_id);

        store.close_store_temporarily(user_email);
        // TODO : update DB @ write to logger
    }

    /**
     *
     * @param store_id represent the store we asked to re-open
     * @param user_email the user who asked to re-open the store
     * @throws if the user is not store founder OR the store or the user are not exist.
     * @return false if the store was already open, and true if the store were re-open
     */
    public void open_close_store(String user_email, int store_id) throws IllegalAccessException {
        if (!this.stores.containsKey(store_id))
        {
            throw new IllegalArgumentException("The store is not exist - store id: "+store_id);
        }
        Store store = this.stores.get(store_id);
        store.open_close_store(user_email);
        // TODO: 22/04/2022 : update DB @ write to logger
    }

    /**
     *
     * @param user_email the user who ask to change the permissions.
     * @param manager_email the user who we ask to change his permissions.
     * @param store_id this method is according specific store.
     * @param permissions a list with all the permissions that we would like give the user.
     * @throws IllegalArgumentException if the store not exist,
     * @throws IllegalArgumentException the manager isn't appointed by user,
     * @throws IllegalArgumentException if the user asking change his own permissions.
     */
    public void edit_manager_specific_permissions(String user_email, String manager_email, int store_id, LinkedList<StorePermission> permissions){
        Store store = this.get_store_by_store_id(store_id);
        store.set_permissions(user_email, manager_email, permissions);
        // TODO: 22/04/2022 : update DB @ write to logger

    }

    /**
     *
     * @param user_email who ask to view store information,
     * @param store_id information of a specific store,
     * @throws IllegalArgumentException if the store not exist,
     * @throws IllegalAccessException the user doesn't have the relevant permission.
     * @return an object with managers & permissions data.
     */
    public String view_store_management_information(String user_email, int store_id) throws IllegalAccessException {
        Store store = this.get_store_by_store_id(store_id);
        return store.view_store_management_information(user_email);
        // TODO: 22/04/2022 : write to logger

    }

    /**
     *
     * @param store_id questions from a specific store,
     * @param user_email who ask to view store questions,
     * @throws IllegalArgumentException if the store not exist,
     * @throws IllegalAccessException the user doesn't have the relevant permission.
     * @return an object with store's questions.
     */
    public String view_store_questions(String user_email, int store_id) throws IllegalAccessException {
        Store store = this.get_store_by_store_id(store_id);
        return store.view_store_questions(user_email);
        // TODO: 22/04/2022 : write to logger

    }

    /**
     *
     * @param store_id
     * @param user_email the manager who wants to answer the question
     * @param question_id a specific question that the user get from view_store_questions
     * @throws IllegalArgumentException if the store not exist,
     * @throws IllegalAccessException the user doesn't have the relevant permission.
     * @param answer the answer of the store manager to the user question.
     */
    public void answer_question(String user_email, int store_id, int question_id, String answer) throws IllegalAccessException {
        Store store = this.get_store_by_store_id(store_id);
        store.answer_question(user_email, question_id, answer);
        // TODO: write to logger & DB
    }

    /**
     *
     * @param store_id the store that we want to get all the purchases history
     * @param user_email the manager
     * @throws IllegalArgumentException if the store not exist,
     * @throws IllegalAccessException the user doesn't have the relevant permission.
     * @return a list with all the purchases history
     */
    public String view_store_purchases_history(String user_email, int store_id) throws IllegalAccessException {
        Store store = this.get_store_by_store_id(store_id);
        return store.view_store_purchases_history(user_email);
        // TODO: write to logger

    }

    /**
     *
     * @param store_id the store who have to close permanently
     * @throws IllegalArgumentException if the store not exist,
     * @throws IllegalAccessException the user doesn't have the relevant permission.
     * @return true if the store was open and now we close it
     */
    public boolean close_store_permanently(int store_id)
    {
        // TODO: have to check that the user is admin
        Store store = this.get_store_by_store_id(store_id);
        return store.close_store_permanently();
        // TODO: update DB @ write to logger
    }

    /**
     *
     * @param store_id
     * @throws IllegalArgumentException if the store not exist
     * @throws IllegalArgumentException if store is not active
     * @return The store
     */
    private Store get_store_by_store_id(int store_id) {
        if (!this.stores.containsKey(store_id))
        {
            throw new IllegalArgumentException("The store is not exist - store id: "+store_id);
        }
        else if (!this.stores.get(store_id).is_active())
        {
            throw new IllegalArgumentException("The store is not active - store id: "+store_id);
        }
        return this.stores.get(store_id);

    }

    /**
     *
     * @param store_id
     * @return store information
     * @throws if the store not exist
     */
    public String find_store_information(int store_id) throws IllegalArgumentException {
        Store store = this.get_store_by_store_id(store_id);
        return store.toString();
    }

    /**
     *
     * @param product_id
     * @param store_id
     * @return product information
     * @throws IllegalArgumentException if store is not exist
     * @throws IllegalArgumentException if store is not active
     */
    public String find_product_information(int product_id, int store_id) throws IllegalArgumentException {
        Store s = get_store_by_store_id(store_id); //throws exception
        return s.get_product_information(product_id);



    }


    private boolean is_product_exist(int product_id, int store_id)
    {
        Store s = get_store_by_store_id(store_id); //throws exception
        return s.is_product_exist(product_id, store_id);
    }


    //------------------------------------------------find product by - Start ----------------------------------------------------
    public List<Product> find_products_by_name(String product_name) {
        List<Product> to_return = new ArrayList<>();
        for (Store store:stores.values()) {
            if (store.is_active())
            {
                List<Product> products_from_store = store.find_products_by_name(product_name);
                to_return.addAll(products_from_store);

            }
        }
        return to_return;

    }
    public List<Product> find_products_by_category(String category) {
        List<Product> to_return = new ArrayList<>();
        for (Store store:stores.values()) {
            if (store.is_active())
            {
                List<Product> products_from_store = store.find_products_by_category(category);
                to_return.addAll(products_from_store);
            }
        }
        return to_return;
    }
    public List<Product> find_products_by_key_words(String key_words) {
        List<Product> to_return = new ArrayList<>();
        for (Store store:stores.values()) {
            if (store.is_active())
            {
                List<Product> products_from_store = store.find_products_by_key_words(key_words);
                to_return.addAll(products_from_store);
            }
        }
        return to_return;
    }
    //------------------------------------------------find product by - End ----------------------------------------------------


    public void add_product_to_store(String user_email, int store_id, int quantity, String name, double price, String category, List<String> key_words)
            throws IllegalArgumentException, IllegalAccessException {
        Store store = get_store_by_store_id(store_id);
        store.add_product(user_email, name, price, category, key_words, quantity);
    }

    public void delete_product_from_store(String user_email, int product_id, int store_id) throws IllegalAccessException {
        Store store = get_store_by_store_id(store_id);
        store.delete_product(product_id, user_email);
    }
    //------------------------------------------------ edit product - Start ----------------------------------------------
    public void edit_product_name(String user_email, int product_id, int store_id, String name) throws IllegalAccessException {
        Store store = get_store_by_store_id(store_id); //trows exceptions
        store.edit_product_name(user_email, product_id, name);
    }

    public void edit_product_price(String user_email, int product_id, int store_id, double price) throws IllegalAccessException {
        Store store = get_store_by_store_id(store_id);
        store.edit_product_price(user_email, product_id, price);
    }

    public void edit_product_category(String user_email, int product_id, int store_id, String category) throws IllegalAccessException {
        Store store = get_store_by_store_id(store_id);
        store.edit_product_category(user_email, product_id, category);
    }

    public void edit_product_key_words(String user_email, int product_id, int store_id, List<String> key_words) throws IllegalAccessException {
        Store store = get_store_by_store_id(store_id);
        store.edit_product_key_words(user_email, product_id, key_words);
    }

    //------------------------------------------------ edit product - End ----------------------------------------------

    public double check_cart_available_products_and_calc_price(Cart cart) {
        HashMap<Integer, Basket> baskets_of_storesID = cart.get_baskets();
        double cart_price=0;
        for (Basket basket : baskets_of_storesID.values()){

            int store_id = basket.getStore_id();
            if(stores.containsKey(store_id))
            {

                double basket_price = stores.get(store_id).check_available_products_and_calc_price(basket); // throw if not available
                cart_price += basket_price;
            }
            else
            {
                throw new IllegalArgumentException("Store does not exist - store id: "+ store_id);
                //not suppose to happen
            }

        }
        return cart_price;

    }
    public Product checkAvailablityAndGet(int store_id, int product_id, int quantity)
    {
        if(!stores.containsKey(store_id))
        {
            throw new IllegalArgumentException("StoreController:checkAvailablityAndGet - Store does not exist , store id: "+store_id);
        }
        return  stores.get(store_id).checkAvailablityAndGet(product_id, quantity);
    }

    public void update_stores_inventory(Cart cart) {
        HashMap<Integer, Basket> baskets_of_storesID = cart.get_baskets();
        for (Basket basket : baskets_of_storesID.values())
        {
            int store_id = basket.getStore_id();
            if(!stores.containsKey(store_id))
            {
                throw new IllegalArgumentException("Store does not exist - store id: "+ store_id);
            }
        }
        for (Basket basket : baskets_of_storesID.values())
        {
            int store_id = basket.getStore_id();
            this.stores.get(store_id).remove_basket_products_from_store(basket, this.getInc_purchase_ids());
        }

    }


    public void open_store(String founder_email, String store_name) {
        int store_id = this.getInc_store_id();
        Store store = new Store(store_id, founder_email, store_name);
        this.stores.put(store_id, store);
    }

    public void add_review(String user_email, int product_id, int store_id, String review) {
        Store s = this.get_store_by_store_id(store_id);//throws
        s.add_review(product_id, user_email, review);

    }

    public void rate_product(String user_email, int product_id, int store_id, int rate) {
        Store s = this.get_store_by_store_id(store_id); //throws
        s.add_product_rating(user_email, product_id, rate);
    }

    public void rate_store(String user_email, int store_id, int rate) {
        Store to_rate = this.get_store_by_store_id(store_id);//throw exceptions
        to_rate.add_rating(user_email, rate);

    }

    public void add_owner(String user_email, String user_email_to_appiont, int store_id)
    {
        Store s = this.get_store_by_store_id(store_id);//throws
        s.add_owner(user_email, user_email_to_appiont);
    }
}

