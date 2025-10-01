package steps;


import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CompraJogosSteps {

    public static WebDriver driver;

    @Before
    public void abrirNavegador() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.live.com/oauth20_authorize.srf?client_id=1f907974-e22b-4810-a9de-d9647380c97e&scope=xboxlive.signin+openid+profile+offline_access&redirect_uri=https%3a%2f%2fwww.xbox.com%2fauth%2fmsa%3faction%3dloggedIn%26locale_hint%3dpt-BR&response_type=code&state=eyJpZCI6IjAxOTk4NjVmLTZjZTctNzI3NC1hYmEyLTA5N2M3MDFiYjM0ZSIsIm1ldGEiOnsiaW50ZXJhY3Rpb25UeXBlIjoicmVkaXJlY3QifX0%3d%7chttps%253A%252F%252Fwww.xbox.com%252Fpt-BR%252Flive&response_mode=fragment&nonce=0199865f-6ce7-712f-97ab-a072fe236c24&prompt=select_account&code_challenge=oLj-VkB4F4CMJI02chNfgiSVUHpuV9Y7lSZs4zag60Y&code_challenge_method=S256&x-client-SKU=msal.js.browser&x-client-Ver=3.20.0&uaid=17d4a8edf69e4a8190c80b4f07efa00c&msproxy=1&issuer=mso&tenant=consumers&ui_locales=pt-BR&client_info=1&epct=PAQABDgEAAABVrSpeuWamRam2jAF1XRQEWRvbfZ45EjcQJ3j0cOOA1YozNUSPYjqs-FfJE7mxzSzeyH65zksybwQTfT4oU8qkhzqZunj_QCpUcT-XkidBw4b6gPMDLSMiQqAw4kh2hEIbUGflMBNCrUeftAIBw389OtiQOZM9EQ8fj4vCSyCS5v-2rOzrlLiffr3CwU0ISFozZqdVm9WTGib4V8Y70iNoye2NUl34aT15Zrq9qa57LiAA&jshs=0#");
    }
    @Dado("que estou acessando o site")
    public void que_estou_acessando_o_site() {

    }

    @Quando("eu informo usuario {string}")
    public void eu_informo_usuario(String usuario) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameEntry")));
        login.sendKeys(usuario);

        WebElement botaoavanca = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='primaryButton']")));
        botaoavanca.click();



    }

    @Quando("a senha {string}")
    public void a_senha(String senha) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            WebElement botaodasenha = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Use sua senha']")));
            botaodasenha.click();
        } catch (Exception ignored){

        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginsenha = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordEntry")));
        loginsenha.sendKeys(senha);

        WebElement botaoconfirmacao = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='primaryButton']")));
        botaoconfirmacao.click();
        WebElement segundaconfirmacao =wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='secondaryButton']")));
        segundaconfirmacao.click();

    }

    @Entao("devo ser redirecionado para pagina inicial")
    public void devo_ser_redirecionado_para_pagina_inicial() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("xbox.com"));
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("xbox.com"));

    }

    @Dado("que estou logado na minha conta")
    public void queEstouLogadoNaMinhaConta() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        WebElement FotodePerfil = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mectrl_headerPicture")));
        Assert.assertTrue(FotodePerfil.isDisplayed());


    }

    @Quando("eu pesquisar jogo {string}")
    public void euPesquisarJogo(String jogo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement barradepesquisa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        barradepesquisa.click();
        WebElement pesquisajogo = wait.until(ExpectedConditions.elementToBeClickable(By.id("cli_shellHeaderSearchInput")));
        pesquisajogo.sendKeys("Forza Horizon 5 Edição Padrão");
        pesquisajogo.submit();
    }

    @Entao("deve aparecer o jogo")
    public void deveAparecerOJogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement jogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@aria-label, 'Forza Horizon 5 Edição Padrão, R$249,00')]")));
            jogo.click();
    }

    @E("compro ele")
    public void comproEle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement aceitacookies = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Aceitar')]")));
        aceitacookies.click();
        WebElement compra = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@aria-label, 'Comprar Forza Horizon 5 Edição Padrão')]")));
        compra.click();
    }

    @E("clico em avanca")
    public void clicoEmAvanca() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement espera = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("store-cart-root")));
        WebElement avancar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avançar']")));
        avancar.click();

    }

    @Entao("vai parecer as formas de pagamento")
    public void vaiParecerAsFormasDePagamento() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement FormadePagamento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pidlddc-text-paymentMethodPMGroupingSelectHeading")));
        Assert.assertEquals("Escolha uma forma de pagamento", FormadePagamento.getText());
    }
}
