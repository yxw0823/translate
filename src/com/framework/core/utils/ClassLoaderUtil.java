package com.framework.core.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@author沈东良shendl_s@hotmail.com
 *Nov29,2006 10:34:34AM
 *用来加载类，classpath下的资源文件，属性文件等。
 *getExtendResource(StringrelativePath)方法，可以使用../符号来加载classpath外部的资源。
 */
@SuppressWarnings("unchecked")
public class ClassLoaderUtil {
    private static Logger log=LoggerFactory.getLogger(ClassLoaderUtil.class);
    /**
     *Thread.currentThread().getContextClassLoader().getResource("")
     */
   
    /**
     *加载Java类。 使用全限定类名
     *@paramclassName
     *@return
     */
    public static Class loadClass(String className) {
        try {
          return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
          throw new RuntimeException("class not found '"+className+"'", e);
        }
     }
     /**
       *得到类加载器
       *@return
       */
     public static ClassLoader getClassLoader() {
     
        return ClassLoaderUtil.class.getClassLoader();
     }
     /**
       *提供相对于classpath的资源路径，返回文件的输入流
       *@paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
       *@return 文件输入流
     *@throwsIOException
     *@throwsMalformedURLException
       */
     public static InputStream getStream(String relativePath) throws MalformedURLException, IOException {
         if(!relativePath.contains("../")){
             return getClassLoader().getResourceAsStream(relativePath);
         }else{
             return ClassLoaderUtil.getStreamByExtendResource(relativePath);
         }
     }
     /**
       *
       *@paramurl
       *@return
       *@throwsIOException
       */
     public static InputStream getStream(URL url) throws IOException{
         if(url!=null){
                return url.openStream();
         }else{
             return null;
         }
     }
     /**
       *
       *@paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
       *@return
       *@throwsMalformedURLException
       *@throwsIOException
       */
     public static InputStream getStreamByExtendResource(String relativePath) throws MalformedURLException, IOException{
        return ClassLoaderUtil.getStream(ClassLoaderUtil.getExtendResource(relativePath));
     }
     
      /**
       *提供相对于classpath的资源路径，返回属性对象，它是一个散列表
       *@paramresource
       *@return
       */
     public static Properties getProperties(String resource) {
        Properties properties = new Properties();
        try {
          properties.load(getStream(resource));
        } catch (IOException e) {
          throw new RuntimeException("couldn't load properties file '"+resource+"'", e);
        }
        return properties;
     }
     /**
       *得到本Class所在的ClassLoader的Classpat的绝对路径。
       *URL形式的
       *@return
       */
     public static String getAbsolutePathOfClassLoaderClassPath(){
         ClassLoaderUtil.log.info(ClassLoaderUtil.getClassLoader().getResource("").toString());
         return ClassLoaderUtil.getClassLoader().getResource("").toString();
     }
     /**
       *
       *@paramrelativePath 必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
       *@return资源的绝对URL
     *@throwsMalformedURLException
       */
     public static URL getExtendResource(String relativePath) throws MalformedURLException{
     
         ClassLoaderUtil.log.info("传入的相对路径："+relativePath) ;
         //ClassLoaderUtil.log.info(Integer.valueOf(relativePath.indexOf("../"))) ;
         if(!relativePath.contains("../")){
             return ClassLoaderUtil.getResource(relativePath);
             
         }
         String classPathAbsolutePath=ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath();
         if(relativePath.substring(0, 1).equals("/")){
             relativePath=relativePath.substring(1);
         }
         ClassLoaderUtil.log.info(""+Integer.valueOf(relativePath.lastIndexOf("../"))) ;
       
         String wildcardString=relativePath.substring(0,relativePath.lastIndexOf("../")+3);
        relativePath=relativePath.substring(relativePath.lastIndexOf("../")+3);
         int containSum=ClassLoaderUtil.containSum(wildcardString, "../");
         classPathAbsolutePath= ClassLoaderUtil.cutLastString(classPathAbsolutePath, "/", containSum);
         String resourceAbsolutePath=classPathAbsolutePath+relativePath;
         ClassLoaderUtil.log.info("绝对路径："+resourceAbsolutePath) ;
         URL resourceAbsoluteURL=new URL(resourceAbsolutePath);
         return resourceAbsoluteURL;
     }
     /**
      *
       *@paramsource
       *@paramdest
       *@return
       */
     private static int containSum(String source,String dest){
         int containSum=0;
         int destLength=dest.length();
         while(source.contains(dest)){
             containSum=containSum+1;
             source=source.substring(destLength);
             
         }
         
         
         return containSum;
     }
     /**
       *
       *@paramsource
       *@paramdest
       *@paramnum
       *@return
       */
     private static String cutLastString(String source,String dest,int num){
         // String cutSource=null;
         for(int i=0;i<num;i++){
             source=source.substring(0, source.lastIndexOf(dest, source.length()-2)+1);
         }
         return source;
     }
     /**
       *
       *@paramresource
       *@return
       */
      public static URL getResource(String resource){
      ClassLoaderUtil.log.info("传入的相对于classpath的路径："+resource) ;
         return ClassLoaderUtil.getClassLoader().getResource(resource);
     }
     //==================================================================================
      /*
       * 取得某一类所在包的所有类名 不含迭代
       */
      public static String[] getPackageAllClassName(String classLocation, String packageName){
          //将packageName分解
          String[] packagePathSplit = packageName.split("[.]");
          String realClassLocation = classLocation;
          int packageLength = packagePathSplit.length;
          for(int i = 0; i< packageLength; i++){
              realClassLocation = realClassLocation + File.separator+packagePathSplit[i];
          }
          File packeageDir = new File(realClassLocation);
          if(packeageDir.isDirectory()){
              String[] allClassName = packeageDir.list();
              return allClassName;
          }
          return null;
      }
      
      /**
       * 从包package中获取所有的Class
       * @param pack
       * @return
       */
      public static Set<Class<?>> getClasses(Package pack){
          
          //第一个class类的集合
          Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
          //是否循环迭代
          boolean recursive = true;
          //获取包的名字 并进行替换
          String packageName = pack.getName();
          String packageDirName = packageName.replace('.', '/');
          //定义一个枚举的集合 并进行循环来处理这个目录下的things
          Enumeration<URL> dirs;
          try {
              dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
              //循环迭代下去
              while (dirs.hasMoreElements()){
                  //获取下一个元素
                  URL url = dirs.nextElement();
                  //得到协议的名称
                  String protocol = url.getProtocol();
                  //如果是以文件的形式保存在服务器上
                  if ("file".equals(protocol)) {
                      //获取包的物理路径
                      String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                      //以文件的方式扫描整个包下的文件 并添加到集合中
                      findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                  } else if ("jar".equals(protocol)){
                      //如果是jar包文件 
                      //定义一个JarFile
                      JarFile jar;
                      try {
                          //获取jar
                          jar = ((java.net.JarURLConnection)url.openConnection()).getJarFile();
                          //从此jar包 得到一个枚举类
                          Enumeration<JarEntry> entries = jar.entries();
                          //同样的进行循环迭代
                          while (entries.hasMoreElements()) {
                              //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                              JarEntry entry = entries.nextElement();
                              String name = entry.getName();
                              //如果是以/开头的
                              if (name.charAt(0) == '/') {
                                  //获取后面的字符串
                                  name = name.substring(1);
                              }
                              //如果前半部分和定义的包名相同
                              if (name.startsWith(packageDirName)) {
                                  int idx = name.lastIndexOf('/');
                                  //如果以"/"结尾 是一个包
                                  if (idx != -1) {
                                      //获取包名 把"/"替换成"."
                                      packageName = name.substring(0, idx).replace('/', '.');
                                  }
                                  //如果可以迭代下去 并且是一个包
                                  if ((idx != -1) || recursive){
                                      //如果是一个.class文件 而且不是目录
                                      if (name.endsWith(".class") && !entry.isDirectory()) {
                                          //去掉后面的".class" 获取真正的类名
                                          String className = name.substring(packageName.length() + 1, name.length() - 6);
                                          try {
                                              //添加到classes
                                              classes.add(Class.forName(packageName + '.' + className));
                                          } catch (ClassNotFoundException e) {
                                              log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                                              e.printStackTrace();
                                          }
                                        }
                                  }
                              }
                          }
                      } catch (IOException e) {
                          log.error("在扫描用户定义视图时从jar包获取文件出错");
                          e.printStackTrace();
                      } 
                  }
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
         
          return classes;
      }
      
      /**
       * 以文件的形式来获取包下的所有Class
       * @param packageName
       * @param packagePath
       * @param recursive
       * @param classes
       */
      public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes){
          //获取此包的目录 建立一个File
          File dir = new File(packagePath);
          //如果不存在或者 也不是目录就直接返回
          if (!dir.exists() || !dir.isDirectory()) {
              log.warn("用户定义包名 " + packageName + " 下没有任何文件");
              return;
          }
          //如果存在 就获取包下的所有文件 包括目录
          File[] dirfiles = dir.listFiles(new FileFilter() {
          //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
                public boolean accept(File file) {
                  return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
                }
              });
          //循环所有文件
          for (File file : dirfiles) {
              //如果是目录 则继续扫描
              if (file.isDirectory()) {
                  findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                                        file.getAbsolutePath(),
                                        recursive,
                                        classes);
              }
              else {
                  //如果是java类文件 去掉后面的.class 只留下类名
                  String className = file.getName().substring(0, file.getName().length() - 6);
                  try {
                      //添加到集合中去
                      classes.add(Class.forName(packageName + '.' + className));
                  } catch (ClassNotFoundException e) {
                      log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                      e.printStackTrace();
                  }
              }
          }
      } 
      //====================================================================
    /**
     *@paramargs
     *@throwsMalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        //ClassLoaderUtil.getExtendResource("../spring/dao.xml");
        //ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
        ClassLoaderUtil.getExtendResource("logback.xml");
       
        System.out.println(ClassLoaderUtil.getClassLoader().getResource("logback.xml").toString());
 
    }
 
}
