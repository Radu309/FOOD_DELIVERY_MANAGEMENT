package data.layer;

import business.layer.BaseProduct;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization implements Serializable{
    public void serialization(List<BaseProduct> baseProductList) {
        try {
            String fileName = "serialization.txt";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            for(BaseProduct it: baseProductList)
                out.writeObject(it);
            //out.writeObject(baseProductList);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
        }catch(IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public List<BaseProduct> deSerialization() {
        List<BaseProduct> baseProductList = new ArrayList<>();
        String fileName = "serialization.txt";
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            baseProductList = (List<BaseProduct>)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been serialized");
        } catch(IOException ex) {
            System.out.println("IOException is caught");
        }catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
            e.printStackTrace();
        }
        return baseProductList;
    }


}
