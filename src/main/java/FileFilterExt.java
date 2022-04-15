public class FileFilterExt implements java.io.FileFilter {
    String extension;
    String description;
    boolean checkMeta;

    FileFilterExt(String extension, String descr, boolean checkMeta)
    {
        this.extension = extension;
        this.description = descr;
        this.checkMeta = checkMeta;
    }

    @Override
    public boolean accept(java.io.File file)
    {
        if(file != null) {
            if (file.isDirectory()) {
                return true;
            }
            if (extension == null) {
                return (extension.length() == 0);
            }
            if (checkMeta && file.getName().contains(".meta")) {
                return false;
            }

            return file.getName().endsWith(extension);
        }

        return false;
    }

}
