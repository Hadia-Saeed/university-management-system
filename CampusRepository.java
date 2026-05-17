import java.io.Serializable;
import java.util.ArrayList;

public class CampusRepository<T> implements Serializable {
    private ArrayList<T> list = new ArrayList<>();
    private static int totalRepositories = 0;

    public CampusRepository(){
        totalRepositories++;
    }

    public void additem(T item){
        if(item != null){
            list.add(item);
        }
        else{
            System.out.println("Item cannot be null.");
        }
    }

    public void removeitem(int index){
        if(index<0 || index >= list.size()){
            System.out.println("Invalid Index Entered.");
            return;
        }
        list.remove(index);
    }

    public ArrayList<T> getList(){
            return list;
    }

    public void DisplayAll(){
        for (T i: list) {
            System.out.println(i.toString());
        }
    }

}
