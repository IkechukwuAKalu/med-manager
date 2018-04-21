package com.ikechukwuakalu.med_manager.user_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ikechukwuakalu.med_manager.R;
import com.ikechukwuakalu.med_manager.base.BaseAppCompatActivity;
import com.ikechukwuakalu.med_manager.user_profile.editor.EditorFragment;
import com.ikechukwuakalu.med_manager.user_profile.viewer.ViewerFragment;

import javax.inject.Inject;

public class UserProfileActivity extends BaseAppCompatActivity {

    @Inject ViewerFragment viewerFragment;
    @Inject EditorFragment editorFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        addFragment(viewerFragment, R.id.user_profile_container);
    }

    public void showEditorFragment() {
        replaceFragment(editorFragment, R.id.user_profile_container, null);
    }
}
