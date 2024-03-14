public class City{
	String cityName;
	String regionName;
	
    public City(String cityName,String regionName){
		this.cityName=cityName;
		this.regionName=regionName;
	}
	
	public String toString(){
		return cityName+" - "+regionName;
	}
}
