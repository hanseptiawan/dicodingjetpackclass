package dev.craftid.dicodingjetpack.ui.fragments;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.data.ContentEntity;
import dev.craftid.dicodingjetpack.testing.SingleFragmentActivity;
import dev.craftid.dicodingjetpack.utils.FakeDataDummy;
import dev.craftid.dicodingjetpack.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SearchResultFragmentTest {

    private ContentEntity contentEntity = FakeDataDummy.getMovieDummies().get(0);

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private SearchResultFragment searchResultFragment = new SearchResultFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().addFragment(searchResultFragment);
    }

    @Test
    public void loadSearchResults() {
        onView(withId(R.id.sfm_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.sfm_recycler_view)).check(new RecyclerViewItemCountAssertion(1));
    }

}