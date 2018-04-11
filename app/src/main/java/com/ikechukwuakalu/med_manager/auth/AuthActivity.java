package com.ikechukwuakalu.med_manager.auth;

import android.os.Bundle;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseAppCompatActivity;

import javax.inject.Inject;

public class AuthActivity extends BaseAppCompatActivity {

    @Inject AuthFragment authFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        addFragment(authFragment, R.id.auth_container);
    }
}
