package apps.younes.mvvm_with_roomdb.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NicePlace implements Parcelable {
    private String title;
    private String imageUrl;
    private String details;

    public NicePlace() {

    }

    public NicePlace(String title, String imageUrl, String details) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.details = details;
    }

    protected NicePlace(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        details = in.readString();
    }

    public static final Creator<NicePlace> CREATOR = new Creator<NicePlace>() {
        @Override
        public NicePlace createFromParcel(Parcel in) {
            return new NicePlace(in);
        }

        @Override
        public NicePlace[] newArray(int size) {
            return new NicePlace[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "NicePlace{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(details);
    }
}
