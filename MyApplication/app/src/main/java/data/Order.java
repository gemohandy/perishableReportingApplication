package data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Order implements Parcelable {
    private int Id = -1;
    private String DateTime = "";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hhh-mm-ss");
    private boolean isActive = false;
    private int fk_CompanyID = -1;
    private ArrayList<OrderItem> items = new ArrayList<>();

    public Order(Parcel in) {
        Id = in.readInt();
        DateTime = in.readString();
        isActive = in.readByte() != 0;
        fk_CompanyID = in.readInt();
        in.readTypedList(items, OrderItem.CREATOR);
    }

    public Order() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(DateTime);
        dest.writeByte((byte) (isActive ? 1 : 0));
        dest.writeInt(fk_CompanyID);
        dest.writeTypedList(items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getFk_CompanyID() {
        return fk_CompanyID;
    }

    public void setFk_CompanyID(int fk_CompanyID) {
        this.fk_CompanyID = fk_CompanyID;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order put in at " + getDateTime();
    }
}
