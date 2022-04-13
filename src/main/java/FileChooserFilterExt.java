class FileChooserFilterExt extends javax.swing.filechooser.FileFilter
{
    String extension;
    String description;

    FileChooserFilterExt(String extension, String descr)
    {
        this.extension = extension;
        this.description = descr;
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
            if (file.getName().contains(".meta")) {
                return false;
            }

            return file.getName().endsWith(extension);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }

}