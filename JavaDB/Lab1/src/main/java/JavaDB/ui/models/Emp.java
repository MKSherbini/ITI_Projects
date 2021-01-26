package JavaDB.ui.models;

import javafx.scene.control.Label;

public class Emp {
    String id;
    String fname;
    String mname;
    String lname;
    String email;
    String phone;

    public void fromLabel(Label id, Label fname, Label mname, Label lname, Label email, Label phone) {
        this.id = id.getText();
        this.fname = fname.getText();
        this.mname = mname.getText();
        this.lname = lname.getText();
        this.email = email.getText();
        this.phone = phone.getText();
    }

}
