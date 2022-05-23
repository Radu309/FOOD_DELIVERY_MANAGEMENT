package business.layer;

import data.layer.Serialization;
import javafx.beans.InvalidationListener;
import prezentation.layer.EmployeeObserver;

import java.io.*;
import java.util.*;

public class DeliveryService extends Observable {
    static List<BaseProduct> baseProductList = new ArrayList<>();
    static List<CompositeProduct> compositeProductsList = new ArrayList<>();
    static List<Order> orderList = new ArrayList<>();

    public List<BaseProduct> readDeliveryService(){
        baseProductList = new ArrayList<>();
        String PATH = "C:\\Downloads\\FACULTATE\\Tehnici de programare\\Assigment4\\products.csv";
        String line = "";
        HashSet<String> lines = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (lines.add(values[0])) {
                    BaseProduct baseProduct = new BaseProduct(values[0],
                            Float.parseFloat(values[1]), Integer.parseInt(values[2]),
                            Integer.parseInt(values[3]), Integer.parseInt(values[4]),
                            Integer.parseInt(values[5]), Integer.parseInt(values[6])
                    );
                    baseProductList.add(baseProduct);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseProductList;
    }
    public List<BaseProduct> addProductDeliveryService(List<BaseProduct> list, String title, Float rating, int calories, int proteins, int fat, int sodium, int price){
        BaseProduct baseProduct = new BaseProduct(title,rating,calories,proteins,fat,sodium,price);
        list.add(baseProduct);
        return list;
    }
    public List<BaseProduct> editProductDeliveryService(List<BaseProduct> list, String title, Float rating, int calories, int proteins, int fat, int sodium, int price){
        for(BaseProduct it: list){
            if(Objects.equals(it.getTitle(), title)) {
                it.setRating(rating);
                it.setCalories(calories);
                it.setProteins(proteins);
                it.setFat(fat);
                it.setSodium(sodium);
                it.setPrice(price);
            }
        }
        return list;
    }
    public List<BaseProduct> deleteProductDeliveryService(List<BaseProduct> list, String oldTitle){
        for(int it = 0 ; it < list.size() - 1; it++)
            if(Objects.equals(list.get(it).getTitle(), oldTitle))
                for(int i = it; i < list.size() - 1; i++)
                    list.set(i,list.get(i+1));
        list.remove(list.get(list.size() - 1));
        baseProductList = list;
        return list;
    }

    public void addProductInMenu(List<BaseProduct> list, String name){
        int n = list.size();
        CompositeProduct compositeProduct = new CompositeProduct(name, list, list.get(n-3).getTitle(),
                list.get(n-2).getTitle(), list.get(n-1).getTitle(),
                list.get(n-3).getPrice() + list.get(n-2).getPrice() + list.get(n-1).getPrice());
        compositeProductsList.add(compositeProduct);
    }
    public List<CompositeProduct> deleteMenuDeliveryService(List<CompositeProduct> list, String oldTitle){
        for(int it = 0 ; it < list.size() - 1; it++)
            if(Objects.equals(list.get(it).getName(), oldTitle))
                for(int i = it; i < list.size() - 1; i++)
                    list.set(i,list.get(i+1));
        list.remove(list.get(list.size() - 1));
        return list;
    }

    public List<BaseProduct> searchProductsFromClient(List<BaseProduct> list, String st1, String st2, String st3, String st4, String st5, String st6, String st7, String st8){
        List<BaseProduct> list1 = new ArrayList<>();
        for(BaseProduct it: list){
            if(it.getRating() >= Float.parseFloat(st1) && it.getRating() <= Float.parseFloat(st2) &&
               it.getCalories() >= Integer.parseInt(st3) && it.getCalories() <= Integer.parseInt(st4) &&
               it.getProteins() >= Integer.parseInt(st5) && it.getProteins() <= Integer.parseInt(st6) &&
               it.getPrice() >= Integer.parseInt(st7) && it.getPrice() <= Integer.parseInt(st8) ){
                list1.add(it);
            }
        }
        return list1;
    }
    //TODO
    public void notifyEmployee(String obj) {
        setChanged();
        notifyObservers(obj);
    }
    //serialization
    public void serialization(){
        Serialization serialization = new Serialization();
        serialization.serialization(baseProductList);

    }
    public void deserialization(){
        Serialization serialization = new Serialization();
        baseProductList = serialization.deSerialization();
        showBaseProductList(baseProductList);
    }
    //for testing
    public void showBaseProductList(List<BaseProduct> baseProductList){
        for(BaseProduct it: baseProductList){
            System.out.println(it.getTitle() + " " + it.getRating() + " " + it.getCalories() + " " + it.getProteins() + " " + it.getFat() + " " + it.getSodium() + " " + it.getPrice());
        }
    }
    public void showCompositeProductList(List<CompositeProduct> list){
        for(CompositeProduct it: list){
            System.out.println( it.getName() + " " +
                                it.getFirstProduct() + " " +
                                it.getSecondProduct() + " " +
                                it.getThirdProduct() + " " +
                                it.getTotalPrice());
        }
        System.out.println("HERE");
    }
    //
    public List<CompositeProduct> getCompositeProductsList() {return compositeProductsList;}
    public void setCompositeProductsList(List<CompositeProduct> compositeProductsList) {DeliveryService.compositeProductsList = compositeProductsList;}
    public List<BaseProduct> getBaseProductList() {return baseProductList;}
    public void setBaseProductList(List<BaseProduct> baseProductList) {DeliveryService.baseProductList = baseProductList;}

}
