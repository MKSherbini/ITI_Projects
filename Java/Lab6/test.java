import streams.dao.*;
import java.util.*;
import java.util.stream.*;
import streams.*;

public class test {
    
	public static void main(String[] args) {
		InMemoryWorldDao worldDao =	InMemoryWorldDao.getInstance();
		Set<Map.Entry<String, Country>>  world = worldDao.getCountries().entrySet();
		Set<Map.Entry<Integer, City>>  cities = worldDao.getCities().entrySet();
		// world.stream().forEach( ( x ) ->  System.out.println(x.getValue().getName() ) );
		// var countryCodes = world.stream().map(x -> x.getValue().getCode() ).collect(Collectors.toList()) ;
		// countryCodes.stream().forEach( ( x ) ->  System.out.println(x) );
		Map<String, List<City>>  groups = cities.stream().map(x -> x.getValue() ).collect(Collectors.groupingBy( x -> x.getCountryCode() ));	
		// groups.entrySet().stream().forEach(( x ) ->  System.out.println(x) );
		groups.entrySet().stream().map( (x) ->  {       
			
			// x.setValue(  	x.getValue().stream() );
			// return new Map.Entry(x.getValue(), x.getKey() );
			return new AbstractMap.SimpleEntry<String, City>(x.getKey(),x.getValue().stream().max( Comparator.comparingInt( y -> y.getPopulation() )).get());
		} ).forEach(( x ) ->  System.out.println(x) );
		
	}
}
