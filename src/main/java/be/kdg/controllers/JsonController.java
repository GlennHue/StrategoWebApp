package be.kdg.controllers;

import be.kdg.beans.DragDropBean;
import be.kdg.model.Achievement;
import be.kdg.model.User;
import be.kdg.persistence.api.AchievementDAOApi;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.persistence.impl.AchievementDAOImpl;
import be.kdg.persistence.impl.UserDAOImpl;
import be.kdg.service.api.AchievementServiceApi;
import be.kdg.service.api.GameServiceApi;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wouter on 13/02/14.
 */
@Controller
public class JsonController {
    @Autowired
    private UserServiceApi userService;
    @Autowired
    private DragDropBean bean;
    @Autowired
    private GameServiceApi gameService;

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
    //todo behaalde achievemtns + alle achievements, getvriendenlijst

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
            resultObj.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObj.toString();
    }

    @RequestMapping(value = "api/getFriends", method = RequestMethod.GET)
    @ResponseBody
    public String getFriends(@RequestParam("username")String username) {
        JSONObject resultObj = new JSONObject();
        try{
            List<User> friends = userService.getFriendsByUsername(username);
            JSONArray array = new JSONArray();
            for(User friend : friends) {
                JSONObject arrayElement = new JSONObject();
                arrayElement.put("id", friend.getId());
                arrayElement.put("email", friend.geteMail());
                arrayElement.put("username", friend.getUsername());
                array.put(arrayElement);
            }
            resultObj.put("friends", array);
            resultObj.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObj.toString();
    }

    @RequestMapping(value = "api/game/setStartPosition", method = RequestMethod.GET)
    @ResponseBody
    public String setStartPosition(@RequestParam("pieces")String pieces, @RequestParam("gameId")int gameId){

        gameService.setStartPosition(gameId, pieces);

        boolean ready = gameService.getReady(gameId);

        if(ready) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "api/game/getReady", method = RequestMethod.GET)
    @ResponseBody
    public String getReady(@RequestParam("gameId")int gameId){
        boolean ready = gameService.getReady(gameId);

        if(ready) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "api/game/setReady", method = RequestMethod.GET)
    @ResponseBody
    public String setReady(){
        bean.setReady();

        return "true";
    }

    @RequestMapping(value = "api/game/movePiece", method = RequestMethod.GET)
    @ResponseBody
    public String movePiece(@RequestParam("index")String index){
        int newIndex = Integer.parseInt(index.split(",")[0]);
        int oldIndex = Integer.parseInt(index.split(",")[1]);

        bean.movePiece(newIndex, oldIndex);

        return "true";
        //
    }

    /*@RequestMapping(value = "api/game/setstartposition", method = RequestMethod.GET)
    @ResponseBody
    public String setStartPosition(@RequestParam("gameid")int gameid, )
                                  */
}