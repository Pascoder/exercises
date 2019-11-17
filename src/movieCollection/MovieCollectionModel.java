package movieCollection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieCollectionModel {
	
	private static final String FILE_NAME = "output copy.txt";
	
	
	
	private final ObservableList<Movie> movies = FXCollections.observableArrayList();
	
	public MovieCollectionModel() {
		movies.addAll(loadFile());
		
	}

	private List<Movie> loadFile() {
		try (Stream<String> stream = getStreamOfLines (FILE_NAME, true)){
			return stream.map(m -> new Movie(m.split("!")))
					.collect(Collectors.toList());
		}
		
		
	}

	private Stream<String> getStreamOfLines(String fileName, boolean locatedInSameFolder) {
		try {
			return Files.lines(getPath(fileName, locatedInSameFolder), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
	}

	private Path getPath(String fileName, boolean locatedInSameFolder) {
		
		try {
			if(!locatedInSameFolder) fileName = "/" + fileName;
			return Paths.get(getClass().getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
		
	}
	public ObservableList<Movie> getResults() {
		return movies;
	}

	public String randomMovie() {
		Random rand = new Random();
		Movie mv = movies.get(rand.nextInt(movies.size()));
		return mv.getName() + " / " + mv.getYear() + " / " + mv.getRegisseur();
	}
}
