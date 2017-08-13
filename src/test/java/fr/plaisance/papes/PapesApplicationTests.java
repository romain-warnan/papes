package fr.plaisance.papes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PapesApplicationTests {

	@Autowired
	private PapesRetriever retriever;

	@Autowired
	private PapeBuilder builder;

	@Test
	public void contextLoads() throws IOException {
		retriever.retrieve("http://www.histoirdefrance.fr/question/papes.htm");
		final List<String> lines = Files.readAllLines(Paths.get("papes.csv"), Charset.forName("UTF-8"));
		lines.forEach(line -> System.out.println(builder.build(line)));
	}

}
