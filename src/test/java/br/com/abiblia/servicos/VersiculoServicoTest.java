package br.com.abiblia.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.After;

//import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import br.com.abiblia.entidades.Versiculo;
import br.com.abiblia.exception.NotFoundException;
import br.com.abiblia.repositorios.VersiculoRepositorio;
import br.com.abiblia.requests.VersiculoRequest;
import br.com.abiblia.responses.PageVersiculoResponse;
import br.com.abiblia.responses.VersiculoResponse;
import br.com.abiblia.util.EntityGenericUtil;
import br.com.abiblia.util.PageDefault;
import br.com.twsoftware.alfred.object.Objeto;

@RunWith(SpringRunner.class)
@SuppressWarnings("unchecked")
public class VersiculoServicoTest {

	@InjectMocks
	private VersiculoServico versiculoServico;

	@Mock
	private VersiculoRepositorio versiculoRepositorio;

	private VersiculoRequest versiculoRequest;

	private Versiculo versiculo;

	private List<Versiculo> listVersiculo = Lists.newArrayList();

	private Page<Versiculo> pageVersiculo;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		for (int i = 0; i < 10; i++) {

			Versiculo versiculoTmp = new Versiculo();
			
			versiculoTmp.setId(EntityGenericUtil.getLong());
			versiculoTmp.setIdVersao(EntityGenericUtil.getLong());
			versiculoTmp.setIdTestamento(EntityGenericUtil.getLong());
			versiculoTmp.setIdLivro(EntityGenericUtil.getLong());
			versiculoTmp.setCapitulo(EntityGenericUtil.getInteger());
			versiculoTmp.setVersiculoNumero(EntityGenericUtil.getInteger());
			versiculoTmp.setVersiculoTexto(EntityGenericUtil.getString());

			listVersiculo.add(versiculoTmp);

		}

		versiculo = listVersiculo.get(0);
		
		versiculoRequest = new VersiculoRequest();

		pageVersiculo = new PageImpl<>(listVersiculo, PageRequest.of(1, 10), 1);

		Mockito.when(versiculoRepositorio.findAll(Mockito.any(Example.class), Mockito.any(PageDefault.class))).thenReturn(pageVersiculo);
		Mockito.when(versiculoRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.of(versiculo));
		
	}

	@After
	public void After() {
	}

	@Test
	public void pageVersiculosSucesso() {

		PageVersiculoResponse pageVersiculosResponse = versiculoServico.versiculos(versiculoRequest);

		assertNotNull(pageVersiculosResponse);
		assertNotNull(pageVersiculosResponse.getContent());
		
		if(Objeto.notBlank(pageVersiculosResponse.getContent())) {
			
			VersiculoResponse versiculoResponse = pageVersiculosResponse.getContent().get(0);
			
			assertEquals(versiculoResponse.getId(), this.versiculo.getId());
			assertEquals(versiculoResponse.getIdVersao(), this.versiculo.getIdVersao());
			assertEquals(versiculoResponse.getIdTestamento(), this.versiculo.getIdTestamento());
			assertEquals(versiculoResponse.getIdLivro(), this.versiculo.getIdLivro());
			assertEquals(versiculoResponse.getCapitulo(), this.versiculo.getCapitulo());
			assertEquals(versiculoResponse.getVersiculoNumero(), this.versiculo.getVersiculoNumero());
			assertEquals(versiculoResponse.getVersiculoTexto(), this.versiculo.getVersiculoTexto());
			
		}

	}
	
	@Test
	public void pageVersiculosVazio() {

		Page<Versiculo> pageLivroVazio = new PageImpl<>(Lists.newArrayList(), PageRequest.of(0, 1), 0);
		
		Mockito.when(versiculoRepositorio.findAll(Mockito.any(Example.class), Mockito.any(PageDefault.class))).thenReturn(pageLivroVazio);

		PageVersiculoResponse pageVersiculosResponse = versiculoServico.versiculos(versiculoRequest);

		assertNotNull(pageVersiculosResponse);
		assertEquals(true, Objeto.isBlank(pageVersiculosResponse.getContent()));
		
	}
	
	@Test
	public void consultaVersiculoSucesso() {

		VersiculoResponse versiculoResponse = versiculoServico.versiculo(EntityGenericUtil.getLong());

		assertNotNull(versiculoResponse);
		assertEquals(versiculoResponse.getId(), this.versiculo.getId());
		assertEquals(versiculoResponse.getIdVersao(), this.versiculo.getIdVersao());
		assertEquals(versiculoResponse.getIdTestamento(), this.versiculo.getIdTestamento());
		assertEquals(versiculoResponse.getIdLivro(), this.versiculo.getIdLivro());
		assertEquals(versiculoResponse.getCapitulo(), this.versiculo.getCapitulo());
		assertEquals(versiculoResponse.getVersiculoNumero(), this.versiculo.getVersiculoNumero());
		assertEquals(versiculoResponse.getVersiculoTexto(), this.versiculo.getVersiculoTexto());

	}
	
	@Test(expected = NotFoundException.class)
	public void consultaVersiculoNaoExiste() {

		Mockito.when(versiculoRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		versiculoServico.versiculo(EntityGenericUtil.getLong());

	}
}
