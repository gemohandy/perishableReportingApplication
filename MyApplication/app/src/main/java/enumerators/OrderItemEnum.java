package enumerators;

import java.util.ArrayList;

import ca.team5.perishablereportingapplication.R;
import data.OrderItem;
import data.OrderItemPreset;

public enum OrderItemEnum {
    APPLES("Apples", R.drawable.apples),
    BANANAS("Bananas", R.drawable.banana),
    BREAD("Bread", R.drawable.bread),
    CHEESE("Cheese", R.drawable.cheese),
    EGGS("Eggs", R.drawable.eggs),
    HAMBURGER("Hamburger", R.drawable.hamburger),
    LETTUCE("Lettuce", R.drawable.lettuce),
    PEARS("Pears", R.drawable.pears);
    OrderItemEnum(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    String name;
    int resId;

    public static ArrayList<OrderItemPreset> getItems() {
        ArrayList<OrderItemPreset> items = new ArrayList<>();
        for (OrderItemEnum oi : OrderItemEnum.values()) {
            OrderItemPreset o = new OrderItemPreset();
            o.setName(oi.name);
            o.setResId(oi.resId);
            items.add(o);
        }
        return items;
    }
}
