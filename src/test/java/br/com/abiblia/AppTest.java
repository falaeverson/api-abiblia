package br.com.abiblia;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.reflection.impl.PojoClassFactory;

import br.com.twsoftware.alfred.object.Objeto;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest extends TestCase {

	public static final String PACKAGE = "br.com.abiblia";

	public static final List<String> PACKAGE_EXCLUDES = Lists.newArrayList(
			"br.com.abiblia.config",
			"br.com.abiblia.controllers", 
			"br.com.abiblia.entidades", 
			"br.com.abiblia.exception",
			"br.com.abiblia.repositorios", 
			"br.com.abiblia.requests", 
			"br.com.abiblia.resources",
			"br.com.abiblia.cofig",
			"br.com.abiblia.responses");

	public static final String SUFIXO = "Test";

	private boolean packageValid(String name) {
		for (String p : PACKAGE_EXCLUDES) {
			if (name.contains(p)) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void classesSemTest() {

		System.out.println("Iniciando a Validação de Testes/Java");

		List<PojoClass> klassesJava = PojoClassFactory.getPojoClassesRecursively(PACKAGE, new PojoClassFilter() {
			@Override
			public boolean include(PojoClass pojoClass) {
				return packageValid(pojoClass.getPackage().getName())
						&& !pojoClass.getSourcePath().contains("/test-classes/") && !pojoClass.isEnum()
						&& !pojoClass.isNestedClass() && !pojoClass.isInterface() && !pojoClass.isAbstract()
						&& Objeto.isBlank(pojoClass.getAnnotation(Ignore.class));
			}
		});

		System.out.println("Total de Classes Java: " + klassesJava.size());

		List<PojoClass> klassesTest = PojoClassFactory.getPojoClassesRecursively(PACKAGE, new PojoClassFilter() {
			@Override
			public boolean include(PojoClass pojoClass) {
				return packageValid(pojoClass.getPackage().getName())
						&& pojoClass.getSourcePath().contains("/test-classes/") && !pojoClass.isEnum()
						&& !pojoClass.isNestedClass() && !pojoClass.isInterface();
			}
		});
		System.out.println("Total de Classes de Test: " + klassesTest.size());

		List<PojoClass> semTest = Lists.newArrayList();
		List<PojoClass> comTest = Lists.newArrayList();
		for (PojoClass pj : klassesJava) {

			List<PojoClass> result = klassesTest.stream().filter(k -> k.getName().equals(pj.getName() + SUFIXO))
					.collect(Collectors.toList());
			if (Objeto.isBlank(result)) {
				semTest.add(pj);
			} else {
				comTest.add(result.get(0));
			}

		}

		if (Objeto.notBlank(semTest)) {

			System.out.println(semTest.size()
					+ " classes não possui seu respectivo teste. Portanto, para prosseguir, é obrigatório que para cada classe Java voce implemente seu Teste seguindo o seguinte padrão:");

			for (PojoClass pj : semTest) {
				System.out.println(" \n " + pj.getName());
				System.out.println(" \n" + " package " 
						+ pj.getPackage().getName() + "; \n"
						+ " import org.springframework.boot.test.IntegrationTest; \n"
						+ " import org.springframework.test.context.web.WebAppConfiguration; \n"
						+ " import org.junit.runner.RunWith; \n"
						+ " import org.springframework.boot.test.SpringApplicationConfiguration; \n"
						+ " import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; \n"
						+ " import junit.framework.TestCase; \n\n" 
						+ " @RunWith(SpringRunner.class) \n"
						+ " @SpringBootTest \n"
						+ " @WebAppConfiguration \n" 
						+ " public class "
						+ pj.getClazz().getSimpleName() + "Test { \n" 
						+ "\n" 
						+ " } \n");
			}

		}

		List<PojoClass> semAnotacao = Lists.newArrayList();
		if (Objeto.notBlank(comTest)) {

			for (PojoClass pj : comTest) {

				if (!isAnnotatedOrParentAnnotated(pj, RunWith.class)
						&& !isAnnotatedOrParentAnnotated(pj, SpringBootTest.class)) {
					System.out.println("A classe " + pj.getName()
							+ " não está utilizando as anotações padrões para o projeto. Favor utilizar: "
							+ "@org.junit.runner.RunWith e "
							+ "@org.springframework.boot.test.SpringBootTest");
				}

			}

		}

		assertTrue(semAnotacao.isEmpty());
		assertTrue(semTest.isEmpty());

	}

	private static boolean isAnnotatedOrParentAnnotated(PojoClass pojoClass,
			Class<? extends Annotation> testAnnotation) {

		if (pojoClass == null)
			return false;

		if (pojoClass.getAnnotation(testAnnotation) != null)
			return true;

		for (PojoMethod pojoMethod : pojoClass.getPojoMethods()) {
			if (pojoMethod.getAnnotation(testAnnotation) != null)
				return true;
		}

		return (isAnnotatedOrParentAnnotated(pojoClass.getSuperClass(), testAnnotation));
	}

}
