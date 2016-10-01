package data;

import android.os.Parcel;
import android.os.Parcelable;

public class Charity implements Parcelable {
    private int Id = -1;
    private int fk_PlaceID = -1;
    private Place place = null;

    protected Charity(Parcel in) {
        Id = in.readInt();
        fk_PlaceID = in.readInt();
        place = in.readParcelable(Place.class.getClassLoader());
    }

    public static final Creator<Charity> CREATOR = new Creator<Charity>() {
        @Override
        public Charity createFromParcel(Parcel in) {
            return new Charity(in);
        }

        @Override
        public Charity[] newArray(int size) {
            return new Charity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeInt(fk_PlaceID);
        parcel.writeParcelable(place, i);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getFk_PlaceID() {
        return fk_PlaceID;
    }

    public void setFk_PlaceID(int fk_PlaceID) {
        this.fk_PlaceID = fk_PlaceID;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
