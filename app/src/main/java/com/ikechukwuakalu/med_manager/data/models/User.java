package com.ikechukwuakalu.med_manager.data.models;

public class User {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String profession;
    private String bloodGroup;
    private String genoType;
    private String photoUri;

    public User(String id, String name, String phone, String email, String profession,
                String bloodGroup, String genoType, String photoUri) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.profession = profession;
        this.bloodGroup = bloodGroup;
        this.genoType = genoType;
        this.photoUri = photoUri;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getGenoType() {
        return genoType;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return (getId().equals(user.getId()) &&
                getName().equals(user.getName())) &&
                (getPhone().equals(user.getPhone())) &&
                (getEmail().equals(user.getEmail())) &&
                (getProfession().equals(user.getProfession())) &&
                (getBloodGroup().equals(user.getBloodGroup())) &&
                (getGenoType().equals(user.getGenoType())) &&
                (getPhotoUri().equals(user.getPhotoUri()));
    }

    @Override
    public String toString() {
        return "User(id: " + getId() +
                " name: " + getName() +
                " phone: " + getPhone() +
                " email: " + getEmail() +
                " profession: " + getProfession() +
                " bloodGroup: " + getBloodGroup() +
                " genoType: " + getGenoType() +
                " photoUri: " + getPhotoUri() + ")";
    }
}
