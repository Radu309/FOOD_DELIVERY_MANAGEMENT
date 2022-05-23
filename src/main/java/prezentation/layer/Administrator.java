package prezentation.layer;

import business.layer.BaseProduct;
import business.layer.CompositeProduct;
import business.layer.DeliveryService;
import business.layer.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Administrator {
    //administrator first window
    @FXML private Button logOutButton;
    @FXML TextField menuProduct1TextField;
    @FXML TextField menuProduct2TextField;
    @FXML TextField menuProduct3TextField;
    @FXML TextField menuTitleTextField;
    //table view items
    @FXML TableColumn<BaseProduct, String> titleColumn;
    @FXML TableColumn<BaseProduct, Float> ratingColumn;
    @FXML TableColumn<BaseProduct, Integer> caloriesColumn;
    @FXML TableColumn<BaseProduct, Integer> proteinColumn;
    @FXML TableColumn<BaseProduct, Integer> fatColumn;
    @FXML TableColumn<BaseProduct, Integer> sodiumColumn;
    @FXML TableColumn<BaseProduct, Integer> priceColumn;
    @FXML TableView<BaseProduct> tableView = new TableView<>();
    //administrator second window MENU with composed items
    @FXML Button backProductInMenuButton;
    //table view composed items
    @FXML TableView<MenuItem> tableViewMenuItems = new TableView<>();
    @FXML TableColumn<MenuItem, String> titleMenuColumn;
    @FXML TableColumn<MenuItem, String> product1Column;
    @FXML TableColumn<MenuItem, String> product2Column;
    @FXML TableColumn<MenuItem, String> product3Column;
    @FXML TableColumn<MenuItem, Integer> priceMenuItemsColumn;
    //insert product window
    @FXML private TextField insertTitleProductTextField;
    @FXML private TextField insertRatingProductTextField;
    @FXML private TextField insertCaloriesProductTextField;
    @FXML private TextField insertProteinsProductTextField;
    @FXML private TextField insertFatProductTextField;
    @FXML private TextField insertSodiumProductTextField;
    @FXML private TextField insertPriceProductTextField;
    @FXML private Button insertProductWindowButton;
    //edit product window
    @FXML private TextField editTitleProductTextField;
    @FXML private TextField editRatingProductTextField;
    @FXML private TextField editCaloriesProductTextField;
    @FXML private TextField editProteinsProductTextField;
    @FXML private TextField editFatProductTextField;
    @FXML private TextField editSodiumProductTextField;
    @FXML private TextField editPriceProductTextField;
    @FXML private Button editProductWindowButton;
    // variable
    private static String nameMenu;

    static List<BaseProduct> productsMenuList = new ArrayList<>();     // list of products from menu
    static List<BaseProduct> baseProductList = new ArrayList<>();      // list of products
    public ObservableList<BaseProduct> getBaseProductList(){
        ObservableList<BaseProduct> list = FXCollections.observableArrayList();
        for(BaseProduct it: baseProductList){
            list.add(new BaseProduct(it.getTitle(),it.getRating(),it.getCalories(),it.getProteins(),it.getFat(),it.getSodium(),it.getPrice()));
        }
        return list;
    }

    static List<CompositeProduct> compositeProductsList = new ArrayList<>();
    public ObservableList<MenuItem> getCompositeProductList(){
        ObservableList<MenuItem> list = FXCollections.observableArrayList();
        for(CompositeProduct it: compositeProductsList){
            list.add(new CompositeProduct(it.getName(),it.getProducts(),
                    it.getFirstProduct(), it.getSecondProduct(), it.getThirdProduct(), it.getTotalPrice()));
        }
        return list;
    }
    //

    @FXML public void clickOnInsertProductsButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("InsertProductWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        Stage stage = new Stage();
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void clickOnEditProductsButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditProductWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        Stage stage = new Stage();
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML public void clickOnInsertProductsWindowButton() {
        String title = insertTitleProductTextField.getText();
        float rating = Float.parseFloat(insertRatingProductTextField.getText());
        int calories = Integer.parseInt(insertCaloriesProductTextField.getText());
        int proteins = Integer.parseInt(insertProteinsProductTextField.getText());
        int fat = Integer.parseInt(insertFatProductTextField.getText());
        int sodium = Integer.parseInt(insertSodiumProductTextField.getText());
        int price = Integer.parseInt(insertPriceProductTextField.getText());

        DeliveryService deliveryService = new DeliveryService();
        baseProductList = deliveryService.addProductDeliveryService(baseProductList,title,rating,calories,proteins,fat,sodium,price);
        tableView.setItems(getBaseProductList());

        Stage stage = (Stage) insertProductWindowButton.getScene().getWindow();
        stage.close();

    }
    @FXML public void clickOnEditProductsWindowButton()  {
        String title = editTitleProductTextField.getText();
        float rating = Float.parseFloat(editRatingProductTextField.getText());
        int calories = Integer.parseInt(editCaloriesProductTextField.getText());
        int proteins = Integer.parseInt(editProteinsProductTextField.getText());
        int fat = Integer.parseInt(editFatProductTextField.getText());
        int sodium = Integer.parseInt(editSodiumProductTextField.getText());
        int price = Integer.parseInt(editPriceProductTextField.getText());

        DeliveryService deliveryService = new DeliveryService();
        baseProductList = deliveryService.editProductDeliveryService(baseProductList,title,rating,calories,proteins,fat,sodium,price);
        tableView.setItems(getBaseProductList());

        Stage stage = (Stage) editProductWindowButton.getScene().getWindow();
        stage.close();
    }
    @FXML public void clickOnDeleteProductsButton() {
        BaseProduct baseProduct = tableView.getSelectionModel().getSelectedItem();
        DeliveryService deliveryService = new DeliveryService();
        baseProductList = deliveryService.deleteProductDeliveryService(baseProductList,baseProduct.getTitle());
        tableView.setItems(getBaseProductList());
    }
    @FXML public void clickOnAddProductInMenuButton(){
        nameMenu = menuTitleTextField.getText();
        BaseProduct baseProduct = tableView.getSelectionModel().getSelectedItem();
        if(Objects.equals(menuProduct1TextField.getText(), "")){
            menuProduct1TextField.setText(baseProduct.getTitle());
            productsMenuList.add(baseProduct);
        }
        else if(Objects.equals(menuProduct2TextField.getText(), "")){
            menuProduct2TextField.setText(baseProduct.getTitle());
            productsMenuList.add(baseProduct);
        }
        else if(Objects.equals(menuProduct3TextField.getText(), "")){
            menuProduct3TextField.setText(baseProduct.getTitle());
            productsMenuList.add(baseProduct);
            clickOnAddMenuButton();
        }
    }
    @FXML public void clickOnAddMenuButton(){
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.addProductInMenu(productsMenuList,nameMenu);
        menuTitleTextField.setText("");
        menuProduct1TextField.setText("");
        menuProduct2TextField.setText("");
        menuProduct3TextField.setText("");
    }

    @FXML public void clickOnImportProductsButton(){
        DeliveryService deliveryService = new DeliveryService();
        baseProductList = deliveryService.readDeliveryService();
        clickOnUpdateTableViewButton();
    }
    @FXML public void clickOnUpdateTableViewButton(){
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));
        sodiumColumn.setCellValueFactory(new PropertyValueFactory<>("sodium"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(getBaseProductList());
        tableView.refresh();
    }
    @FXML public void clickOnLogOutButton(){
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }
    //MENU
    @FXML public void clickOnViewMenuItemsButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuItemsWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        Stage stage = new Stage();
        stage.setTitle("Welcome!");

        stage.setScene(scene);
        stage.show();
    }
    @FXML public void clickOnBackAdministratorMenu(){
        Stage stage = (Stage) backProductInMenuButton.getScene().getWindow();
        stage.close();
    }
    @FXML public void clickOnDeleteMenuItemsButton(){
        MenuItem menuItem = tableViewMenuItems.getSelectionModel().getSelectedItem();
        DeliveryService deliveryService = new DeliveryService();
        compositeProductsList = deliveryService.deleteMenuDeliveryService(compositeProductsList,menuItem.getName());
        tableViewMenuItems.setItems(getCompositeProductList());
    }
    @FXML public void clickOnRefreshMenuWindow(){
        DeliveryService deliveryService = new DeliveryService();
        compositeProductsList = deliveryService.getCompositeProductsList();
        titleMenuColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        product1Column.setCellValueFactory(new PropertyValueFactory<>("firstProduct"));
        product2Column.setCellValueFactory(new PropertyValueFactory<>("secondProduct"));
        product3Column.setCellValueFactory(new PropertyValueFactory<>("thirdProduct"));
        priceMenuItemsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tableViewMenuItems.setItems(getCompositeProductList());
        tableViewMenuItems.refresh();
    }
}
