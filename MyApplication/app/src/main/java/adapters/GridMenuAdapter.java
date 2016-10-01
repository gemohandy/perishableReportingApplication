package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import fragments.LoginFragment;

public class GridMenuAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public GridMenuAdapter(Context context) {
        this.context = context;
        populate();
    }

    private void populate() {
        LoginFragment loginFragment = LoginFragment.newInstance();
        fragments.add(loginFragment);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_grid_menu, viewGroup, false);
        TextView tvTitle = (TextView)view.findViewById(R.id.igm_tv_title);
        tvTitle.setText(getTitle(i));
        return view;
    }

    public String getTitle(int pos) {
        switch (pos) {
            case 0:
                return "Login";
            default:
                return "";
        }
    }
}
