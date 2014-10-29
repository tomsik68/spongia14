package sk.tomsik68.resourceslib;


import java.io.File;

class RawResource {
    protected final File location;

    RawResource(File file) {
        this.location = file;
    }

    File getFile() {
        return location;
    }

}
