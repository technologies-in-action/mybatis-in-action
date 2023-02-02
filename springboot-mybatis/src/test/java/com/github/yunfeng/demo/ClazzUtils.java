package com.github.yunfeng.demo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class ClazzUtils {
    private static final String CLASS_SUFFIX = ".class";
    private static final String CLASS_FILE_PREFIX = File.separator + "classes" + File.separator;
    private static final String PACKAGE_SEPARATOR = ".";
    private static final String TEST_CLASS = "target/test-classes";
    /**
     * 查找包下的所有类的名字
     *
     * @param packageName 如com.github.yunfeng.demo
     * @return 类的全名集合
     */
    public static Set<String> getClazzName(String packageName) {
        Set<String> result = new HashSet<>();
        String suffixPath = packageName.replace(PACKAGE_SEPARATOR, "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> urls = loader.getResources(suffixPath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url == null) {
                    continue;
                }
                String protocol = url.getProtocol();
                String path = url.getPath();
                if ("file".equals(protocol) && !path.contains(TEST_CLASS)) {
                    result.addAll(getAllClassNameByFile(new File(path)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Set<String> getAllClassNameByFile(File file) {
        Set<String> result = new HashSet<>();
        if (!file.exists()) {
            return result;
        }
        if (file.isFile()) {
            addClassToResult(file, result);
            return result;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                result.addAll(getAllClassNameByFile(f));
            }
        }
        return result;
    }

    private static void addClassToResult(File file, Set<String> result) {
        String path = file.getPath();
        if (path.endsWith(CLASS_SUFFIX)) {
            path = path.replace(CLASS_SUFFIX, "");
            // 从"/classes/"后面开始截取
            int beginIndex = path.indexOf(CLASS_FILE_PREFIX) + CLASS_FILE_PREFIX.length();
            String clazzName = path.substring(beginIndex).replace(File.separator, PACKAGE_SEPARATOR);
            if (!clazzName.contains("$")) {
                result.add(clazzName);
            }
        }
    }
}