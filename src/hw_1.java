import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class hw_1 {

    public static void main(String[] args){
        HashMap<String, Integer> temp_user_data = new HashMap<String, Integer>();
        HashMap<String, Integer> user_data = new HashMap<String, Integer>();
        String f_name = args[0];
        try(BufferedReader input_flow = new BufferedReader(new FileReader(f_name))){
            String ln = input_flow.readLine();
                while(ln != null){
                    Integer user_time = Integer.parseInt(ln.split(" ")[0]);
                    String user_id = ln.split(" ")[1];
                    String user_event = ln.split(" ")[2];
                    if (user_event.equals("login")){
                        temp_user_data.put(user_id, user_time);
                    }
                    else{
                        Integer user_login_time =  temp_user_data.remove(user_id);
                        if (user_data.containsKey(user_id)){
                            user_data.put(user_id, user_data.get(user_id) + user_time - user_login_time);
                        }
                        else{
                            user_data.put(user_id, user_time - user_login_time);
                        }
                    }
                   ln = input_flow.readLine();

                }
            user_data = sortByValues(user_data);
            for (String name: user_data.keySet()){

                String key =name.toString();
                System.out.println(key + " " + convert_time(user_data.get(name)));


            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String convert_time(Integer time){
        String result = new String();
        Integer num_hours = time/3600;
        if (num_hours>0){
            result += (num_hours.toString() + " hours ");
        }
        time -= 3600*num_hours;
        Integer num_minutes = time/60;
        if (num_minutes>0){
            result += (num_minutes.toString() + " minutes ");
        }
        time -= 60*num_minutes;
        if (time>0){
            result += (time.toString() + " seconds ");
        }
        return result;

    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return -((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}

