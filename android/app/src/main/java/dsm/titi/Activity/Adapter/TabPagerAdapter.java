package dsm.titi.Activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dsm.titi.Activity.Fragment.Fragment_Album;
import dsm.titi.Activity.Fragment.Fragment_Map;
import dsm.titi.Activity.Fragment.Fragment_Writing;


/**
 * Created by ghdth on 2018-04-23.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
  private final List<Fragment> fragmentList=new ArrayList<>();
  private final List<String> FragmentTitleList=new ArrayList<>();

  public TabPagerAdapter(FragmentManager manager){
      super(manager);
  }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment,String title){
       fragmentList.add(fragment);
       FragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentTitleList.get(position);
    }

}
