package dev.craftid.dicodingjetpack.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentEntity implements Parcelable {

    public static int TYPE_MOVIE = 1111;
    public static int TYPE_TV_SHOW = 1112;

    private Integer contentType;
    private Integer idContent;
    private String voteAverage;
    private String title;
    private String name;
    private String posterPath;
    private String originalTitle;
    private String backdropPath;
    private String overview;
    private String releaseDate;
    private String firstAirDate;

    public ContentEntity() {
    }

    public ContentEntity(Integer contentType, Integer idContent, String voteAverage, String title, String name, String posterPath, String originalTitle, String backdropPath, String overview, String releaseDate, String firstAirDate) {
        this.contentType = contentType;
        this.idContent = idContent;
        this.voteAverage = voteAverage;
        this.title = title;
        this.name = name;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.firstAirDate = firstAirDate;
    }

    protected ContentEntity(Parcel in) {
        if (in.readByte() == 0) {
            contentType = null;
        } else {
            contentType = in.readInt();
        }
        if (in.readByte() == 0) {
            idContent = null;
        } else {
            idContent = in.readInt();
        }
        voteAverage = in.readString();
        title = in.readString();
        name = in.readString();
        posterPath = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        firstAirDate = in.readString();
    }

    public static final Creator<ContentEntity> CREATOR = new Creator<ContentEntity>() {
        @Override
        public ContentEntity createFromParcel(Parcel in) {
            return new ContentEntity(in);
        }

        @Override
        public ContentEntity[] newArray(int size) {
            return new ContentEntity[size];
        }
    };

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getIdContent() {
        return idContent;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (contentType == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(contentType);
        }
        if (idContent == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idContent);
        }
        parcel.writeString(voteAverage);
        parcel.writeString(title);
        parcel.writeString(name);
        parcel.writeString(posterPath);
        parcel.writeString(originalTitle);
        parcel.writeString(backdropPath);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(firstAirDate);
    }

    @Override
    public String toString() {
        return "ContentEntity{" +
                "contentType=" + contentType +
                ", idContent=" + idContent +
                ", voteAverage='" + voteAverage + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                '}';
    }
}
