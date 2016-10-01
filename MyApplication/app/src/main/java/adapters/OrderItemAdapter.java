package adapters;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import ca.team5.perishablereportingapplication.R;
import data.OrderItemPreset;
import enumerators.OrderItemEnum;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemPresetHolder> {
    private Context context;
    private ArrayList<OrderItemPreset> presets = new ArrayList<>();
    private int selectedIndex = -1;
    private HorizontalGridView gridView;
    public OrderItemAdapter(Context context, HorizontalGridView gridView) {
        super();
        this.context = context;
        this.gridView = gridView;
        populatePresets();
    }

    private void populatePresets() {
        presets = OrderItemEnum.getItems();
    }

    @Override
    public OrderItemPresetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_grid_menu, parent, false);
        return new OrderItemPresetHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderItemPresetHolder holder, final int position) {
        holder.iv.setImageResource(presets.get(position).getResId());
        holder.tv.setText(presets.get(position).getName());
        if (selectedIndex == position) {
            holder.itemView.setAlpha(1f);
        } else {
            holder.itemView.setAlpha(.5f);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedIndex = position;
                notifyDataSetChanged();
                gridView.setSelectedPosition(selectedIndex);
            }
        });
    }
    @Override
    public int getItemCount() {
        return presets.size();
    }

    public OrderItemPreset getSelectedItem() {
        return presets.get(selectedIndex);
    }

    public void resetSelectedItem() {
        selectedIndex = -1;
    }

    public void setSelectedItem(int position) {
        selectedIndex = position;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public class OrderItemPresetHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv;

        public OrderItemPresetHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.igm_iv_feature);
            tv = (TextView)itemView.findViewById(R.id.igm_iv_title);
        }
    }
}
