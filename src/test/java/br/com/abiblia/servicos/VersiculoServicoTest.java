package br.com.abiblia.servicos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class VersiculoServicoTest {

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

	}

	@After
	public void After() {

	}

	@Test
	public void teste() {

	}
}
