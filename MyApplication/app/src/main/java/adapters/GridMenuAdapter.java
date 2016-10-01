package adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import fragments.LoginFragment;
import fragments.MakeOrderFragment;
import fragments.RegisterFragment;
import fragments.ReserveOrderFragment;
import fragments.ReviewReservedOrdersFragment;

public class GridMenuAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public GridMenuAdapter(Context context) {
        this.context = context;
        populate();
    }

    private void populate() {
        LoginFragment loginFragment = LoginFragment.newInstance();
        MakeOrderFragment makeOrderFragment = MakeOrderFragment.newInstance();
        ReserveOrderFragment roFrag = ReserveOrderFragment.newInstance();
        RegisterFragment rFr = RegisterFragment.newInstance();
        ReviewReservedOrdersFragment rrofr = ReviewReservedOrdersFragment.newInstance();
        fragments.add(loginFragment);
        fragments.add(rFr);
        fragments.add(makeOrderFragment);
        fragments.add(roFrag);
        fragments.add(rrofr);


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
        ImageView ivFeature = (ImageView)view.findViewById(R.id.igm_iv_feature);
        TextView tvTitle = (TextView)view.findViewById(R.id.igm_iv_title);
        tvTitle.setText(getTitle(i));
        ivFeature.setImageDrawable(getDrawableAt(i));
        return view;
    }

    public Drawable getDrawableAt(int pos) {
        switch (pos) {
            case 0:
                return context.getDrawable(R.drawable.login);
            case 1:
                return context.getDrawable(R.drawable.login);
            case 2:
                return context.getDrawable(R.drawable.paperwithcheck);
            case 3:
                return context.getDrawable(R.drawable.canned);
            case 4:
                return context.getDrawable(R.drawable.review);
            default:
                return context.getDrawable(R.drawable.canned);
        }
    }
    public String getTitle(int pos) {
        switch (pos) {
            case 0:
                return "Login";
            case 1:
                return "Register";
            case 2:
                return "Make Order";
            case 3:
                return "Reserve Order";
            case 4:
                return "Review Orders";
            default:
                return "";
        }
    }
}
