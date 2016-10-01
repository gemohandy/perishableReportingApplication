package data;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItemPreset implements Parcelable {
    private String Name;
    private int resId;

    public OrderItemPreset(Parcel in) {
        Name = in.readString();
        resId = in.readInt();
    }

    public static final Creator<OrderItemPreset> CREATOR = new Creator<OrderItemPreset>() {
        @Override
        public OrderItemPreset createFromParcel(Parcel in) {
            return new OrderItemPreset(in);
        }

        @Override
        public OrderItemPreset[] newArray(int size) {
            return new OrderItemPreset[size];
        }
    };

    public OrderItemPreset() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeInt(resId);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
