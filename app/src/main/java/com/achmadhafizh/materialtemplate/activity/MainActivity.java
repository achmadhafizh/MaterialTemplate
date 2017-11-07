package com.achmadhafizh.materialtemplate.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.achmadhafizh.materialtemplate.R;
import com.achmadhafizh.materialtemplate.fragment.FavoritesFragment;
import com.achmadhafizh.materialtemplate.fragment.FontsFragment;
import com.achmadhafizh.materialtemplate.fragment.FriendsFragment;
import com.achmadhafizh.materialtemplate.fragment.NearbyFragment;
import com.achmadhafizh.materialtemplate.fragment.OneFragment;
import com.achmadhafizh.materialtemplate.fragment.RecyclerFragment;
import com.achmadhafizh.materialtemplate.fragment.TabsFragment;
import com.achmadhafizh.materialtemplate.fragment.ThreeFragment;
import com.achmadhafizh.materialtemplate.fragment.TwoFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private AccountHeader navHeader = null;
    private AccountHeader navHeaderAppended = null;
    private Drawer navDrawer = null;
    private Drawer navDrawerAppended = null;
    private IProfile profile;
    private PrimaryDrawerItem navOne, navTwo, navThree;
    private SecondaryDrawerItem navSecOne, navSecTwo, navSecThree, navSecFour;
    private SectionDrawerItem navSection;
    private Fragment curFragment;
    private BottomBarTab nearby, favorite, friends;
    private String title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bmb)
    BoomMenuButton boomMenuButton;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setUpBoomMenuButton();
        setUpBottomBar();
        setUpNavigationDrawer(savedInstanceState);

        if (savedInstanceState == null) {
            navDrawer.deselect();
            bottomBar.selectTabAtPosition(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = navHeader.saveInstanceState(outState);
        outState = navDrawer.saveInstanceState(outState);
        outState = navHeaderAppended.saveInstanceState(outState);
        outState = navDrawerAppended.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(navDrawer != null && navDrawer.isDrawerOpen()) {
            navDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpBoomMenuButton() {
        boomMenuButton.setButtonEnum(ButtonEnum.SimpleCircle);
        boomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        boomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .normalImageRes(R.drawable.butterfly)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    });
            boomMenuButton.addBuilder(builder);
        }
    }

    private void setUpBottomBar() {
        favorite = bottomBar.getTabWithId(R.id.tab_favorites);
        nearby = bottomBar.getTabWithId(R.id.tab_nearby);
        friends = bottomBar.getTabWithId(R.id.tab_friends);

        favorite.setBadgeCount(5);
        nearby.setBadgeCount(15);
        friends.setBadgeCount(7);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                curFragment = null;

                switch (tabId) {
                    case R.id.tab_favorites:
                        title = favorite.getTitle();
                        favorite.removeBadge();
                        curFragment = new FavoritesFragment();
                        break;
                    case R.id.tab_nearby:
                        title = nearby.getTitle();
                        nearby.removeBadge();
                        curFragment = new NearbyFragment();
                        break;
                    case R.id.tab_friends:
                        title = friends.getTitle();
                        friends.removeBadge();
                        curFragment = new FriendsFragment();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

                if (curFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.frame_container, curFragment, title);
                    fragmentTransaction.commitAllowingStateLoss();

                    toolbar.setTitle(title);
                    Log.d(TAG, title + " Selected");
                }
            }
        });
    }

    private void setUpNavigationDrawer(Bundle savedInstanceState) {
        profile = new ProfileDrawerItem().withName("Achmad Hafizh").withEmail("achmadhafizh@metroindonesia.com").withIcon(R.drawable.profile);

        navHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        profile
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        navOne = new PrimaryDrawerItem().withName(R.string.drawer_item_tabs).withDescription(R.string.drawer_item_tabs_desc).withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(1);
        navTwo = new PrimaryDrawerItem().withName(R.string.drawer_item_fonts).withDescription(R.string.drawer_item_fonts_desc).withIcon(GoogleMaterial.Icon.gmd_gamepad).withBadge("99").withIdentifier(2);
        navThree = new PrimaryDrawerItem().withName(R.string.drawer_item_recycler).withDescription(R.string.drawer_item_recycler_desc).withIcon(GoogleMaterial.Icon.gmd_visibility).withIdentifier(3);
        navSection = new SectionDrawerItem().withName(R.string.drawer_item_section_header);
        navSecOne = new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withDescription(R.string.drawer_item_settings).withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(4);
        navSecTwo = new SecondaryDrawerItem().withName(R.string.drawer_item_help).withDescription(R.string.drawer_item_help).withIcon(GoogleMaterial.Icon.gmd_help).withIdentifier(5);
        navSecThree = new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withDescription(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github).withIdentifier(6);
        navSecFour = new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withDescription(R.string.drawer_item_contact).withIcon(GoogleMaterial.Icon.gmd_contacts).withIdentifier(7);

        navDrawer = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withRootView(R.id.drawer_container)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(navHeader)
                .addDrawerItems(
                        navOne,
                        navTwo,
                        navThree,
                        navSection,
                        navSecOne,
                        navSecTwo,
                        navSecThree,
                        navSecFour
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        curFragment = null;

                        if (drawerItem.getIdentifier() == 1) {
                            curFragment = new TabsFragment();
                        } else if (drawerItem.getIdentifier() == 2) {
                            curFragment = new FontsFragment();
                        } else if (drawerItem.getIdentifier() == 3) {
                            curFragment = new RecyclerFragment();
                        } else if (drawerItem.getIdentifier() == 4) {
                            curFragment = new OneFragment();
                        } else if (drawerItem.getIdentifier() == 5) {
                            curFragment = new TwoFragment();
                        } else if (drawerItem.getIdentifier() == 6) {
                            curFragment = new ThreeFragment();
                        } else if (drawerItem.getIdentifier() == 7) {
                            curFragment = new RecyclerFragment();
                        } else {
                            throw new IllegalArgumentException();
                        }

                        if (curFragment != null) {
                            title = ((Nameable) drawerItem).getName().getText(MainActivity.this);

                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                            fragmentTransaction.replace(R.id.frame_container, curFragment, title);
                            fragmentTransaction.commitAllowingStateLoss();

                            toolbar.setTitle(title);
                            Log.d(TAG, title + " Selected");
                        }

                        return false;
                    }
                })
                .withShowDrawerOnFirstLaunch(true)
                .withSavedInstance(savedInstanceState)
                .build();

        navHeaderAppended = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        navDrawerAppended = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(navHeaderAppended)
                .withFooter(R.layout.footer)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                            toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                        }
                        return false;
                    }
                })
                .withDrawerGravity(Gravity.END)
                .append(navDrawer);
    }
}
