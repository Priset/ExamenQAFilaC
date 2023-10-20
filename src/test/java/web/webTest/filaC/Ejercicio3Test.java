package web.webTest.filaC;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import web.webTest.TestBase2;

import java.util.Random;
public class Ejercicio3Test extends TestBase2 {
    private static Random rand = new Random();

    @Test
    public void createProject() throws InterruptedException{
        String nameUser = "Priset"+rand.nextInt(10000);
        mainTodoistPage.loginButton.click();
        loginPage.emailTextBox.setText("priset@gmail.com");
        loginPage.passwordTextBox.setText("priset1234");
        loginPage.loginButton.click();

        navBarSection.openInfoButton.click();
        navBarSection.openSettingsButton.click();
        configurationSection.nameProfileButton.click();
        configurationSection.nameProfileText.clearSetText(nameUser);
        configurationSection.actualizarButton.click();
        configurationSection.cerrarConfigButton.click();
        Thread.sleep(3000);

        navBarSection.openInfoButton.click();
        navBarSection.openSettingsButton.click();
        configurationSection.nameProfileButton.click();
        Assertions.assertEquals(configurationSection.nameProfileButton.getTextByAttribute("defaultValue"), nameUser, "ERROR! El usuario no se actualizo");
        Thread.sleep(3000);

    }
}
