package br.com.abiblia.servicos;

import org.junit.After;

//import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//import br.com.conductor.pier.dto.Tenant;
//import br.com.conductor.pier.dto.requests.AplicacaoRequest;
//import br.com.conductor.pier.dto.responses.PageAplicacaoResponse;
//import br.com.conductor.pier.entidades.Aplicacao;
//import br.com.conductor.pier.entidades.Token;
//import br.com.conductor.pier.servicos.AplicacaoServico;
//import br.com.conductor.pier.util.EntityGenericUtil;
//import br.com.conductor.pier.util.PageablePIER;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LivroServicoTest {

	@Before
	public void before() {
		
		MockitoAnnotations.initMocks(this);

//        aplicacaoServicoMock = new AplicacaoServico(aplicacaoRepositorio, tokenRepositorio);
//
//        aplicacao = new Aplicacao();
//        aplicacao.setId(EntityGenericUtil.getLong());
//        aplicacao.setNome(EntityGenericUtil.getString());
//
//        Token token = new Token();
//        token.setId(EntityGenericUtil.getLong());
//        aplicacao.setTokenDefault(token);
//
//        aplicacaoPage = new PageImpl<>(Collections.singletonList(aplicacao), new PageRequest(1,1), 1);
	}

	@After
	public void After() {
		
	}
	
	@Test
	public void deveListarTodasAsAplicacoes() {

//    	Mockito.when(aplicacaoRepositorio.findAll(Mockito.any(Tenant.class), Mockito.any(Example.class), Mockito.any(PageablePIER.class))).thenReturn(aplicacaoPage);
//
//        PageAplicacaoResponse pageAplicacaoResponse = aplicacaoServicoMock.listar(new AplicacaoRequest());
//
//        assertNotNull(pageAplicacaoResponse);
//        assertEquals(pageAplicacaoResponse.getTotalElements(), aplicacaoPage.getTotalElements());
//        assertEquals(pageAplicacaoResponse.getContent().get(0).getId(), aplicacaoPage.getContent().get(0).getId());
//        assertEquals(pageAplicacaoResponse.getContent().get(0).getTokenId(), aplicacaoPage.getContent().get(0).getTokenDefault().getId());
//        assertEquals(pageAplicacaoResponse.getContent().get(0).getNome(), aplicacaoPage.getContent().get(0).getNome());
	}
}
