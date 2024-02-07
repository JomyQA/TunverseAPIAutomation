package api.payload;

import com.github.javafaker.Bool;

public class user {

    String firstname;
    String lastname;
    String email;
    String password;
    boolean terms_condition_checked;
    boolean paying_tax;
    String provider;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getTerms_condition_checked() {
        return terms_condition_checked;
    }

    public void setTerms_condition_checked(String terms_condition_checked) {
        this.terms_condition_checked = Boolean.parseBoolean(terms_condition_checked);
    }

    public boolean getPaying_tax() {
        return paying_tax;
    }

    public void setPaying_tax(String paying_tax) {
        this.paying_tax = Boolean.parseBoolean(paying_tax);
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
