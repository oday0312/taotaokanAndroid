package com.theindex.taotaokanAndroid.weibo;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 14-1-3
 * Time: PM6:06
 * To change this template use File | Settings | File Templates.
 */
import org.json.JSONException;
import org.json.JSONObject;


public class Userparser {

    private weiboUser userBean = null;

    public Userparser() {
        userBean = new weiboUser();
    }

    public weiboUser userInfoParser(String dataStr) {
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            userBean.setUid(jsonObject.getString("id"));
            userBean.setScreen_name(jsonObject.getString("screen_name"));
            userBean.setName(jsonObject.getString("name"));
            userBean.setLocation(jsonObject.getString("location"));
            userBean.setProfile_image_url(jsonObject.getString("profile_image_url"));
            userBean.setGender(jsonObject.getString("gender"));
            userBean.setAvatar_large(jsonObject.getString("avatar_large"));
        } catch (JSONException e) {
            userBean = null;
            e.printStackTrace();
        }
        return userBean;
    }

}