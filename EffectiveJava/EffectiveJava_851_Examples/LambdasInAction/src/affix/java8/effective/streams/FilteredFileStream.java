
package affix.java8.effective.streams;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FilteredFileStream {
    
	public static void main(String[] args) {

		System.out.println("FilteredFileStream");

		int lineCount = 0;
		int longCount = 0;
		String inputFile = "Java8Features.txt";

		Path testFilePath = Paths.get(".", new String[]{inputFile});

		// store all words from file in a collection
		List<String> wordList = new ArrayList<String>();

		try (Stream<String> java8Stream = Files.lines(testFilePath);) {

			Iterator<String> iter = java8Stream.iterator();
			while(iter.hasNext()){
				String line = iter.next();
				Stream<String> wordStream = wordList.stream();
				wordStream.forEach(System.out::println);
				lineCount++;

				String[] words = line.split(" ");
				wordList.addAll(Arrays.asList(words));
			}					
		} 
		catch (java.io.IOException ex) { 
			ex.printStackTrace();
		} 	

		// transform the collection into a stream 
		Stream<String> words = wordList.stream();
		// filter out the words that are at least 10 chars long to a new stream
		Stream<String> longWords = words.filter(w -> w.length() >= 10);

		// transform the contents(Strings) of the stream into lower case to a new stream
		Stream<String> lowerCaseWords = longWords.map(String::toLowerCase);
		longCount = (int)lowerCaseWords.count();

		System.out.println();            
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("There are " + lineCount + " lines in file " + inputFile);
		System.out.println("Number of long words (>=10 chars) " + longCount);

	}
}
