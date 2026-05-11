
import java.util.ArrayList;

public class CampusRepository<T>  {
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
        if(index>list.size()){
            System.out.println("Invalid Index Entered.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if(i==index){
                list.remove(index);
                return;
            }
            System.out.println("Invalid index entered.");
        }
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
