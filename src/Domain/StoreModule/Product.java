package Domain.StoreModule;

import java.util.List;

public class Product {
    private String name;
    private int store_id;
    private int product_id;
    private double price;
    private ProductReview productReview;
    private String category;
    private List<String> key_words;

    public Product(String name, int store_id, int product_id, double price, String category, List<String> key_words) {
        this.name = name;
        this.store_id = store_id;
        this.product_id = product_id;
        this.price = price;
        this.productReview = new ProductReview();
        this.category = category;
        this.key_words = key_words;
    }
    public void add_review(String user_email, String review) {
        this.productReview.add_review(user_email, review);
    }
    public void add_rating(String user_email, int rating) {
        this.productReview.add_rating(user_email, rating);
    }



//---------------------------------------------------------------------- Getters - Start ------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public int getStore_id() {
        return store_id;
    }

    public double getPrice() {
        return price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public List<String> getKey_words() {
        return key_words;
    }

    public ProductReview getProductReview() {
        return productReview;
    }

    public String getCategory() {
        return category;
    }

//---------------------------------------------------------------------- Getters - End ------------------------------------------------------------------------------------

//---------------------------------------------------------------------- Setters - Start ------------------------------------------------------------------------------------

    public void setName(String name) {
        //check validity
        this.name = name;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductReview(ProductReview productReview) {
        this.productReview = productReview;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setKey_words(List<String> key_words) {
        this.key_words = key_words;
    }
    //---------------------------------------------------------------------- Setters - End ------------------------------------------------------------------------------------



}