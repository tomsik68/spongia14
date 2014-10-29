package sk.tomsik68.resourceslib;


import java.io.File;

abstract class ResourceProcessor {
    abstract boolean processes(File file);

    abstract RawResource process(File file, String key) throws Exception;
}
