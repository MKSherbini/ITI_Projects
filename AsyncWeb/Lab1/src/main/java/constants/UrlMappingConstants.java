package constants;

import models.VCUrlMapping;
import constants.enums.PageNames;

import java.util.HashMap;
import java.util.Map;

public class UrlMappingConstants {
    private static final Map<PageNames, VCUrlMapping> urlMap = new HashMap<>();

    static {
        urlMap.put(PageNames.HOME_PAGE, new VCUrlMapping("Home", "home", "index.jsp", false));
        urlMap.put(PageNames.AJAX_FROM_SCRATCH, new VCUrlMapping("Ajax from scratch", "ajax", "ajax.jsp", false));
        urlMap.put(PageNames.CHAT, new VCUrlMapping("Best chat app", "ajax2", "ajax2.jsp", false));
        // ... add other pages
    }

    private static volatile UrlMappingConstants instance = null;

    private UrlMappingConstants() {
        if (instance != null)
            throw new RuntimeException("Use getInstance(), reflection is not allowed");
    }

    public static UrlMappingConstants getInstance() {
        if (instance == null) {
            synchronized (UrlMappingConstants.class) {
                if (instance == null) {
                    instance = new UrlMappingConstants();
                }
            }
        }
        return instance;
    }

    public String getTitle(PageNames page) {
        return urlMap.containsKey(page) ? urlMap.get(page).getTitle() : "Untitled pos";
    }


    public String getViewUrl(PageNames page) {
        return urlMap.containsKey(page) ? urlMap.get(page).getViewUrl() : null;
    }

    public String getControllerUrl(PageNames page) {
        return urlMap.containsKey(page) ? urlMap.get(page).getControllerUrl() : null;
    }

    /*
        to be a public jsp
        you either not exist at all in the list (to handle the resources)
        or you exist with a public jsp set to true (index.jsp for example)
        each pass one must be true
     */
    public boolean isJspPublic(String page) {
        return urlMap.entrySet().stream().allMatch(mapping -> (!mapping.getValue().getViewUrl().equals(page)) ||
                (mapping.getValue().getViewUrl().equals(page) && mapping.getValue().isViewPublic()));
    }
}
