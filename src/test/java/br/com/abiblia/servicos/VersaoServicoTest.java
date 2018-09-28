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

import br.com.abiblia.entidades.Versao;
import br.com.abiblia.exception.NotFoundException;
import br.com.abiblia.repositorios.VersaoRepositorio;
import br.com.abiblia.requests.VersaoRequest;
import br.com.abiblia.responses.PageVersaoResponse;
import br.com.abiblia.responses.VersaoResponse;
import br.com.abiblia.util.EntityGenericUtil;
import br.com.abiblia.util.PageDefault;
import br.com.twsoftware.alfred.object.Objeto;

@RunWith(SpringRunner.class)
@SuppressWarnings("unchecked")
public class VersaoServicoTest {

	@InjectMocks
	private VersaoServico versaoServico;

	@Mock
	private VersaoRepositorio versaoRepositorio;

	private VersaoRequest versaoRequest;

	private Versao versao;

	private List<Versao> listVersao = Lists.newArrayList();

	private Page<Versao> pageVersao;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		for (int i = 0; i < 10; i++) {

			Versao versaoTmp = new Versao();
			
			versaoTmp.setId(EntityGenericUtil.getLong());
			versaoTmp.setNome(EntityGenericUtil.getString());
			versaoTmp.setAbreviacao(EntityGenericUtil.getString());

			listVersao.add(versaoTmp);

		}

		versao = listVersao.get(0);
		
		versaoRequest = new VersaoRequest();

		pageVersao = new PageImpl<>(listVersao, PageRequest.of(1, 10), 1);

		Mockito.when(versaoRepositorio.findAll(Mockito.any(Example.class), Mockito.any(PageDefault.class))).thenReturn(pageVersao);
		Mockito.when(versaoRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.of(versao));
		
	}

	@After
	public void After() {
	}

	@Test
	public void pageversaoSucesso() {

		PageVersaoResponse pageVersaoResponse = versaoServico.versoes(versaoRequest);

		assertNotNull(pageVersaoResponse);
		assertNotNull(pageVersaoResponse.getContent());
		
		if(Objeto.notBlank(pageVersaoResponse.getContent())) {
			
			VersaoResponse versaoResponse = pageVersaoResponse.getContent().get(0);
			
			assertEquals(versaoResponse.getId(), this.versao.getId());
			assertEquals(versaoResponse.getNome(), this.versao.getNome());
			assertEquals(versaoResponse.getAbreviacao(), this.versao.getAbreviacao());
			
		}

	}
	
	@Test
	public void pageVersaoVazio() {

		Page<Versao> pageVersaoVazio = new PageImpl<>(Lists.newArrayList(), PageRequest.of(0, 1), 0);
		
		Mockito.when(versaoRepositorio.findAll(Mockito.any(Example.class), Mockito.any(PageDefault.class))).thenReturn(pageVersaoVazio);

		PageVersaoResponse pageVersaoResponse = versaoServico.versoes(versaoRequest);

		assertNotNull(pageVersaoResponse);
		assertEquals(true, Objeto.isBlank(pageVersaoResponse.getContent()));
		
	}
	
	@Test
	public void consultaVarsaoSucesso() {

		VersaoResponse versaoResponse = versaoServico.versao(EntityGenericUtil.getLong());

		assertNotNull(versaoResponse);
		assertEquals(versaoResponse.getId(), this.versao.getId());
		assertEquals(versaoResponse.getNome(), this.versao.getNome());
		assertEquals(versaoResponse.getAbreviacao(), this.versao.getAbreviacao());

	}
	
	@Test(expected = NotFoundException.class)
	public void consultaVersaoNaoExiste() {

		Mockito.when(versaoRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		versaoServico.versao(EntityGenericUtil.getLong());

	}
}
