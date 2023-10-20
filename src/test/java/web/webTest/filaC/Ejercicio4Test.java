package web.webTest.filaC;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.singletonSession.Session;
import web.webTest.TestBase;
import java.util.Random;

public class Ejercicio4Test extends TestBase {
    private static Random rand = new Random();
    @BeforeAll
    public static void setup(){
        userRand = "ghost"+rand.nextInt(10000)+"@gmail.com";
    }
    static String userRand;
    @Test
    public void verifyEjercicio3Test() throws InterruptedException {
        mainPage.signUpButton.click();
        signUpPage.fullNameTextbox.setText(userRand);  //Cambio manual para cada nuevo testeo
        signUpPage.emailTextbox.setText(userRand); //Cambio manual para cada nuevo testeo
        signUpPage.passwordTextbox.setText("ghost123458");
        signUpPage.acceptTermsButton.click();
        signUpPage.signUpButton.click();
        Assertions.assertTrue(centralSection.openSettingsButton.isControlDisplayed(), "ERROR! El usuario no se creo!");

        menuSection.settigns.click();
        settingsSection.accountButton.click();
        settingsSection.deleteAccountButton.click();
        Session.getSession().getBrowser().switchTo().alert().accept();

        Thread.sleep(3000);
        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(), "ERROR! El usuario no fue eliminado");

    }
}
