package web.pages.todoLy;

import web.controls.Button;
import org.openqa.selenium.By;

public class MenuSection {

    public Button logoutButton = new Button(By.xpath("//a[text()='Logout']"));

    public Button settigns = new Button(By.xpath("//a[@href='javascript:OpenSettingsDialog();']"));

}
