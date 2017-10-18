package Requests;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hillel on 18.08.17.
 */
public class Requests {
//    public static void main(String[] args) throws IOException {
//        System.out.println(sendGet("http://soft.it-hillel.com.ua:3000/api/users")[0]);
//    }

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String[] sendGet(String URL) throws IOException {
        HttpGet request = new HttpGet(URL);
        return getData(httpclient.execute(request));
    }

    public static String[] sendPost(String URL, String data) throws IOException {
        HttpPost request = new HttpPost(URL);
        request.setEntity(new StringEntity(data, "UTF-8"));
        return getData(httpclient.execute(request));
    }

    public static String[] sendPut(String URL, String data) throws IOException {
        HttpPut request = new HttpPut(URL);
        request.setEntity(new StringEntity(data, "UTF-8"));
        return getData(httpclient.execute(request));
    }

    private static String[] getData(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();

        String[] responseData = new String[2];
        responseData[0] = response.getEntity().toString();
        responseData[1] = entity != null ? EntityUtils.toString(entity) : "No response data.";
        return responseData;
    }

    public static String getUserInfo(String id) throws IOException {
        String data = sendGet("http://soft.it-hillel.com.ua:3000/api/users")[0];

        Pattern findUserInfo = Pattern.compile("\\{[^\\{\\}]*\"id\":\"" + id + "\"[^\\{\\}]*\\}");
        Matcher m = findUserInfo.matcher(data);
        if (m.find()) {
            return m.group();
        } else {
            return "No data found.";
        }
    }
}
