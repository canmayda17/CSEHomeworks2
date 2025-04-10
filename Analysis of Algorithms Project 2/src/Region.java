import java.util.ArrayList;

public class Region {
	
	private float[] leftTopCornerPoint;
	private float[] leftDownCornerPoint;
	private float[] rightTopCornerPoint;
	private float[] rightDownCornerPoint;
	private int row;
	private int column;
	private int numberOfCities;
	private boolean isVisitedTemp;
	private boolean isVisited;
	private ArrayList<City> cities;
	
	public Region(float[] leftTopCornerPoint, float[] leftDowncornerpoint, float[] right_top_corner_point, 
			float[] right_down_corner_point, int row, int column) {
		
		this.row = row;
		this.column = column;
		this.leftTopCornerPoint = leftTopCornerPoint;
		this.leftDownCornerPoint = leftDowncornerpoint;
		this.rightTopCornerPoint = right_top_corner_point;
		this.rightDownCornerPoint = right_down_corner_point;
		this.numberOfCities = 0;
		this.isVisitedTemp = false;
		this.isVisited = false;
		this.cities = new ArrayList<City>();
	}
	
	public boolean addSuitableCity(City city) {
		boolean firstCheck = (city.getX() > this.leftDownCornerPoint[0]) && (city.getY() > this.leftDownCornerPoint[1]);
		boolean secondCheck = (city.getX() > this.leftTopCornerPoint[0]) && (city.getY() < this.leftTopCornerPoint[1]);
		boolean thirdCheck = (city.getX() < this.rightDownCornerPoint[0]) && (city.getY() > this.rightDownCornerPoint[1]);
		boolean fourthCheck = (city.getX() < this.rightTopCornerPoint[0]) && (city.getY() < this.rightTopCornerPoint[1]);
		boolean totalCheck1 = firstCheck && secondCheck && thirdCheck && fourthCheck;
		firstCheck = (city.getX() == this.leftDownCornerPoint[0]) || (city.getY() == this.leftDownCornerPoint[1]);
		secondCheck = (city.getX() == this.rightTopCornerPoint[0]) || (city.getY() == this.rightTopCornerPoint[1]);
		boolean totalCheck2 = firstCheck || secondCheck;
		if (totalCheck1 || totalCheck2) {
			this.cities.add(city);
			this.numberOfCities++;
			return true;
		}
		return false;
	}

	public float[] getLeftTopCornerPoint() {
		return leftTopCornerPoint;
	}

	public float[] getLeftDowncornerpoint() {
		return leftDownCornerPoint;
	}

	public float[] getRight_top_corner_point() {
		return rightTopCornerPoint;
	}

	public float[] getRight_down_corner_point() {
		return rightDownCornerPoint;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getNumberOfCities() {
		return numberOfCities;
	}

	public boolean isVisitedTemp() {
		return isVisitedTemp;
	}

	public void setIsVisitedTemp(boolean isVisitedTemp) {
		this.isVisitedTemp = isVisitedTemp;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setIsVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public ArrayList<City> getCities() {
		return cities;
	}
	
}
