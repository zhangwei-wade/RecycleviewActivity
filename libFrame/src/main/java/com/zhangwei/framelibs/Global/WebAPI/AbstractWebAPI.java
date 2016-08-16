package com.zhangwei.framelibs.Global.WebAPI;

import android.graphics.Point;

import com.zhangwei.framelibs.Global.AbstractClass.AbstractGson;
import com.zhangwei.framelibs.Global.AbstractClass.BaseGlobal;
import com.zhangwei.framelibs.Global.Entity.ResultEntity;
import com.zhangwei.framelibs.Global.Other.Base64AndBitmap;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2014/5/25.
 * <p/>
 * 請求服务器的get、post方法
 * <p/>
 * 文件的下载  大文件的上传
 */
public abstract class AbstractWebAPI {
    private final String statusName = "status";//服务端返回过来的json属性值0（返回错误）和1（成功）
    private final String dataName = "data";//服务端返回过来的json属性值
    private final int connectionTimeOut = 10 * 1000;//连接超时
    private final int SoTimeout = 10 * 1000;//读取超时
    private final int OK = 200;
    public final String NAMESPACE = "scsLogin";
    private String sessionid;

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param html   发送请求的URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    protected void getUrl(String html, String params, APIResponse apiResponse) {
        String response_str = "";
        BufferedReader in = null;
        HttpURLConnection connection = null;
        String url;
        try {
            url = html + params;
            BaseGlobal.playLog(url);
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            setSession(connection);
            // 建立实际的连接
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(SoTimeout);
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            if (connection.getResponseCode() == OK) {
                while ((line = in.readLine()) != null) {
                    response_str += line;
                }
            } else {
                connectionFailure(apiResponse, BaseGlobal.NoService);
                response_str = "";
            }
            MainThreadExecute(response_str, apiResponse);
            getSessionID(connection);
        } catch (ConnectException e) {//网络连接失败
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
            response_str = "";
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.ConnectionTimeOut);
            response_str = "";
        } catch (UnknownHostException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
        } catch (IOException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.NoService);
            response_str = "";
        } finally {
            apiResponse.fetchFinish();
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BaseGlobal.playLog(response_str);
    }

    /**
     * 获取文件 比如图片
     */
    protected void fetchFile(final File file, final String url, final APIResponse apiResponse) {
        HttpURLConnection conn = null;
        InputStream is = null;
        RandomAccessFile randomAccessFile = null;
        int currentPercent;
        float current = 0;
        int len;
        try {
            BaseGlobal.playLog(url);
            String loadPath = file.getPath();
            loadPath = loadPath + BaseGlobal.MT;
            File loadFile = new File(loadPath);
            if (!loadFile.exists()) {
                loadFile.createNewFile();
            }
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("URIEncoding", "utf-8");
            conn.setRequestProperty("Accept-Encoding", "identity");
//            setSession(conn);
//            if (loadFile.exists() && loadFile.length() > 0) {//实现端点续传
//                conn.setRequestProperty("User-Agent", "Net");
//                // 设置续传开始
//                conn.setRequestProperty("Range", "bytes=" + loadFile.length() + "-");
//                current = loadFile.length();
//            }
//            conn.setDoInput(true); //允许输入流，即允许下载
//            conn.setDoOutput(true); //允许输出流，即允许上传
//            conn.setUseCaches(false); //不使用缓冲
            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(connectionTimeOut);
//            conn.setReadTimeout(SoTimeout);
            // 建立实际的连接
            conn.connect();
            randomAccessFile = new RandomAccessFile(loadPath, "rw");
            randomAccessFile.seek(loadFile.length());
            if (conn.getResponseCode() == OK || conn.getResponseCode() == 206) {
//                BaseGlobal.playLog("fileSize:" + fileSize + "");
                is = conn.getInputStream();
                byte[] bytes = new byte[5 * 1024];
                float fileSize = conn.getContentLength() + current;
//                if (fileSize > 0 && is != null) {
                while ((len = is.read(bytes)) != -1) {
                    randomAccessFile.write(bytes, 0, len);
                    current += len;
                    currentPercent = (int) ((current / fileSize) * 100.0);
                    apiResponse.fetchProgressLoading(currentPercent, 100);
//                    }
                }
                BaseGlobal.fileReName(loadFile, file);//文件重命名
                apiResponse.fetchSuccess(BaseGlobal.Success);//文件下载成功
            } else {
                connectionFailure(apiResponse, BaseGlobal.NoService);
            }
            getSessionID(conn);
        } catch (ConnectException e) {//网络连接超时
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.ConnectionTimeOut);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            connectionFailure(apiResponse, BaseGlobal.NoService);
        } finally {
            BaseGlobal.removeDownFileProgress(url);//下载完后移除文件对应的APIResponse
            try {
                if (is != null)
                    is.close();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (conn != null)
                    conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            apiResponse.fetchFinish();
        }
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param html   发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    protected void postUrl(String html, String params, APIResponse apiResponse) {
        PrintWriter out = null;
        BufferedReader in = null;
        HttpURLConnection conn = null;
        String response_str = "";
        try {
            BaseGlobal.playLog(html + "   " + params);
            URL realUrl = new URL(html);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(connectionTimeOut);
            conn.setReadTimeout(SoTimeout);
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "kSOAP/2.0");
            conn.setRequestProperty("SOAPAction", html);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            setSession(conn);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            if (conn.getResponseCode() == OK) {
                while ((line = in.readLine()) != null) {
                    response_str += line;
                }
            } else {
                connectionFailure(apiResponse, BaseGlobal.NoService);
                response_str = "";
            }
            getSessionID(conn);
            MainThreadExecute(response_str, apiResponse);
        } catch (ConnectException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.ConnectionTimeOut);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
        } catch (IOException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.NoService);
        } finally {
            try {
                if (conn != null)
                    conn.disconnect();
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            BaseGlobal.removeDownFileProgress(html);
            apiResponse.fetchFinish();
        }
        BaseGlobal.playLog(response_str);
    }

    private void setSession(HttpURLConnection conn) {
        if (sessionid != null) {
            conn.setRequestProperty("Cookie", sessionid);
        }
    }

    private void getSessionID(HttpURLConnection con) {
        // 取得sessionid.
//        if (!BaseGlobal.isEmpty(sessionid))
//            return;
        try {
            String cookieval = con.getHeaderField("set-cookie");
            if (cookieval != null) {
                sessionid = cookieval.substring(0, cookieval.indexOf(";"));
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param urlPath
     * @param params     map 参数 <参数名称 , 参数值>
     * @param fileParams map 文件类型 参数 <参数名称 , 文件路径>
     */

    public void postFile(String urlPath, Map<String, String> params,
                         Map<String, String> fileParams, APIResponse apiResponse, Point mPoint) throws FileNotFoundException {
        String PREFIX = "--"; // 前缀
        String LINE_END = "\r\n"; // 换行
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识
        URL url;
        HttpURLConnection connection = null;
        String response_str = null;
        try {
            url = new URL(fetchFullUrl(urlPath, params));
            BaseGlobal.playLog(fetchFullUrl(urlPath, params));
            connection = (HttpURLConnection) url.openConnection();
            // 设置超时时间
            connection.setReadTimeout(SoTimeout);
            connection.setConnectTimeout(connectionTimeOut);
            // 请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            // 设置编码
            connection.setRequestProperty("Charset", "utf-8");
            connection.setRequestProperty("connection", "keep-alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 设置内容类型及定义BOUNDARY
            connection.setRequestProperty("Content-Type", "multipart/form-data"
                    + ";boundary=" + BOUNDARY);
            setSession(connection);
            // 开启输入流
            connection.setDoInput(true);
            // 开启输出流
            connection.setDoOutput(true);
            // 关闭缓存
            connection.setUseCaches(false);
            connection.setChunkedStreamingMode(1024);

            // 获取输出流
            DataOutputStream dos = new DataOutputStream(
                    connection.getOutputStream());
            StringBuffer sb;
            dos.flush();

            // 发送文件参数，读取文件流写入post输出流
            if (fileParams != null && !fileParams.isEmpty()) {
                Iterator<Map.Entry<String, String>> fileIter = fileParams
                        .entrySet().iterator();
                while (fileIter.hasNext()) {
                    sb = new StringBuffer();
                    Map.Entry<String, String> entry = fileIter.next();
                    String fileKey = entry.getKey();
                    String filePath = entry.getValue();
                    File file = new File(filePath);
                    if (file.exists() == false) {
                        throw new FileNotFoundException();
                    }
                    // 设置边界标示，设置 Content-Disposition头传入文件流

                    sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                    sb.append("Content-Disposition:form-data; name=\""
                            + fileKey + "\"; filename=\"" + file.getName()
                            + "\"" + LINE_END);
                    sb.append("Content-Type:" + "application/json" + LINE_END);
                    sb.append(LINE_END);
                    dos.write(sb.toString().getBytes());
                    /*如果是图片就一次性上传*/
                    byte[] bytes = Base64AndBitmap.getPathToByte(filePath, 1, mPoint);
                    if (mPoint != null && bytes != null) {
                        dos.write(bytes);
                    } else {
                        bytes = new byte[1024];
                        InputStream is = new FileInputStream(file);
                        int len;
                        while ((len = is.read(bytes)) != -1) {
                            dos.write(bytes, 0, len);
                        }
                        is.close();
                        dos.flush();
                    }
                    dos.write(LINE_END.getBytes());
                    dos.flush();
                }
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();
            }
            dos.close();
            int res = connection.getResponseCode();
            // 返回成功
            if (res == OK) {
                InputStream input = connection.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                response_str = sb1.toString();
                input.close();
            } else {
                connectionFailure(apiResponse, BaseGlobal.NoService);
                response_str = "";
            }
            MainThreadExecute(response_str, apiResponse);
            getSessionID(connection);
        } catch (ConnectException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.InternetConnectionFailure);
            response_str = "";
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.ConnectionTimeOut);
            response_str = "";
        } catch (Exception e) {
            e.printStackTrace();
            connectionFailure(apiResponse, BaseGlobal.NoService);
            response_str = "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            //下载完后移除文件对应的APIResponse
            BaseGlobal.removeDownFileProgress(fetchFullUrl(urlPath, params));
            apiResponse.fetchFinish();
        }
        BaseGlobal.playLog(response_str);
    }


    private void MainThreadExecute(String response_str, APIResponse apiResponse) {
        try {
            if (apiResponse != null) {
                if (!BaseGlobal.isEmpty(response_str)) {
                    ResultEntity result = new AbstractGson<ResultEntity>() {
                    }.fromJson(response_str);
                    if (result.getECode() == 0) {
                        if (result.getData() != null
                                && result.getData().toString().length() >= 0) {
                            String json = new AbstractGson() {
                            }.toJson(result.getData());
                            if (BaseGlobal.isEmpty(json))
                                apiResponse.fetchSuccess(result.getData().toString());
                            else {
                                apiResponse.fetchSuccess(json);
                            }
                        } else
                            apiResponse.fetchSuccess("");
                    } else {
                        apiResponse.fetchFailure(result.getEDesc());
                    }
                } else {
                    connectionFailure(apiResponse, response_str);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            connectionFailure(apiResponse, e.getMessage());
        } catch (Exception e) {
            connectionFailure(apiResponse, e.getMessage());
        }
    }


    private void connectionFailure(APIResponse apiResponse, String error) {
        if (apiResponse != null) {
            apiResponse.fetchFailure(error);
        }
    }

    public String fetchFullUrl(String url, Map<String, String> map) {
        if (map == null)
            return url + "";
        if (!url.contains("?"))
            url += "?";
        else url += "&";
        url += MapToString(map);
        return url;
    }

    private String MapToString(Map<String, String> map) {
        String param = "";
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                param += entry.getKey() + "=" + URLEncoder.encode(
                        entry.getValue() != null ? entry.getValue() : "", "utf-8");
                param += "&";
            }
            param = param.substring(0, param.length() - 1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }
}
