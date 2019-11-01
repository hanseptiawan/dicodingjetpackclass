package dev.craftid.dicodingjetpack.ui.activities;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.data.ContentEntity;
import dev.craftid.dicodingjetpack.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailContentActivityTest {

    private ContentEntity dummyData = FakeDataDummy.getDetailContent(0);

    @Rule
    public ActivityTestRule<DetailContentActivity> activityRule = new ActivityTestRule<DetailContentActivity>(DetailContentActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailContentActivity.class);
            result.putExtra("contentID", dummyData.getIdContent());
            result.putExtra("contentType", dummyData.getContentType());
            return result;
        }
    };

    @Test
    public void loadCourse() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(withText(dummyData.getTitle())));
        onView(withId(R.id.tv_date_release)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_date_release)).check(matches(withText(dummyData.getReleaseDate())));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(withText(dummyData.getOverview())));
        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_user_score)).check(matches(withText(dummyData.getVoteAverage())));
    }

}