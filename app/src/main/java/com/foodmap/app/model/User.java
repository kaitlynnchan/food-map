package com.foodmap.app.model;

public class User {

    private String id;

    private String email;
    private String profilePic;

    public User(String id, String email, String profilePic) {
        this.id = id;
        this.email = email;
        this.profilePic = profilePic;
    }

    public void setUser(User user) {
        this.id = user.id;
        this.email = user.email;
        this.profilePic = user.profilePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
