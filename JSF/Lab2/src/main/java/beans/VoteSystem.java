package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Named
@ApplicationScoped
@Data
public class VoteSystem implements Serializable {
    private Map<String, Integer> categories;
//            = Map.of(
//            "Category1", 0,
//            "Category2", 0,
//            "Category3", 0,
//            "Category4", 0,
//            "Category5", 0
//    );

    {
        categories = new HashMap<>();
        categories.put("Category1", 0);
        categories.put("Category2", 0);
        categories.put("Category3", 0);
        categories.put("Category4", 0);
        categories.put("Category5", 0);
    }

    //
//    private Map<String, String> categoriesIndex = Map.of(
//            "0", "Category1",
//            "1", "Category2",
//            "2", "Category3",
//            "3", "Category4",
//            "4", "Category5"
//    );
    private int total;

    public void increment(String key) {
//        if (categoriesIndex.containsKey(key)) {
//            var cat = categoriesIndex.get(key);
        if (categories.containsKey(key)) {
            categories.put(key, categories.get(key) + 1);
            total++;
        }
//        }
    }


    public int getTotal() {
        return Math.max(1, total);
    }
}