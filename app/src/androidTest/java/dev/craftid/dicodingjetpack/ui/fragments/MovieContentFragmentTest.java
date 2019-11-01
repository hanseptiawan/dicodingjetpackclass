package dev.craftid.dicodingjetpack.ui.fragments;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.testing.SingleFragmentActivity;
import dev.craftid.dicodingjetpack.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieContentFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MovieContentFragment movieContentFragment = new MovieContentFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(movieContentFragment);
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.cm_rv_content)).check(matches(isDisplayed()));
        onView(withId(R.id.cm_rv_content)).check(new RecyclerViewItemCountAssertion(20));
    }
}