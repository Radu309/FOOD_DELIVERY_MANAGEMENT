package prezentation.layer;

import java.util.Observable;
import java.util.Observer;

public class EmployeeObserver implements Observer {
    public static String notification = "";
    @Override
    public void update(Observable o, Object result){
        String notificationString = (String)result;
        notification += "NEW ORDER\n" + notificationString;
    }
}
