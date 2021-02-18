package com.dock.tech.processoseletivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dock.tech.processoseletivo.ProcessoSeletivoApplication;

@SpringBootTest(classes=ProcessoSeletivoApplication.class)
class ProcessoSeletivoApplicationTests {
	
	@Resource
	private ProcessoSeletivoApplication controller;
	
	@Test
	public void testaHelloWorld() {
		String retorno = controller.home();
		assertEquals(retorno, "Hello Docker World!");
	}

}
