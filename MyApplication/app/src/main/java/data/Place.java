package data;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable{
    private int Id = -1;
    private String Name = "";
    private String Phone = "";
    private String Email = "";
    private String Address = "";
    private String City = "";
    private String Province = "";
    private String Country = "";


    public Place() {}

    protected Place(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
        Phone = in.readString();
        Email = in.readString();
        Address = in.readString();
        City = in.readString();
        Province = in.readString();
        Country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Name);
        dest.writeString(Phone);
        dest.writeString(Email);
        dest.writeString(Address);
        dest.writeString(City);
        dest.writeString(Province);
        dest.writeString(Country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
