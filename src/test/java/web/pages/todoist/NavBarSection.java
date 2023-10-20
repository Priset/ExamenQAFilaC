package web.pages.todoist;

import org.openqa.selenium.By;
import web.controls.Button;
import web.controls.TextBox;

public class NavBarSection {

    public Button openInfoButton = new Button(By.id(":r0:"));
    public Button openSettingsButton = new Button(By.xpath("//span[contains(text(), 'Config')]"));

}
