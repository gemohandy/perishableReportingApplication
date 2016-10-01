package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ca.team5.perishablereportingapplication.R;

public class GridMenuAdapter extends BaseAdapter {
    private Context context = null;

    public GridMenuAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                break;
        }
        return null;
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

        return view;
    }
}
