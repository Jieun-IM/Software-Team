package com.example.damhwa2.model;

import java.util.List;

public class TestUser {
    private String name;
    private String gender;
    private int age;
    private List<String> tags;

    public TestUser(String name, String gender, int age, List<String> tags) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return name + " (" + gender + ", " + age + ", " + tags + ")";
    }
}
