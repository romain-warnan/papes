package fr.plaisance.papes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PapesApplicationTests {

	@Autowired
	private PapeLoader loader;

	@Test
	public void loadAll() throws IOException {
		List<Pape> papes = loader.loadAll();
		Assert.assertEquals(266, papes.size());
	}
}
