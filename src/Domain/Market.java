package Domain;

import Domain.StoreModule.*;
import Service.iService;

import java.util.HashMap;
import java.util.LinkedList;

public class Market implements iService {
    private StoreController store_controller;

    public Market() {
        this.store_controller = StoreController.get_instance();
    }


    @Override
    public void init_market() {
        //Tom
        //connect to payment service
        //connect to supply service
    }

    @Override
    public boolean payment(int price) {
        //Tom
        return true;
    }

    @Override
    public boolean supply(int user_id, int purchase_id) {
        //Tom
        return true;

    }

    @Override
    public double guest_login() {
        return 0;
    }

    @Override
    public double login(String username, String password) {
        return 0;
    }

    @Override
    public double logout() {
        return 0;
    }

    @Override
    public double register() {
        return 0;
    }

    @Override
    public String find_store_information(int store_id) {
        String info="";
        try
        {
            info = this.store_controller.find_store_information(store_id);
        }
        catch (IllegalArgumentException e)
        {
            return "Store does not exist in the market";
        }
        return info;

    }

    @Override
    public String find_product_information(int product_id) {
        String info="";
        try
        {
            info = this.store_controller.find_product_information(product_id);
        }
        catch (IllegalArgumentException e)
        {
            return "Product does not exist in the market";
        }
        return info;
    }

    /**
     *
     * @param product_name
     * @return Product if exist by the identifier
     * @throws Product does not exist
     */
    @Override
    public Product find_product_by_name(String product_name) throws IllegalArgumentException{
        Product p = this.store_controller.find_product_by_name(product_name);
        if (p==null)
        {
            throw new IllegalArgumentException("Product does not exist - product name: "+product_name);
        }
        return p;
    }

    /**
     *
     * @param category
     * @return Product if exist by the identifier
     * @throws Product does not exist
     */
    @Override
    public Product find_product_by_category(String category) throws IllegalArgumentException{
        Product p = this.store_controller.find_product_by_category(category);
        if (p==null)
        {
            throw new IllegalArgumentException("Product does not exist - product category: "+category);
        }
        return p;
    }

    /**
     *
     * @param key_word
     * @return Product if exist by the identifier
     * @throws Product does not exist
     */
    @Override
    public Product find_product_by_keyword(String key_word) throws IllegalArgumentException{
        Product p = this.store_controller.find_product_by_keyword(key_word);
        if (p==null)
        {
            throw new IllegalArgumentException("Product does not exist - product key word: "+key_word);
        }
        return p;
    }

    @Override
    public double view_user_cart() {
        return 0;
    }

    @Override
    public double delete_product_from_cart() {
        return 0;
    }

    @Override
    public double add_product_to_cart() {
        return 0;
    }

    @Override
    public double edit_product_from_cart() {
        return 0;
    }

    @Override
    public int buy_cart() {
        return 0;
    }

    @Override
    public int open_store() {
        return 0;
    }

    @Override
    public int add_review(int product_id) {
        return 0;
    }

    @Override
    public int rate_product(int product_id) {
        return 0;
    }

    @Override
    public int rate_store(int store_id) {
        return 0;
    }

    @Override
    public int send_request_to_store(int store_id, String request) {
        return 0;
    }

    @Override
    public double send_complain() {
        return 0;
    }

    @Override
    public double view_user_purchase_history() {
        return 0;
    }

    @Override
    public double view_account_details() {
        return 0;
    }

    @Override
    public double edit_account_details() {
        return 0;
    }

    @Override
    public double add_security_personal_question() {
        return 0;
    }

    @Override
    public void add_product_to_store(Product product, int store_id) {
        store_controller.add_product_to_store(product, store_id);
    }

    @Override
    public void delete_product_from_store() {
        //Tom
    }

    @Override
    public void edit_product(int store_id, int product_id) {
        //Tom
    }

    @Override
    public void add_discount_rule() {
        //Tom
    }

    @Override
    public void delete_discount_rule() {
        //Tom
    }

    @Override
    public void add_purchase_rule() {
        //Tom
    }

    @Override
    public void delete_purchase_rule() {
        //Tom
    }

    @Override
    public int add_owner() {
        return 0;
    }

    @Override
    public int add_manager() {
        return 0;
    }

    @Override
    public int delete_owner() {
        return 0;
    }

    @Override
    public int delete_manager() {
        return 0;
    }

    @Override
    public void edit_manager_permissions(int user_id, int manager_id, int store_id, LinkedList<StorePermission> permissions) {
        try
        {
            this.store_controller.edit_manager_specific_permissions(user_id, manager_id, store_id, permissions);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean close_store_temporarily(int store_id, int user_id) {
        boolean answer = false;
        try
        {
            answer = this.store_controller.close_store_temporarily(store_id, user_id);
        }
        catch (Exception e)
        {
            return false;
        }
        return answer;
    }

    @Override
    public boolean open_close_store(int store_id, int user_id) {
        boolean answer = false;
        try
        {
            answer = this.store_controller.open_close_store(store_id, user_id);
        }
        catch (Exception e)
        {
            return false;
        }
        return answer;

    }

    @Override
    public String view_store_management_information(int user_id, int store_id) {
        String answer;
        try
        {
            StoreManagersInfo info = this.store_controller.view_store_management_information(user_id, store_id);
            answer = info.toString();
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return answer;
    }

    @Override
    public String view_store_questions(int store_id, int user_id) {
        String answer;
        try
        {
            HashMap<Integer, Question> questions = this.store_controller.view_store_questions(store_id, user_id);
            answer = questions.toString();
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return answer;
    }

    @Override
    public boolean manager_answer_question(int store_id, int user_id, int question_id, String answer) {
        try
        {
            this.store_controller.answer_question(store_id, user_id, question_id, answer);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public String view_store_purchases_history(int store_id, int user_id) {
        HashMap<Integer, Purchase> info;
        String answer;
        try
        {
            info = this.store_controller.view_store_purchases_history(store_id, user_id);
            answer = info.toString();
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return answer;

    }

    @Override
    public boolean close_store_permanently(int store_id, int user_id) {
        boolean answer = false;
        try
        {
            answer = this.store_controller.close_store_permanently(store_id, user_id);
        }
        catch (Exception e)
        {
            return false;
        }
        return answer;

    }

    @Override
    public double delete_user_from_system() {
        return 0;
    }

    @Override
    public double view_system_questions() {
        return 0;
    }

    @Override
    public double admin_answer_question() {
        return 0;
    }

    @Override
    public int get_system_statistics() {
        return 0;
    }
}