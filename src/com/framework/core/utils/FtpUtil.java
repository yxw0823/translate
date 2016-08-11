package com.framework.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.cnml.XMLException;
import org.dom4j.DocumentException;

public class FtpUtil
{
    private static Log log = LogFactory.getLog(FtpUtil.class);
    private FTPClient ftpClient = null;
    private String ip = "";
    private String username = "itvFtp";
    private String password = "itv";
    private int port = 21;
    private String localFileFullName = "";// 需要上传的目录

    // 枚举类UploadStatus代码
    public enum UploadStatus
    {
        Create_Directory_Fail, // 远程服务器相应目录创建失败
        Create_Directory_Success, // 远程服务器闯将目录成功
        Upload_New_File_Success, // 上传新文件成功
        Upload_New_File_Failed, // 上传新文件失败
        File_Exits, // 文件已经存在
        Remote_Bigger_Local, // 远程文件大于本地文件
        Upload_From_Break_Success, // 断点续传成功
        Upload_From_Break_Failed, // 断点续传失败
        Delete_Remote_Faild; // 删除远程文件失败
    }

    // 枚举类DownloadStatus代码
    public enum DownloadStatus
    {
        Remote_File_Noexist, // 远程文件不存在
        Local_Bigger_Remote, // 本地文件大于远程文件
        Download_From_Break_Success, // 断点下载文件成功
        Download_From_Break_Failed, // 断点下载文件失败
        Download_New_Success, // 全新下载文件成功
        Download_New_Failed; // 全新下载文件失败
    }

    public FtpUtil(String serverIP, int port, String username, String password)
    {
        this.ip = serverIP;
        this.username = username;
        this.password = password;
        this.port = port;
        this.loginFtp();
    }

    public boolean loginFtp()
    {
        boolean flag = true;
        if (ftpClient == null)
        {
            int reply;
            try
            {
                ftpClient = new FTPClient();
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.connect(ip);
                ftpClient.login(username, password);
                ftpClient.setDefaultPort(port);
                reply = ftpClient.getReplyCode();
                ftpClient.setDataTimeout(120000);
            }
            catch (SocketException e)
            {
                flag = false;
                e.printStackTrace();
                System.err.println("登录ftp服务器失败,连接超时！");
                log.debug("登录ftp服务器失败");
            }
            catch (IOException e)
            {
                flag = false;
                e.printStackTrace();
                System.err.println("登录ftp服务器失败，FTP服务器无法打开！");
                log.debug("登录ftp服务器失败");
            }
        }
        return flag;
    }

    public boolean logoutFtp()
    {
        try
        {
            if (ftpClient != null)
            {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public String getUploadPath(String path)
    {
        File source = new File(path);
        return source.getPath().substring(localFileFullName.length()).replace('\\', '/');
    }

    /**
     * 输出到文件
     * 
     * @param fileName
     * @param data
     * @throws Exception
     */
    public synchronized boolean upload(String fileName, String path, String name) throws Exception
    {
        // E:/Workspace_ALICE/.metadata/.plugins/org.eclipse.wst.server.core/tmp6/wtpwebapps/xhw/temp/logo_img_2014061220223926.jpg
        boolean flag = true;
        File source = new File(fileName);
        if (source.exists())
        {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            if (source.isDirectory())
            {
                if (!isDirExist(source.getPath().substring(localFileFullName.length()).replace('\\', '/')))
                {
                    createDir(source.getPath().substring(localFileFullName.length()).replace('\\', '/'));
                }
                File sourceFile[] = source.listFiles();
                for (int i = 0; i < sourceFile.length; i++)
                {
                    if (sourceFile[i].exists())
                    {
                        if (sourceFile[i].isDirectory())
                        {
                            this.upload(sourceFile[i].getCanonicalPath(), path, sourceFile[i].getName());
                        }
                        else
                        {
                            // 改变目录
                            this.changerToPath(sourceFile[i].getCanonicalPath());
                            flag = ftpClient.storeFile(name, new FileInputStream(sourceFile[i]));
                            log.debug("文件:" + sourceFile[i].getName() + "上传" + (flag == true ? "成功" : "失败"));
                            // 返回根目录
                            ftpClient.changeWorkingDirectory("~");
                        }
                    }
                }
            }
            else
            {
                try
                {
                    FileInputStream bis = new FileInputStream(source);
                    this.changerToPath(localFileFullName);
                    flag = ftpClient.storeFile(name, bis);
                    log.debug("文件:" + source.getName() + "上传" + (flag == true ? "成功" : "失败"));
                    ftpClient.changeWorkingDirectory("~");
                    bis.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    log.debug("本地文件上传失败！" + source.getCanonicalPath(), e);
                }
            }
        }
        return flag;
    }

    /**
     * Description: 从FTP服务器下载文件
     * 
     * @Version1.0
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public boolean downFile(String remotePath, String fileName, String localPath)
    {
        boolean result = false;
        try
        {
            int reply;
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes("UTF-8"), "iso-8859-1"));
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs)
            {
                if (ff.getName().equals(fileName))
                {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                }
            }
            ftpClient.logout();
            result = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ftpClient.isConnected())
            {
                try
                {
                    ftpClient.disconnect();
                }
                catch (IOException ioe)
                {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     * 
     * @Version1.0
     * @param remotePath FTP服务器上的相对路径
     * @param localPath 下载后保存到本地的路径
     * @return
     * @throws XMLException
     * @throws DocumentException
     */
    public Map<String, InputStream> downFiles(String remotePath, String path)
    {
        Map<String, InputStream> map = new HashMap<String, InputStream>();
        DownloadStatus result;
        FTPFile[] fs = null;
        List<InputStream> list = new ArrayList<InputStream>();
        try
        {
            int reply;
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            System.out.println("当前目录：" + ftpClient.printWorkingDirectory());
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes("UTF-8"), "iso-8859-1"));
            System.out.println("移动至：" + ftpClient.printWorkingDirectory());
            ftpClient.changeToParentDirectory();
            System.out.print(ftpClient.getReplyString());
            System.out.println("【FTPTools】:返回至上层目录");
            System.out.println("【FTPTools】:当前目录为" + ftpClient.printWorkingDirectory());
            // 获取文件列表
            ftpClient.listNames();
            fs = ftpClient.listFiles();
            ftpClient.listFiles(remotePath);
            for (FTPFile ff : fs)
            {
                if (ff.getName().contains(".xml"))
                {
                    InputStream is = ftpClient.retrieveFileStream(ff.getName());
                    map.put(ff.getName(), is);
                    boolean isDo = ftpClient.completePendingCommand();
                }
                else
                {
                    // 开启线程下载。
                    // 首先判读本地文件是否已经存在。存在就不在下载
                    // 下载文件到本地
                    File localFile = new File(path + "/" + ff.getName());
                    long lRemoteSize = ff.getSize();
                    // 本地存在文件，进行断点下载
                    if (localFile.exists())
                    {
                        long localSize = localFile.length();
                        // 判断本地文件大小是否大于远程文件大小
                        if (localSize >= lRemoteSize)
                        {
                            // System.out.println("本地文件大于远程文件，下载中止");
                            // 说明本地文件已经存在，删除服务器上的文件。
                            continue;
                        }
                        // 进行断点续传，并记录状态
                        FileOutputStream out = new FileOutputStream(localFile, true);
                        ftpClient.setRestartOffset(localSize);
                        InputStream in = ftpClient.retrieveFileStream(ff.getName());
                        byte[] bytes = new byte[1024];
                        long step = lRemoteSize / 100;
                        long process = localSize / step;
                        int c;
                        while ((c = in.read(bytes)) != -1)
                        {
                            out.write(bytes, 0, c);
                            localSize += c;
                            long nowProcess = localSize / step;
                            if (nowProcess > process)
                            {
                                process = nowProcess;
                                if (process % 10 == 0)
                                    System.out.println("下载进度：" + process);
                                // TODO 更新文件下载进度,值存放在process变量中
                            }
                        }
                        in.close();
                        out.close();
                        boolean isDo = ftpClient.completePendingCommand();
                        if (isDo)
                        {
                            result = DownloadStatus.Download_From_Break_Success;
                            // 删除文件
                        }
                        else
                        {
                            result = DownloadStatus.Download_From_Break_Failed;
                        }
                    }
                    else
                    {
                        OutputStream out = new FileOutputStream(localFile);
                        InputStream in = ftpClient.retrieveFileStream(ff.getName());
                        byte[] bytes = new byte[1024];
                        long step = lRemoteSize / 100;
                        long process = 0;
                        long localSize = 0L;
                        int c;
                        while ((c = in.read(bytes)) != -1)
                        {
                            out.write(bytes, 0, c);
                            localSize += c;
                            long nowProcess = localSize / step;
                            if (nowProcess > process)
                            {
                                process = nowProcess;
                                if (process % 10 == 0)
                                    System.out.println("下载进度：" + process);
                                // TODO 更新文件下载进度,值存放在process变量中
                            }
                        }
                        in.close();
                        out.close();
                        boolean upNewStatus = ftpClient.completePendingCommand();
                        if (upNewStatus)
                        {
                            result = DownloadStatus.Download_New_Success;
                            // 删除文件
                        }
                        else
                        {
                            result = DownloadStatus.Download_New_Failed;
                        }
                    }
                    /*
                     * StringBuffer sb = new StringBuffer(); InputStreamReader
                     * isr = new InputStreamReader(is, "UTF-8"); char buf[] =
                     * new char[20]; int nBufLen = isr.read(buf); while (nBufLen
                     * != -1) { sb.append(new String(buf, 0, nBufLen)); nBufLen
                     * = isr.read(buf); } // System.out.print(sb.toString());
                     */
                }
            }
        }
        catch (IOException e)
        {
            // e.printStackTrace();
        }
        finally
        {
        }
        return map;
    }

    /**
     * <删除FTP上的文件> <远程删除FTP服务器上的录音文件>
     * 
     * @param remotePath 远程文件路径
     * @param fileName 待删除的文件名
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean deleteFtpFile(String remotePath, String fileName)
    {
        boolean success = false;
        try
        {
            // 转移到FTP服务器目录
            ftpClient.changeWorkingDirectory(remotePath);
            success = ftpClient.deleteFile(remotePath + fileName);
            // ftpClient.logout();
        }
        catch (IOException e)
        {
            success = false;
        }
        finally
        {
            if (ftpClient.isConnected())
            {
                try
                {
                    ftpClient.disconnect();
                }
                catch (IOException e)
                {
                }
            }
        }
        return success;
    }

    public boolean isAlive()
    {
        boolean islive = ftpClient.isAvailable() && ftpClient.isConnected();
        return islive;
    }

    /**
     * 输入内容到指定目录下
     * 
     * @param fileName
     * @param data
     * @throws Exception
     */
    public synchronized boolean uploadContent(String fileName, String path, String content) throws Exception
    {
        boolean flag = true;
        try
        {
            this.changerToPath(path);
            flag = ftpClient.storeFile(fileName, new ByteArrayInputStream(content.getBytes("UTF-8")));
            log.debug("文件:" + fileName + "上传" + (flag == true ? "成功" : "失败"));
            System.out.println(path + "===>>" + fileName);
            ftpClient.changeWorkingDirectory("~");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.debug("本地文件上传失败！" + fileName, e);
        }
        return flag;
    }

    /**
     * 获取当前的FTP路径
     * 
     * @param path
     * @return
     */
    private boolean changerToPath(String path)
    {
        boolean isOK = false;
        StringTokenizer s = new StringTokenizer(path, "/");
        s.countTokens();
        String pathName = "";
        while (s.hasMoreElements())
        {
            pathName = (String) s.nextElement();
            try
            {
                isOK = ftpClient.changeWorkingDirectory(pathName);
            }
            catch (Exception e)
            {
                e = null;
                isOK = false;
            }
        }
        return isOK;
    }

    public static String inputStream2String(InputStream is) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1)
        {
            baos.write(i);
        }
        return new String(baos.toString().getBytes(), "UTF-8");
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        // FtpUtil packer = new FtpUtil("203.192.12.16", 21, "gxyd",
        // "G2x0y1d4@0414");
        FtpUtil packer = new FtpUtil("111.12.13.217", 21, "ftpusers", "FH123!");
        /*
         * packer.downFiles("/contents/"); String fileName =
         * "XxjckxC000001_20140315_BJTFN0.xml";
         * System.out.println(fileName.substring(0, 27));
         */
        // packer.downFile("/contents/", "XxjckxC000001_20140315_BJTFN0.xml",
        // "E:\\");
        // System.out.print(packer.getUploadPath("/home"));
        // packer.localFileFullName = "/operation/name/";
        // packer.upload("D://MCDZK_WS_D_APPINFO_20140413.json",
        // packer.localFileFullName);
        // System.out.println(packer.changerToPath("/operation/name/"));
    }

    /**
     * 创建文件夹
     * 
     * @param dir
     * @param ftpClient
     * @throws Exception
     */
    private void createDir(String dir) throws Exception
    {
        StringTokenizer s = new StringTokenizer(dir, "/");
        s.countTokens();
        String pathName = "";
        while (s.hasMoreElements())
        {
            pathName = (String) s.nextElement();
            try
            {
                ftpClient.makeDirectory(pathName);
                ftpClient.changeWorkingDirectory(pathName);
            }
            catch (Exception e)
            {
                e = null;
            }
        }
        ftpClient.changeWorkingDirectory("~");
    }

    /**
     * 检查文件夹是否存在
     * 
     * @param dir
     * @param ftpClient
     * @return
     */
    private boolean isDirExist(String dir)
    {
        boolean isDirExist = false;
        try
        {
            isDirExist = ftpClient.changeWorkingDirectory(dir);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return isDirExist;
    }

    public void setLocalFileFullName(String localFileFullName)
    {
        this.localFileFullName = localFileFullName;
    }

    /**
     * @return the localFileFullName
     */
    public String getLocalFileFullName()
    {
        return localFileFullName;
    }

    public List<String> getAllDirectories(String path) throws Exception
    {
        FTPFile[] allDirectories = ftpClient.listDirectories(path);
        List<String> directoryNames = new ArrayList<String>();
        for (FTPFile item : allDirectories)
        {
            directoryNames.add(item.getName());
        }
        return directoryNames;
    }

    public List<String> getAllChildFiles(String parentPath) throws Exception
    {
        FTPFile[] allFiles = ftpClient.listFiles(parentPath);
        List<String> fileNames = new ArrayList<String>();
        for (FTPFile item : allFiles)
        {
            fileNames.add(item.getName());
        }
        return fileNames;
    }

    public String readFtpFile(String filePath) throws Exception
    {
        InputStream ins = ftpClient.retrieveFileStream(filePath);
        return inputStreamToString(ins);
        // BufferedReader reader = new BufferedReader(new InputStreamReader(ins,
        // "UTF-8"));
        // StringBuilder sb = new StringBuilder();
        // String line = null;
        // try
        // {
        // while ((line = reader.readLine()) != null)
        // {
        // sb.append(line);
        // }
        // }
        // catch (IOException e)
        // {
        // e.printStackTrace();
        // }
        // finally
        // {
        // try
        // {
        // ins.close();
        // }
        // catch (IOException e)
        // {
        // e.printStackTrace();
        // }
        // }
        // return sb.toString();
    }

    private String inputStreamToString(InputStream is) throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1)
        {
            baos.write(i);
        }
        byte[] lens = baos.toByteArray();
        String result = new String(lens, "UTF-8");
        return result;
    }
}
