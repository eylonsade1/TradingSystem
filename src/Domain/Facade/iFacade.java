package Domain.Facade;

import Domain.StoreModule.Policy.DiscountPolicy;
import Domain.StoreModule.Policy.PurchasePolicy;
import Domain.StoreModule.Policy.Rule;
import Domain.StoreModule.Product.Product;
import Domain.StoreModule.StorePermission;

import java.util.LinkedList;
import java.util.List;

public interface iFacade {


    /**
     * Requirment 2.1.1
     */
//    String guest_login();

    /**
     * Requirment 2.1.2
     */
    String logout();

    /**
     * Requirment 2.1.3
     */
    String register(String Email, String pw, String name, String lastName);

    /**
     * Requirment 2.1.4
     */
    String login(String Email, String password);

    /**
     * Requirment 2.2.1
     */
    String find_store_information(int store_id);

    String find_product_information(int product_id, int store_id);

    /**
     * Requirment 2.2.2
     */
    String find_products_by_name(String product_name);

    String find_products_by_category(String category);

    String find_products_by_keywords(String key_words);

    /**
     * Requirment 2.2.3
     */
    String add_product_to_cart(int storeID, int productID, int quantity);

    String remove_product_from_cart(int storeID, int productID);

    /**
     * Requirment 2.2.4
     */
    String view_user_cart();

    String edit_product_quantity_in_cart(int storeID, int productID, int quantity);

    /**
     * Requirment 2.2.5
     */

    String buy_cart(String paymentInfo, String supplyInfo);

    /**
     * Requirment 2.3.2
     */
    String open_store(String store_name);


    /**
     * Requirment 2.3.3
     */
    String add_product_review(int product_id, int store_id, String review);

    /**
     * Requirment 2.3.4
     */
    String rate_product(int product_id, int store_id, int rate);

    String rate_store(int store_id, int rate);

    /**
     * Requirment 2.3.5
     */
    String send_question_to_store(int store_id, String question);

    /**
     * Requirment 2.3.6
     */
    String send_question_to_admin(String question); // wrong signature

    /**
     * Requirment 2.3.7
     */
    String view_user_purchase_history();

    /**
     * Requirment 2.3.8
     */
    String get_user_email();

    String get_user_name();

    String get_user_last_name();


    /**
     * Requirment 2.4.1
     */


    String add_product_to_store(int store_id, int quantity, String name, double price,
                                String category, List<String> key_words);

    String delete_product_from_store(int product_id, int store_id);

    String edit_product_name(int product_id, int store_id, String name);

    String edit_product_price(int product_id, int store_id, double price);

    String edit_product_category(int product_id, int store_id, String category);

    String edit_product_key_words(int product_id, int store_id, List<String> key_words);

    /**
     * Requirment 2.4.2
     */
    String set_store_purchase_policy(int store_id, PurchasePolicy policy);

    String set_store_discount_policy(int store_id, DiscountPolicy policy);

    /**
     * Requirment 2.4.3
     */
    String set_store_purchase_rules(int store_id, Rule rule);

    /**
     * Requirment 2.4.4
     */
    String add_owner(String user_email_to_appoint, int store_id);

    /**
     * Requirment 2.4.5
     */
    String delete_owner(String user_email_to_delete_appointment, int store_id);

    /**
     * Requirment 2.4.6
     */
    String add_manager(String user_email_to_appoint, int store_id);

    /**
     * Requirment 2.4.7
     */
    String edit_manager_permissions(String manager_email, int store_id, LinkedList<StorePermission> permissions);

    /**
     * Requirment 2.4.8
     */
    String delete_manager(String user_email_to_delete_appointment, int store_id);

    /**
     * Requirment 2.4.9
     */
    String close_store_temporarily(int store_id);

    /**
     * Requirment 2.4.10
     */
    String open_close_store(int store_id);

    /**
     * Requirment 2.4.11
     */
    String view_store_management_information(int store_id);

    /**
     * Requirment 2.4.12
     */
    String manager_view_store_questions(int store_id);

    String manager_answer_question(int store_id, int question_id, String answer);

    /**
     * Requirment 2.4.13
     */
    String view_store_purchases_history(int store_id);

    /**
     * Requirment 2.6.1
     */
    String close_store_permanently(int store_id);


    /**
     * Requirment 2.6.2
     */
    String remove_user(String email);

    /**
     * Requirment 2.6.3
     */
    String admin_view_users_questions();

    String admin_answer_user_question(int question_id, String answer);

    /**
     * Requirment 2.6.4
     */
    String view_user_purchases_history(String user_email);
    String admin_view_store_purchases_history(int store_id);

    /**
     * Requirment 2.6.5
     */
    String get_market_stats();

    // ---------------- added functions ------------------------------------------
    String unregister(String password); // TODO:  add security functions

    String edit_name(String pw, String new_name);

    String edit_last_name(String pw, String new_last_name);

    String edit_password(String pw, String password);

    String admin_view_user_purchases_history(String user_email);

    String get_user_security_question();

    String edit_name_premium(String pw, String new_name, String answer);

    String edit_last_name_premium(String pw, String new_last_name, String answer);

    String edit_password_premium(String pw, String password, String answer);

    String improve_security(String password,String question, String answer);
    }
