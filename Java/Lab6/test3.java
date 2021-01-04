import streams.dao.*;
import java.util.*;
import java.util.stream.*;
import streams.*;

public class test3 {
    
	public static void main(String[] args) {
		InMemoryWorldDao worldDao =	InMemoryWorldDao.getInstance();
		Set<Map.Entry<String, Country>>  world = worldDao.getCountries().entrySet();
		Set<Map.Entry<Integer, City>>  cities = worldDao.getCities().entrySet();
		Map<String, Country> allCountries=worldDao.getCountries();
		Map<Integer, City> allCities=worldDao.getCities();
		
		var best = 
		world.stream().map( x -> allCities.get(x.getValue().getCapital()) )
								.filter( x -> x!=null)
								.max( Comparator.comparingInt( x -> x.getPopulation() ))
								 // .forEach(( x ) ->  System.out.println(x) );
								.get();
		
		System.out.println(best);
	}
}
