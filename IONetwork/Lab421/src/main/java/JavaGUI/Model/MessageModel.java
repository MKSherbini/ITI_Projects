package JavaGUI.Model;

import java.io.Serializable;
import java.util.Date;

public class MessageModel implements Serializable {

    enum SendStatus {
        SENDING,
        SENT,
        RECEIVED,
        READ
    }

    public UserModel m_from;
    public UserModel m_to;
    public String m_content;
    public Date m_date;
    public SendStatus m_sendStatus;

    public MessageModel(UserModel m_from, String m_content) {
        this.m_from = m_from;
        this.m_content = m_content;
        m_date = new Date();
        m_sendStatus = SendStatus.SENDING;
    }


    public MessageModel(UserModel m_from, UserModel m_to, String m_content, SendStatus m_sendStatus) {
        this.m_from = m_from;
        this.m_to = m_to;
        this.m_content = m_content;
        this.m_date = new Date();
        this.m_sendStatus = m_sendStatus;
    }
}
