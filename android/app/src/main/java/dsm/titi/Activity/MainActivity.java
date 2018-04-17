package dsm.titi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import dsm.titi.Activity.Adapter.HomeViewPagerAdapter;
import dsm.titi.Activity.Fragment.HomeFragment;
import dsm.titi.R;

public class MainActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    AHBottomNavigationViewPager viewPager;
    HomeFragment currentFragment=null;
    HomeViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        this.createNavItem();

    }
    private void initUI(){
        bottomNavigation=(AHBottomNavigation)findViewById(R.id.bottom_navigation);
        viewPager=(AHBottomNavigationViewPager)findViewById(R.id.view_pager);
        AHBottomNavigationItem item1=new AHBottomNavigationItem("Map",R.drawable.map_location,R.color.colorPrimary);
        AHBottomNavigationItem item2=new AHBottomNavigationItem("Album",R.drawable.photo_library,R.color.colorPrimary);
        AHBottomNavigationItem item3=new AHBottomNavigationItem("writing",R.drawable.edit,R.color.colorPrimary);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(currentFragment==null) currentFragment=adapter.getCurrentFragment();
                if(wasSelected&&currentFragment!=null){
                    bottomNavigation.refresh();
                    return true;
                }
                if(currentFragment!=null)currentFragment.willBeHidden();
                viewPager.setCurrentItem(position,false);
                if(currentFragment==null)return true;
                currentFragment=adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();
                return true;
            }
        });
        viewPager.setOffscreenPageLimit(4);
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();
    }
    public void createNavItem(){
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        bottomNavigation.setCurrentItem(0);
    }

}
