package models;

public class VCUrlMapping {
    private final String title;
    private final String controllerUrl;
    private final String viewUrl;
    private final boolean isViewPublic;

    public VCUrlMapping(String title, String controllerUrl, String viewUrl, boolean isViewPublic) {
        this.title = title;
        this.controllerUrl = controllerUrl;
        this.viewUrl = viewUrl;
        this.isViewPublic = isViewPublic;
    }

    public String getTitle() {
        return title;
    }

    public String getControllerUrl() {
        return controllerUrl;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public boolean isViewPublic() {
        return isViewPublic;
    }
}
