package cn.movinginfo.tztf.common.domain;

public class GeoCoordinate {
	private static final long serialVersionUID = 1L;

    @Override
	public String toString() {
		return "GeoCoordinate [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	private double latitude;
    private double longitude;

    public GeoCoordinate() {
    }

    public GeoCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
