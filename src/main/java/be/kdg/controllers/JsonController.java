package be.kdg.controllers;

import be.kdg.service.api.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wouter on 13/02/14.
 */
@Controller
public class JsonController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/api/users",method = RequestMethod.GET)
    @ResponseBody
    public String convertToJson() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectUsers = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","Wouter");
        jsonObject.put("password","test");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("username","Woody");
        jsonObject2.put("password","woody");
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        jsonObjectUsers.put("users",jsonArray);
        return jsonObjectUsers.toString();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String showData(@RequestParam("username")String username,@RequestParam("password")String password) throws JSONException {
        System.out.println(username+"!!!!!!!!!!!!!!!!!!");
        JSONObject jSonVerified = new JSONObject();
        jSonVerified.put("isVerified",userService.userIsValid(username,password));
        return jSonVerified.toString();
    }

}
