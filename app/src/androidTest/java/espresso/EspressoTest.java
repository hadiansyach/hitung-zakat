package espresso;

import static androidx.test.espresso.action.ViewActions.click;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.d3if3032.hitungzakat.MainActivity;
import com.d3if3032.hitungzakat.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {
    @Rule
        public ActivityScenarioRule<MainActivity> activityScenarioRule =
                new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Test
    public void buttonClicked(){
        Espresso.onView(ViewMatchers.withId(R.id.btnZakatPenghasilan))
                .perform(click());
    }
//    @Test
//    public void textView(){
//        new ActivityScenarioRule<ZakatPenghasilanActivity>(ZakatPenghasilanActivity.class);
//        Espresso.onView(ViewMatchers.withId(R.id.kondisi))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Hello World")));
//        Espresso.onView(ViewMatchers.withId(R.id.uangZakat))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Rp 120000")));
//        Espresso.onView(ViewMatchers.withId(R.id.totalPendapatan))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Rp 99999999")));
//
//    }
}