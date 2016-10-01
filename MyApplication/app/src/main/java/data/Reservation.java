package data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

public class Reservation implements Parcelable {
    private Integer Id= -1;
    private String DateTime = "";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private boolean isActive = false;
    private Integer fk_CharityID = -1;
    private int fk_OrderID = -1;

    public Reservation(Parcel in) {
        Id = in.readInt();
        DateTime = in.readString();
        isActive = in.readByte() != 0;
        fk_CharityID = in.readInt();
        fk_OrderID = in.readInt();
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    public Reservation() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(DateTime);
        parcel.writeByte((byte) (isActive ? 1 : 0));
        parcel.writeInt(fk_CharityID);
        parcel.writeInt(fk_OrderID);
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
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

    public Integer getFk_CharityID() {
        return fk_CharityID;
    }

    public void setFk_CharityID(Integer fk_CharityID) {
        this.fk_CharityID = fk_CharityID;
    }

    public int getFk_OrderID() {
        return fk_OrderID;
    }

    public void setFk_OrderID(int fk_OrderID) {
        this.fk_OrderID = fk_OrderID;
    }
}
