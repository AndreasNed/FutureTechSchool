package Menu;

public class MenuOption {

    private MenuOptionInterface menu;
    private String string;

    public MenuOption(String string, MenuOptionInterface menu) {
        this.menu = menu;
        this.string = string;
    }

    public MenuOptionInterface getMenu() {
        return menu;
    }

    public void setMenu(MenuOptionInterface menu) {
        this.menu = menu;
    }

    public String getString() {
        return string;

    }

}
