package seme.vilson.david.com.sems.b_tit;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;



public class DashboardActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener{


    private  LinearLayout linearLayout;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitynavigation);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.linearLayout2, new ProductFragment());
        tx.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_, menu);
////        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_settings));
////        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
////        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection Simplifiable IfStatement
//        if (id==R.id.logout)
//        {
//            SharedPreferences sp=getSharedPreferences("status",MODE_PRIVATE);
//            SharedPreferences.Editor editor=sp.edit();
//            editor.putBoolean("isLogin",false);
//            editor.clear();
//            editor.commit();
//            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
//            finish();
//            Toast.makeText(context, " Logout ", Toast.LENGTH_SHORT).show();
//        }
//        else if (id==R.id.activityProduct)
//        {
//        startActivity(new Intent(DashboardActivity.this,AddProductActivity.class));
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.productsFragment)
        {
            fragmentManager.beginTransaction().replace(R.id.linearLayout2, new ProductFragment()).commit();
            Toast.makeText(context, "Products Fragment", Toast.LENGTH_SHORT).show();
//            Snackbar snackbar = Snackbar
//                    .make(linearLayout, "Products Fragment", Snackbar.LENGTH_LONG);
//            snackbar.show();

            // Handle the camera action
        }
        else if (id == R.id.contactsFragment)
        {
            fragmentManager.beginTransaction().replace(R.id.linearLayout2, new ContactsFragment()).commit();
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Contacts Fragment", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else if (id == R.id.settingFragment)
        {
            fragmentManager.beginTransaction().replace(R.id.linearLayout2, new SettingFragment()).commit();
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "Setting Fragment", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
