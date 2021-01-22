package Lab2;

import java.io.File;
import java.net.URLConnection;

import org.apache.commons.io.FilenameUtils;

public class BrowserItemModel {
    enum TYPE {
        FILE,
        IMAGE,
        MUSIC,
        VIDEO,
        DIRECTORY,
    }

    public String m_name;
    public TYPE m_type;
    public File m_parent;

    /**
     * @param m_parent file parent
     * @param m_name   file name
     */
    public BrowserItemModel(File m_parent, String m_name) {
        this.m_name = m_name;
        this.m_parent = m_parent;

        var file = getFileDir();
        System.out.println(m_name + " is a file?: " + file.isFile());
        if (file.isFile()) { // File
            this.m_type = TYPE.FILE;
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType != null) {
                if (mimeType.startsWith("image")) {
                    this.m_type = TYPE.IMAGE;
                } else if (mimeType.startsWith("music")) {
                    this.m_type = TYPE.MUSIC;
                } else if (mimeType.startsWith("video")) {
                    this.m_type = TYPE.VIDEO;
                }
            }

        } else {
            this.m_type = TYPE.DIRECTORY;
        }

    }

    public File getFileDir() {
        return new File(FilenameUtils.concat(m_parent.getAbsolutePath(), m_name));
    }

    public BrowserItemModel(File m_name) {
        this.m_name = m_name.getName();
        this.m_type = TYPE.DIRECTORY;
        this.m_parent = m_name.getParentFile();
    }

    public String getIconCode() {
        switch (m_type) {
            case FILE:
                return "mdi2f-file";
            case IMAGE:
                return "mdi2f-file-image";
            case MUSIC:
                return "mdi2f-file-music";
            case VIDEO:
                return "mdi2f-file-video";
            case DIRECTORY:
                return "mdi2f-folder";
            default:
                return "mdi2f-folder-remove";
        }
    }

}
