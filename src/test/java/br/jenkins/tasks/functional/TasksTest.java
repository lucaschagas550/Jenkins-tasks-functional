package br.jenkins.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
//	@Test
//	public void testAmbiente() {
//		WebDriver driver = new ChromeDriver();
//		driver.navigate().to("http://www.google.com.br");
//	}
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	@Test
	public void deveSalavarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descri��o
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);

		//fechar o browser
		}
		finally{
			driver.quit();			
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();

		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);

		//fechar o browser
		}
		finally{
			driver.quit();			
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descri��o
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);

		//fechar o browser
		}
		finally{
			driver.quit();			
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descri��o
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);

		//fechar o browser
		}
		finally{
			driver.quit();			
		}
	}
}
