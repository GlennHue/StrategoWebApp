package be.kdg.controllers;

import be.kdg.model.Achievement;
import be.kdg.service.api.AchievementServiceApi;
import be.kdg.service.api.UserServiceApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wouter on 13/02/14.
 */
@Controller
public class JsonController {
    @Autowired
    private UserServiceApi userService;

    @Autowired
    private AchievementServiceApi achievementService;
    //used for sending getessage to android, can be deleted after everything works
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
    //todo behaalde achievemtns + alle achievements, getgrindenlijst

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String showData(@RequestParam("username")String username,@RequestParam("password")String password) throws JSONException {
        JSONObject jSonVerified = new JSONObject();
        jSonVerified.put("isVerified",userService.userIsValid(username,password));
        return jSonVerified.toString();
    }

    @RequestMapping(value = "/api/getachievements", method = RequestMethod.GET)
    @ResponseBody
    public String getAchievements(@RequestParam("username")String username){
        JSONObject resultObj = new JSONObject();
        try {
            resultObj.put("username", username);
            List<Achievement> achievements = userService.getAchievementsByUsername(username);
            JSONArray array = new JSONArray();
            for(Achievement a : achievements) {
                JSONObject arrayElement = new JSONObject();
                arrayElement.put("id", a.getId());
                arrayElement.put("title", a.getTitle());
                arrayElement.put("description", a.getDescription());
                array.put(arrayElement);
            }
            resultObj.put("achievements", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObj.toString();
    }

}