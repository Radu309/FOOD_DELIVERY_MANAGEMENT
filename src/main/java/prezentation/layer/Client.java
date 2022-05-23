package prezentation.layer;

import business.layer.BaseProduct;
import business.layer.CompositeProduct;
import business.layer.DeliveryService;
import business.layer.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Client {
    @FXML private Button logOutButton;
    //table products
    @FXML TableView<BaseProduct> tableView = new TableView<>();
    @FXML TableColumn<BaseProduct, String> titleColumn;
    @FXML TableColumn<BaseProduct, Float> ratingColumn;
    @FXML TableColumn<BaseProduct, Integer> caloriesColumn;
    @FXML TableColumn<BaseProduct, Integer> proteinColumn;
    @FXML TableColumn<BaseProduct, Integer> fatColumn;
    @FXML TableColumn<BaseProduct, Integer> sodiumColumn;
    @FXML TableColumn<BaseProduct, Integer> priceColumn;
    //table composed items
    @FXML TableView<MenuItem> tableViewMenuItems = new TableView<>();
    @FXML TableColumn<MenuItem, String> titleMenuColumn;
    @FXML TableColumn<MenuItem, String> product1Column;
    @FXML TableColumn<MenuItem, String> product2Column;
    @FXML TableColumn<MenuItem, String> product3Column;
    @FXML TableColumn<MenuItem, Integer> priceMenuItemsColumn;
    //textFields for search product
    @FXML private TextField minimumRatingTextField;
    @FXML private TextField maximumRatingTextField;
    @FXML private TextField minimumCaloriesTextField;
    @FXML private TextField maximumCaloriesTextField;
    @FXML private TextField minimumProteinsTextField;
    @FXML private TextField maximumProteinsTextField;
    @FXML private TextField minimumPriceTextField;
    @FXML private TextField maximumPriceTextField;
    //for buying items
    @FXML TextArea nameOrderTextArea = new TextArea();
    @FXML TextArea priceOrderTextArea = new TextArea();
    @FXML TextField totalPriceTextField = new TextField();
    // variable
    static String nameOneOrder = "";
    static String nameOneOrderInLine = "";
    static String priceOneOrder = "";
    static int priceOrder;
    static String notification = "";

    static List<BaseProduct> baseProductList = new ArrayList<>();      // list of products
    public ObservableList<BaseProduct> getBaseProductList(){
        ObservableList<BaseProduct> list = FXCollections.observableArrayList();
        for(BaseProduct it: baseProductList){
            list.add(new BaseProduct(it.getTitle(),it.getRating(),it.getCalories(),it.getProteins(),it.getFat(),it.getSodium(),it.getPrice()));
        }
        return list;
    }

    static List<CompositeProduct> compositeProductsList = new ArrayList<>();    //list of composed products
    public ObservableList<MenuItem> getCompositeProductList(){
        ObservableList<MenuItem> list = FXCollections.observableArrayList();
        for(CompositeProduct it: compositeProductsList){
            list.add(new CompositeProduct(it.getName(),it.getProducts(),
                    it.getFirstProduct(), it.getSecondProduct(), it.getThirdProduct(), it.getTotalPrice()));
        }
        return list;
    }
    ////////////////////////////////////////////// CLIENT WINDOWS
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
    @FXML public void clickOnRefreshTwoTableViewButton(){
        DeliveryService deliveryService = new DeliveryService();
        baseProductList = deliveryService.getBaseProductList();
        clickOnRefreshMenuWindow();
        clickOnUpdateTableViewButton();
    }
    @FXML public void clickOnSearchButton(){
        String str1 = minimumRatingTextField.getText();
        String str3 = minimumCaloriesTextField.getText();
        String str5 = minimumProteinsTextField.getText();
        String str7 = minimumPriceTextField.getText();

        String str2 = maximumRatingTextField.getText();
        String str4 = maximumCaloriesTextField.getText();
        String str6 = maximumProteinsTextField.getText();
        String str8 = maximumPriceTextField.getText();
        List<BaseProduct> listAux = baseProductList;
        DeliveryService deliveryService = new DeliveryService();
        baseProductList = deliveryService.searchProductsFromClient(baseProductList,str1,str2,str3,str4,str5,str6,str7,str8);
        clickOnUpdateTableViewButton();
        baseProductList = listAux;
    }
    public void addToOrders(String name, String price, int totalPrice){
        nameOneOrder += name + "\n";
        nameOneOrderInLine += name + ";\t";
        priceOneOrder += price + "\n";
        priceOrder += totalPrice;
        nameOrderTextArea.setText(nameOneOrder);
        priceOrderTextArea.setText(priceOneOrder);
        totalPriceTextField.setText(Integer.toString(priceOrder));
    }
    @FXML public void clickOnBuyMenuButton(){
        CompositeProduct compositeProduct = (CompositeProduct) tableViewMenuItems.getSelectionModel().getSelectedItem();
        addToOrders(compositeProduct.getName(),Integer.toString(compositeProduct.getTotalPrice()),compositeProduct.getTotalPrice());
    }
    @FXML public void clickOnBuyProductButton(){
        BaseProduct baseProduct = tableView.getSelectionModel().getSelectedItem();
        addToOrders(baseProduct.getTitle(),Integer.toString(baseProduct.getPrice()),baseProduct.getPrice());
    }
    @FXML public void clickOnBuyAllButton(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notification += "New Order\nMenu: " + nameOneOrderInLine + "\nTotal Price: " + priceOrder + "\nDate: " +dtf.format(now) + "\n\n";
        //DeliveryService deliveryService = new DeliveryService();
        //deliveryService.notifyEmployee(notification);
        EmployeeObserver.notification = notification;
        priceOrder = 0;
        nameOneOrderInLine = "";
        nameOneOrder = "";
        priceOneOrder = "";
        nameOrderTextArea.setText(nameOneOrder);
        priceOrderTextArea.setText(priceOneOrder);
        totalPriceTextField.setText(Integer.toString(priceOrder));
    }
    @FXML public void clickOnLogOutButton(){
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }
}
