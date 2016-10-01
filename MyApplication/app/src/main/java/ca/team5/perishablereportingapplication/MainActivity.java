package ca.team5.perishablereportingapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fragments.GridMenuFragment;

public class MainActivity extends FragmentActivity {
    private TextView tvWelcome;
    GridMenuFragment gmFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        bind();
    }

    private void bind() {
        tvWelcome = (TextView)findViewById(R.id.main_header);
        gmFrag = GridMenuFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, gmFrag, gmFrag.getTag()).addToBackStack(gmFrag.getTag()).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bind();
    }
}