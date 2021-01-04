import streams.dao.*;
import java.util.*;
import java.util.stream.*;
import streams.*;

public class test2 {
    
	public static void main(String[] args) {
		InMemoryWorldDao worldDao =	InMemoryWorldDao.getInstance();
		Set<Map.Entry<String, Country>>  world = worldDao.getCountries().entrySet();
		Set<Map.Entry<Integer, City>>  cities = worldDao.getCities().entrySet();
		Map<String, Country> allCountries=worldDao.getCountries();
	// allCountries.get(x.getValue().getCountryCode()).getContinent()
		Map<String, List<City>>  groups = cities.stream().map(x -> x.getValue() ).collect(Collectors.groupingBy( x -> allCountries.get(x.getCountryCode()).getContinent() ));	
		groups.entrySet().stream().map( (x) ->  {       
			return new AbstractMap.SimpleEntry<String, City>(x.getKey(),x.getValue().stream().max( Comparator.comparingInt( y -> y.getPopulation() )).get());
		} ).forEach(( x ) ->  System.out.println(x) );
	}
}
