package com.example.tugasbesar_pbp_f;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddCarTesting {

    @Rule
    public ActivityTestRule<AddCar> mActivityTestRule = new ActivityTestRule<>(AddCar.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE");

    @Test
    public void addCarTesting() {
//        ViewInteraction textInputEditText = onView(
//                allOf(withId(R.id.emailInput),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.textView4),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText.perform(replaceText("admin.cardido@cardido.masuk.web.id"), closeSoftKeyboard());
//
//        ViewInteraction textInputEditText2 = onView(
//                allOf(withId(R.id.passwordInput),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.textView5),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText2.perform(replaceText("pbpsemangat"), closeSoftKeyboard());
//
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.btnLogin), withText("Login"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                6),
//                        isDisplayed()));
//        materialButton.perform(click());

//        ViewInteraction cardView = onView(
//                allOf(withId(R.id.cvAddUser),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                2)));
//        cardView.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.inCarName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutCName),
                                        0),
                                1)));
        textInputEditText3.perform(scrollTo(), replaceText("Honda "), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.inCarType),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutCType),
                                        0),
                                1)));
        textInputEditText4.perform(scrollTo(), replaceText("SUV"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.inCarPlat),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutCPlat),
                                        0),
                                1)));
        textInputEditText5.perform(scrollTo(), replaceText("B9999KM"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.inPassanger),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutPassger),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("5"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.inBags),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutBags),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("5"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.inFuel),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutFuel),
                                        0),
                                1)));
        textInputEditText8.perform(scrollTo(), replaceText("Full to full"), closeSoftKeyboard());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.inTotal),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layoutTotal),
                                        0),
                                1)));
        textInputEditText9.perform(scrollTo(), replaceText("125000"), closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton9.perform(scrollTo(), click());

//        ViewInteraction materialButton10 = onView(
//                allOf(withId(R.id.btnGaleri), withText("OPEN GALERY"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                7)));
//        materialButton10.perform(scrollTo(), click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.btnSaveCar), withText("Add to List"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton11.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
