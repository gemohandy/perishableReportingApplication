package ca.team5.perishablereportingapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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

import java.io.IOException;
import java.util.ArrayList;

import data.Order;
import data.Reservation;
import datasources.OrderDataSource;
import datasources.ReservationDataSource;
import fragments.GridMenuFragment;

public class MainActivity extends FragmentActivity {
    private TextView tvWelcome;
    GridMenuFragment gmFrag;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        bind();
        try {
            registerReceiver(receiver, getBRIntentFilter());
            Intent i = new Intent();
            i.setAction("update");
        } catch(Exception e) {
            e.printStackTrace();
        }
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
        try {
            registerReceiver(receiver, getBRIntentFilter());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            unregisterReceiver(receiver);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshData() {
        OrderDataSource ods = new OrderDataSource();
        ReservationDataSource rds = new ReservationDataSource();
        try {
            ods.getHttpGETInputStream();
            orders = ods.getOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rds.getHttpGETInputStream();
            reservations = rds.getReservations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshData(Order order) {
        OrderDataSource ods = new OrderDataSource();
        ReservationDataSource rds = new ReservationDataSource();
            orders.add(order);
        gmFrag.setOrders(orders);
    }

    public IntentFilter getBRIntentFilter(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("update");
        return intentFilter;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Order order = null;
            if (intent != null) {
                if (intent.getBundleExtra("bundle") != null) {
                    Bundle b = intent.getBundleExtra("bundle");
                    order = b.getParcelable("order");
                }
                if (intent.getAction().equals("update")) {
                    final Order finalOrder = order;
                    AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            if (finalOrder != null) {
                                refreshData(finalOrder);
                            } else {
                                refreshData();
                            }
                            return null;
                        }
                    }.execute();
                }
            }
        }
    };
}