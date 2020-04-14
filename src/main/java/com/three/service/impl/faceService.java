package com.three.service.impl;

import com.three.service.faceServiceInterface;
import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class faceService implements faceServiceInterface {

    final String APP_ID = "19391142";

    final String API_KEY = "OS69z5q5znCmzCEIBBlqQpVY";

    final String SECRET_KEY = "37G5SXrd5syh3vmRyLkLplemWKqaVfSQ";

    final AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

    @Override
    public int faceVerify(String base64) {
        String groupIdList = "hrm"; // 百度人脸库名称

        JSONObject res = client.search(base64, "BASE64", groupIdList,null);

        if(res.get("error_code").toString().equals("0")){
            JSONObject result = (JSONObject)res.get("result");
            JSONArray userList = (JSONArray) result.get("user_list");
            JSONObject userList01 = (JSONObject)userList.get(0);
            return Integer.parseInt((String) userList01.get("user_id"));
        }
        else
            return -1;
    }

    @Override
    public boolean faceRegister(String base64, Integer userId) {
        HashMap<String, String> options = new HashMap<String, String>();

        //图片质量 NONE: 不进行控制 LOW:较低的质量要求 NORMAL: 一般的质量要求 HIGH: 较高的质量要求 默认 NONE
        options.put("quality_control","NORMAL");

        //活体检测 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率)  NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
        options.put("liveness_control","LOW");

        //百度人脸库名称
        String groupId = "hrm";

        JSONObject res = client.addUser(base64, "BASE64", groupId, userId.toString(), options);

        if(res.get("error_code").toString().equals("0"))
            return true;
        else
            return false;
    }
}
