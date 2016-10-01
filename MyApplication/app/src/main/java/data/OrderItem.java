package data;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {
    private Integer Id = -1;
    private String Name = "";
    private int Quantity = 0;
    private int fk_OrderID = -1;


    public OrderItem(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
        Quantity = in.readInt();
        fk_OrderID = in.readInt();
    }

    public OrderItem() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Name);
        dest.writeInt(Quantity);
        dest.writeInt(fk_OrderID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getFk_OrderID() {
        return fk_OrderID;
    }

    public void setFk_OrderID(int fk_OrderID) {
        this.fk_OrderID = fk_OrderID;
    }
}
