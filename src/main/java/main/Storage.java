/*package main;


import main.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage
{
    private static int currentId = 1;
    private static HashMap<Integer, User> users = new HashMap<>();

    public static List<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<>();
        list.addAll(users.values());
        return list;
    }
    public static User getUser(int id){
        if(users.containsKey(id)){
            return users.get(id);
        }
        return null;
    }

    public static void addUser(User user){
        users.put(currentId++, user);
    }
    public static void deleteUser(int id){
        users.remove(id);
    }
    public static void setUser(int id, String name){
        users.get(id).setName(name);
    }

}
*/
