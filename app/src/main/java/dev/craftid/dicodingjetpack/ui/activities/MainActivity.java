package dev.craftid.dicodingjetpack.ui.activities;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.ui.fragments.MainContentFragment;
import dev.craftid.dicodingjetpack.ui.fragments.SearchResultFragment;
import dev.craftid.dicodingjetpack.utils.EventUtils;

public class MainActivity extends AppCompatActivity {

    private EventUtils eventUtils;
    private OnSearchListener onSearchListener;

    private TextInputEditText mainEtSearch;
    private TextInputLayout mainTilSearch;

    private static String MAIN_FRAGMENT = "mainContentFragment";
    private static String SEARCH_RESULT_FRAGMENT = "searchResultFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventUtils = new EventUtils(this);

        initView();
        initAction();
    }

    private void initView() {
        mainEtSearch = findViewById(R.id.main_et_search);
        mainTilSearch = findViewById(R.id.main_til_search);

        loadMainContentFragment();
    }

    private void initAction() {

        mainEtSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                eventUtils.hideKeyboard();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(SEARCH_RESULT_FRAGMENT);
                if (fragment == null) {
                    if (checkSearchField())
                        loadSearchResultFragment(Objects.requireNonNull(mainEtSearch.getText()).toString());
                } else {
                    if (checkSearchField())
                        onSearchListener.OnSubmit(Objects.requireNonNull(mainEtSearch.getText()).toString());
                }
                return true;
            }
            return false;
        });

        mainTilSearch.setEndIconOnClickListener(view -> {
            Objects.requireNonNull(mainEtSearch.getText()).clear();
            FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_translate_bottom, R.anim.exit_translate_bottom, R.anim.enter_translate_bottom, R.anim.exit_translate_bottom);
            getSupportFragmentManager().popBackStack();
        });
    }

    private boolean checkSearchField() {
        String query = Objects.requireNonNull(mainEtSearch.getText()).toString();
        if (query.isEmpty()) {
            mainEtSearch.requestFocus();
            Toast.makeText(this, "Please input some keywords", Toast.LENGTH_SHORT).show();
            return false;
        } else if (query.length() < 4) {
            Toast.makeText(this, "Minimum is 4 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void loadSearchResultFragment(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_translate_bottom, R.anim.exit_translate_bottom,
                R.anim.enter_translate_bottom, R.anim.exit_translate_bottom);
        fragmentTransaction.add(R.id.main_fl_content, SearchResultFragment.newInstance(bundle), SEARCH_RESULT_FRAGMENT);
        fragmentTransaction.addToBackStack(MAIN_FRAGMENT);
        fragmentTransaction.commit();
    }

    private void loadMainContentFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_translate_bottom, R.anim.exit_translate_bottom,
                R.anim.enter_translate_bottom, R.anim.exit_translate_bottom);
        fragmentTransaction.replace(R.id.main_fl_content, MainContentFragment.newInstance(), MAIN_FRAGMENT);
        fragmentTransaction.commit();
    }

    public void setOnSearchListener(OnSearchListener onSearchListener) {
        this.onSearchListener = onSearchListener;
    }

    public interface OnSearchListener {
        void OnSubmit(String query);
    }

    private long currentTimeMillis;
    @Override
    public void onBackPressed() {
        int TIME_MILLIS = 1000;
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SEARCH_RESULT_FRAGMENT);
        if (fragment != null){
            if (mainEtSearch.getText() != null)
                mainEtSearch.getText().clear();
        } else if (currentTimeMillis + TIME_MILLIS > currentTimeMillis) {
            moveTaskToBack(true);
            return;
        } else {
            Toast.makeText(this, "Press again to minimize app", Toast.LENGTH_SHORT).show();
        }

        currentTimeMillis = System.currentTimeMillis();
    }
}
