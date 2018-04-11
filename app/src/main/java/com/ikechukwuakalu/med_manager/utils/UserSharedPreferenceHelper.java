package com.ikechukwuakalu.med_manager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ikechukwuakalu.med_manager.data.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserSharedPreferenceHelper {

    private final String USER_ID = "user_id";
    private final String USER_NAME = "user_name";
    private final String USER_PHONE = "user_phone";
    private final String USER_EMAIL = "user_email";
    private final String USER_PROFESSION = "user_profession";
    private final String USER_BLOOD_GROUP = "user_blood_group";
    private final String USER_GENO_TYPE = "user_geno_type";
    private final String USER_PHOTO = "user_photo";

    private SharedPreferences preferences;

    @Inject
    public UserSharedPreferenceHelper(Context context) {
        String SP_NAME = "Med-Man-SP";
        preferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public boolean isUserSignedIn() {
        return preferences.getString(USER_ID, null) != null;
    }

    public void signInUser(User user) {
        SharedPreferences.Editor editor = preferences.edit();
        addUserDetails(user, editor);
        editor.apply();
    }

    private void addUserDetails(User user, SharedPreferences.Editor editor) {
        editor.putString(USER_ID, user.getId());
        editor.putString(USER_NAME, user.getName());
        editor.putString(USER_PHONE, user.getPhone());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_PROFESSION, user.getProfession());
        editor.putString(USER_BLOOD_GROUP, user.getBloodGroup());
        editor.putString(USER_GENO_TYPE, user.getGenoType());
        editor.putString(USER_PHOTO, user.getPhotoUri());
    }

    public User getUserDetails() {
        String id = preferences.getString(USER_ID, null);
        String name = preferences.getString(USER_NAME, "Add Name");
        String phone = preferences.getString(USER_PHONE, "None");
        String email = preferences.getString(USER_EMAIL, "None");
        String profession = preferences.getString(USER_PROFESSION, "Not set");
        String bloodGroup = preferences.getString(USER_BLOOD_GROUP, "Not set");
        String genoType = preferences.getString(USER_GENO_TYPE, "Not set");
        String photo = preferences.getString(USER_PHOTO, null);

        return new User(id, name, phone, email, profession, bloodGroup, genoType, photo);
    }

    public void signUserOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
