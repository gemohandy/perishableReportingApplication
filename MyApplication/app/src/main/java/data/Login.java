package data;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {
    private Integer Id = -1;
    private String Username = "";
    private String Password = "";
    private String Name = "";
    private String Phone = "";
    private String Email = "";
    private Integer fk_CompanyID = -1;
    private Integer fk_CharityID = -1;

    public Login(Parcel in) {
        Id = in.readInt();
        Username = in.readString();
        Password = in.readString();
        Name = in.readString();
        Phone = in.readString();
        Email = in.readString();
        fk_CompanyID = in.readInt();
        fk_CharityID = in.readInt();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    public Login() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Username);
        parcel.writeString(Password);
        parcel.writeString(Name);
        parcel.writeString(Phone);
        parcel.writeString(Email);
        parcel.writeInt(fk_CompanyID);
        parcel.writeInt(fk_CharityID);
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public Integer getFk_CompanyID() {
        return fk_CompanyID;
    }

    public void setFk_CompanyID(Integer fk_CompanyID) {
        this.fk_CompanyID = fk_CompanyID;
    }

    public Integer getFk_CharityID() {
        return fk_CharityID;
    }

    public void setFk_CharityID(Integer fk_CharityID) {
        this.fk_CharityID = fk_CharityID;
    }
}
