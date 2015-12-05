package bg.uni_sofia.fmi.corejava.movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MoviesExplorer {

    public static void main(String[] args) throws Exception {
        // 1) Load the movies
        List<Movie> movies = new ArrayList<>();
        Stream<String> lines = Files.lines(Paths.get("resources","movies-mpaa.txt"));
        lines.forEach(line -> addMovie(movies,line));
        System.out.println("Number of movies: " + movies.size());
        
        // 2) Find the number of movies released in 2003
       
       long movies2003 =  movies.stream().filter(movie ->movie.getYear() == 2003).count();
       System.out.println("Movies in 2003: " + movies2003);
       // 3) Find the first movie that contains Lord of the Rings
       
       Optional<Movie> lotr = movies.stream().
    		   	filter(movie ->movie.getTitle().
    			contains("Lord of the Rings")).findFirst();
	   lotr.ifPresent(System.out::println);
       lotr.orElseThrow(IllegalArgumentException::new);
       
       // 4) Display the films sorted by the release year
      
       movies.stream().
       		sorted((movie1, movie2) -> movie1.getYear() - movie2.getYear()).forEach(System.out::println);
       	
       // 5) Find the first and the last year in the statistics
   
       OptionalInt min = getYearsStream(movies).min();
       System.out.println("The first year in the db is: " + min.getAsInt());
    
       OptionalInt max =  getYearsStream(movies).max();
       System.out.println("The first year in the db is: " + max.getAsInt());
     
     
       // 6) Print the movies grouped by year
       
       Map<Integer, List<Movie>> groupedMovies = movies.stream().
    		   collect(Collectors.groupingBy(Movie::getYear));
       System.out.println(groupedMovies);
     
       Map<Character, List<Movie>> groupedByCharacter = movies.stream()
       .collect(Collectors.groupingBy(movie -> new Character(movie.getTitle().charAt(0))));
       groupedByCharacter
       		.entrySet()
       		.stream()
       		.forEach(MoviesExplorer::printEntry);
       
       // 7) Extract all the actors

       Stream<Actor> actorsStream = movies.stream().flatMap(movie -> movie.getActors().stream());
       Set<Actor> newSet = actorsStream.collect(Collectors.toSet());
       System.out.println(newSet.size());
       
       // 8) Find all the movies with Kevin Spacey
       
       

    }



	private static void printEntry(Entry<Character, List<Movie>> entry) {
		System.out.println(entry.getKey());
    	entry.getValue().forEach(System.out::println);
	}

    

	private static IntStream getYearsStream(List<Movie> movies) {
		return movies.stream()
			  .mapToInt(Movie::getYear);
	}

    
    
    private static void addMovie(List<Movie> movies, String movieInfo) {
        String elements[] = movieInfo.split("/");
        String title = parseMovieTitle(elements);
        String releaseYear = parseMovieReleaseYear(elements);

        Movie movie = new Movie(title, Integer.valueOf(releaseYear));

        for (int i = 1; i < elements.length; i++) {
            String[] name = elements[i].split(", ");
            String lastName = name[0].trim();
            String firstName = "";
            if (name.length > 1) {
                firstName = name[1].trim();
            }

            Actor actor = new Actor(firstName, lastName);
            movie.addActor(actor);
        }

        movies.add(movie);
    }

    private static String parseMovieTitle(String[] elements) {
        return elements[0].substring(0, elements[0].toString().lastIndexOf("(")).trim();
    }

    private static String parseMovieReleaseYear(String[] elements) {
        String releaseYear = elements[0].substring(elements[0].toString().lastIndexOf("(") + 1,
                elements[0].toString().lastIndexOf(")"));
        if (releaseYear.contains(",")) {
            releaseYear = releaseYear.substring(0, releaseYear.indexOf(","));
        }
        return releaseYear;
    }
}
