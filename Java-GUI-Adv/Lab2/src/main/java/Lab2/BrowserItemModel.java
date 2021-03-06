package Lab2;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

public class BrowserItemModel {
    public boolean isFile() {
        return m_type != TYPE.DIRECTORY;
    }

    enum TYPE {
        FILE,
        IMAGE,
        MUSIC,
        VIDEO,
        DIRECTORY,
        APP,
    }

    public String m_name;
    public TYPE m_type;
    public File m_file;
    public File m_parent; // is self if root
    boolean m_isRoot;

    /**
     * @param m_parent file parent
     * @param m_name   file name
     */
    public BrowserItemModel(File m_parent, String m_name) {
        this.m_name = m_name;
        this.m_parent = m_parent;
        this.m_file = getFileDir();

        setExtensionDynamic(m_name);
    }

    public BrowserItemModel(File dirFile) {
        this.m_parent = dirFile.getParentFile();

        if (this.m_parent == null) {
            this.m_isRoot = true;
            try {
                this.m_name = Files.getFileStore(Paths.get(dirFile.toURI())).name();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.m_parent = dirFile;
            this.m_file = dirFile;
        } else {
            this.m_name = dirFile.getName();
            this.m_file = getFileDir();
        }
        setExtensionDynamic(m_name);
    }

    public File getFileDir() {
        if (!m_isRoot)
            return new File(FilenameUtils.concat(m_parent.getAbsolutePath(), m_name));
        return m_parent;
    }

    private void setExtensionDynamic(String m_name) {
        var file = getFileDir();
        System.out.println(m_name + " is a file?: " + file.isFile());
        if (file.isFile()) { // File
            this.m_type = TYPE.FILE;
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            System.out.println(m_name + " " + mimeType);
            if (mimeType != null) {
                if (mimeType.startsWith("image")) {
                    this.m_type = TYPE.IMAGE;
                } else if (mimeType.startsWith("audio")) {
                    this.m_type = TYPE.MUSIC;
                } else if (mimeType.startsWith("video")) {
                    this.m_type = TYPE.VIDEO;
                } else if (mimeType.startsWith("application")) {
                    this.m_type = TYPE.APP;
                }
            }
        } else {
            this.m_type = TYPE.DIRECTORY;
        }
    }


    public String getIconCode() {
        switch (m_type) {
            case FILE:
                return "mdi2f-file";
            case IMAGE:
                return "mdi2f-file-image";
            case MUSIC:
                return "mdi2f-file-music";
            case APP:
                return "mdi2a-application";
            case VIDEO:
                return "mdi2f-file-video";
            case DIRECTORY:
                return "mdi2f-folder";
            default:
                return "mdi2f-folder-remove";
        }
    }

    @Override
    public String toString() {
        return "BrowserItemModel{" +
                "m_name='" + m_name + '\'' +
                ", m_type=" + m_type +
                ", m_parent=" + m_parent +
                '}';
    }
//    @Override
//    public String toString() { // todo fix this shit
//        return "";
//    }
}
