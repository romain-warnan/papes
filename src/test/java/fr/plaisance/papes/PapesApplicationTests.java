package fr.plaisance.papes;

import org.junit.Assert;
import org.junit.Before;
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
	private PapesLoader loader;

	@Autowired
	private PapesService service;

	private List<Pape> papes;

	@Before
	public void before() throws IOException {
		this.papes = loader.loadAll();
	}

	@Test
	public void loadAll() {
		Assert.assertEquals(266, papes.size());
	}

	@Test
	public void papesParNationalite() {
		Assert.assertEquals(102, service.papesParNationalite(papes).get("Romain").longValue());
	}

	@Test
	public void papesParDureeDeRegne() {
		Assert.assertEquals(266, service.papesParDureeDeRegne(papes).entrySet().size());
	}

	@Test
	public void vacancesDuTrone() {
		//Assert.assertEquals(266, service.vacancesDuTrone(papes).entrySet().size());
		service.vacancesDuTrone(papes).forEach((k, v) -> System.out.println(k));
	}
}
