public class City {
	
	private int cityId;
	private int x;
	private int y;
	
	public City(int cityId, int x, int y) {
		this.cityId = cityId;
		this.x = x;
		this.y = y;
	}
	
	public int getId() {
		return this.cityId;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}