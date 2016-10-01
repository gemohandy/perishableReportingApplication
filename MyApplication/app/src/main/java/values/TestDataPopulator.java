package values;

import java.util.ArrayList;
import java.util.Calendar;

import data.Order;
import data.OrderItem;
import data.Reservation;

public class TestDataPopulator {

    public static Order getMockOrders() {
        Order order = new Order();
        order.setDateTime(order.getSdf().format(Calendar.getInstance().getTime()));
        order.setActive(true);
        order.setFk_CompanyID(1);
        order.setId(1);
        OrderItem item = new OrderItem();
        item.setName("Apples");
        item.setQuantity(1);
        item.setId(1);
        OrderItem item2 = new OrderItem();
        item2.setName("Bananas");
        item2.setQuantity(1);
        item2.setId(2);
        order.getItems().add(item);
        order.getItems().add(item2);

        return order;
    }

    public static Reservation getMockReservation() {
        Reservation reservation = new Reservation();
        return reservation;
    }
}
