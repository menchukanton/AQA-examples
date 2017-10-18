import Requests.Requests;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hillel on 18.08.17.
 */
public class Tests {
    String userId;

    private void findUserID (String data) {
        Pattern findID = Pattern.compile("\"id\":\"(\\d+)");
        Matcher m = findID.matcher(data);
        if (m.find()) {
            userId = m.group(1);
        }
    }

    private void checkContentType(String headers) {
        Assert.assertTrue(headers.contains("Content-Type: application/json"));
    }

    @Test(description = "Second requirement - getting user list")
    void getUsers() throws IOException {
        String[] responseData = Requests.sendGet("http://soft.it-hillel.com.ua:3000/api/users");
        Assert.assertTrue(responseData[1].contains("[{\"id\":\""));
        findUserID(responseData[1]);
        checkContentType(responseData[0]);
    }

    @Test(description = "Third requirement - saving users")
    void saveUser(String data) throws IOException {
        String[] responseData = Requests.sendPut("http://soft.it-hillel.com.ua:3000/api/users/"+userId, '{' + data + '}');
        Assert.assertTrue(Requests.getUserInfo(userId).contains(data));
        checkContentType(responseData[0]);
    }



}
