package com.ali.cupid.Cards;

import java.io.Serializable;

public class cardObject implements Serializable {
    private String  userId,
                    name,
                    profileImageUrl,
                    age,
                    about,
                    job;
    public cardObject(String userId, String name, String age, String about, String job, String profileImageUrl){
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.age = age;
        this.about = about;
        this.job = job;
    }

    public String getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public String getAbout(){
        return about;
    }
    public String getJob(){
        return job;
    }
    public String getProfileImageUrl(){
        return profileImageUrl;
    }
}
