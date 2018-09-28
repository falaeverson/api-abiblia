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

import br.com.abiblia.entidades.Livro;
import br.com.abiblia.exception.NotFoundException;
import br.com.abiblia.repositorios.LivroRepositorio;
import br.com.abiblia.requests.LivroRequest;
import br.com.abiblia.responses.LivroResponse;
import br.com.abiblia.responses.PageLivroResponse;
import br.com.abiblia.util.EntityGenericUtil;
import br.com.abiblia.util.PageDefault;
import br.com.twsoftware.alfred.object.Objeto;

@RunWith(SpringRunner.class)
@SuppressWarnings("unchecked")
public class LivroServicoTest {

	@InjectMocks
	private LivroServico livroServico;

	@Mock
	private LivroRepositorio livroRepositorio;

	private LivroRequest livroRequest;

	private Livro livro;

	private List<Livro> listLivro = Lists.newArrayList();

	private Page<Livro> pageLivro;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		for (int i = 0; i < 10; i++) {

			Livro livroTmp = new Livro();
			
			livroTmp.setId(EntityGenericUtil.getLong());
			livroTmp.setIdTestamento(EntityGenericUtil.getLong());
			livroTmp.setPosicao(EntityGenericUtil.getLong());
			livroTmp.setNome(EntityGenericUtil.getString());
			livroTmp.setAbreviacao(EntityGenericUtil.getString());

			listLivro.add(livroTmp);

		}
		
		livro = listLivro.get(0);
		
		livroRequest = new LivroRequest();

		pageLivro = new PageImpl<>(listLivro, PageRequest.of(1, 10), 1);

		Mockito.when(livroRepositorio.findAll(Mockito.any(Example.class), Mockito.any(PageDefault.class))).thenReturn(pageLivro);
		Mockito.when(livroRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.of(livro));
		
	}

	@After
	public void After() {
	}

	@Test
	public void pageLivrosSucesso() {

		PageLivroResponse pageLivroResponse = livroServico.livros(livroRequest);

		assertNotNull(pageLivroResponse);
		assertNotNull(pageLivroResponse.getContent());
		
		if(Objeto.notBlank(pageLivroResponse.getContent())) {
			
			LivroResponse livroResponse = pageLivroResponse.getContent().get(0);
			
			assertEquals(livroResponse.getId(), this.livro.getId());
			assertEquals(livroResponse.getIdTestamento(), this.livro.getIdTestamento());
			assertEquals(livroResponse.getPosicao(), this.livro.getPosicao());
			assertEquals(livroResponse.getNome(), this.livro.getNome());
			assertEquals(livroResponse.getAbreviacao(), this.livro.getAbreviacao());
			
		}

	}
	
	@Test
	public void pageLivrosVazio() {

		Page<Livro> pageLivroVazio = new PageImpl<>(Lists.newArrayList(), PageRequest.of(0, 1), 0);
		
		Mockito.when(livroRepositorio.findAll(Mockito.any(Example.class), Mockito.any(PageDefault.class))).thenReturn(pageLivroVazio);

		PageLivroResponse pageLivroResponse = livroServico.livros(livroRequest);

		assertNotNull(pageLivroResponse);
		assertEquals(true, Objeto.isBlank(pageLivroResponse.getContent()));
		
	}
	
	@Test
	public void consultaLivrosSucesso() {

		LivroResponse livroResponse = livroServico.livro(EntityGenericUtil.getLong());

		assertNotNull(livroResponse);
		assertEquals(livroResponse.getId(), this.livro.getId());
		assertEquals(livroResponse.getIdTestamento(), this.livro.getIdTestamento());
		assertEquals(livroResponse.getPosicao(), this.livro.getPosicao());
		assertEquals(livroResponse.getNome(), this.livro.getNome());
		assertEquals(livroResponse.getAbreviacao(), this.livro.getAbreviacao());

	}
	
	@Test(expected = NotFoundException.class)
	public void consultaLivrosNaoExiste() {

		Mockito.when(livroRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		livroServico.livro(EntityGenericUtil.getLong());

	}
}
