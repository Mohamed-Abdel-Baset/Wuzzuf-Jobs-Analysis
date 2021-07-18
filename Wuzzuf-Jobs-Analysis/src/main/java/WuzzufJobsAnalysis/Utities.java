package WuzzufJobsAnalysis;

import java.util.*;

public class Utities {
    //Sorting hashmap descendingly and return LinkedHashMap
    public static LinkedHashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static String getHtml(List<String> keys, List<Integer> values, String header, String colHeader){
        String output = "<html><head>\n" +
                "<style>\n" +
                "table {\n" +
                "  border-collapse: collapse;\n" +
                "  width: 25%;\n" +
                "}\n" +
                "\n" +
                "th, td {\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even){background-color: #f2f2f2}\n" +
                "\n" +
                "th {\n" +
                "  background-color: #04AA6D;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>\n" +
                "</head><body>";
        output += "<h1>"+header+"</h1><table>";
        output += "<tr><th>"+colHeader+"</th><th>Counts</th></tr>";
        for (int i=0; i<keys.size(); i++){
            output += "<tr><td>"+keys.get(i)+"</td><td>"+values.get(i)+"</td></tr>";
        }
        output += "</table></body></html>";
        return output;
    }
}
