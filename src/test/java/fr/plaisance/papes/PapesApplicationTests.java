package fr.plaisance.papes;

import fr.plaisance.papes.data.Generator;
import fr.plaisance.papes.model.Pape;
import fr.plaisance.papes.service.PapesLoader;
import fr.plaisance.papes.service.PapesService;
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

	@Autowired
	private Generator generator;

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
		Assert.assertEquals(266, service.plusLongsPontificats(papes).entrySet().size());
	}

	@Test
	public void vacancesDuTrone() {
		Assert.assertEquals(266, service.vacancesDuTrone(papes).entrySet().size());
	}

	@Test
	public void papesParSaintete() {
		Assert.assertEquals(76, service.papesParSaintete(papes).get(Boolean.TRUE).longValue());
	}
}
