package be.kdg.controllers;

import be.kdg.beans.DragDropBean;
import be.kdg.model.Achievement;
import be.kdg.model.User;
import be.kdg.persistence.api.AchievementDAOApi;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.persistence.impl.AchievementDAOImpl;
import be.kdg.persistence.impl.UserDAOImpl;
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

    private boolean zever = true;

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
        if(zever)  {
        List<Achievement> achievementArrayList = new ArrayList<Achievement>();
        achievementArrayList.add(new Achievement("Minesweeper", "Sweep 5 mines with one miner"));
        achievementArrayList.add(new Achievement("Win", "Win one game"));
        achievementArrayList.add(new Achievement("more wins", "win 10 games"));
        achievementArrayList.add(new Achievement("im a pro", "win 100 games"));
        achievementArrayList.add(new Achievement("do you even lift bro", "beat a marshal with a spy 10 times"));
        User user1 = new User("andy", "andy", "jefke@gmail.com");
        user1.setVerified(true);
        for(Achievement a : achievementArrayList) {
            user1.addAchievement(a);
        }
        AchievementDAOApi achievementDAO = new AchievementDAOImpl();
        UserDAOApi userDAO = new UserDAOImpl();
        for(Achievement a : achievementArrayList) {
            achievementDAO.addAchievement(a);
        }
        userDAO.addUser(user1);
        zever = false;
        }
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
    public boolean setStartPosition(@RequestParam("pieces")String pieces ){

        bean.putStartPieces(pieces);


        return true;
    }

    /*@RequestMapping(value = "api/game/setstartposition", method = RequestMethod.GET)
    @ResponseBody
    public String setStartPosition(@RequestParam("gameid")int gameid, )
                                  */
}