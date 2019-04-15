package br.edu.testes.domain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

@RunWith(Arquillian.class)
public class PessoaServiceTest implements Serializable {

  private static final long serialVersionUID = 6581553331554423893L;
  private static final String CPF = "123.456.789-10";
  private static final String NOME = "Fulano de Tal";

  @EJB
  private PessoaService pessoaService;

  @Deployment
  public static WebArchive deploy() {
    final String domain = "br.edu.testes.domain";
    final String mockito = "org.mockito";
    final String objenesis = "org.objenesis";

    return ShrinkWrap.create(WebArchive.class).addPackages(true, domain, mockito, objenesis).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Before
  public void setup() throws IOException {
    Assert.assertNotNull("Ocorreu falha no processo de IoC/DI.", pessoaService);

    Pessoa pessoa = new Pessoa();
    pessoa.setCpf(CPF);
    pessoa.setNome(NOME);

    pessoaService = Mockito.mock(PessoaService.class);
    Mockito.when(pessoaService.buscar(CPF)).thenReturn(
      pessoa
    );
  }

  @Test
  public void testBuscarPorCpf() throws IllegalArgumentException, ParseException {
    Pessoa pessoa = pessoaService.buscar(CPF);
    Assert.assertNotNull(pessoa);
    Assert.assertEquals(CPF, pessoa.getCpf());
    Assert.assertEquals(NOME, pessoa.getNome());
  }

}
