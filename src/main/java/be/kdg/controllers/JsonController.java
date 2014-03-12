package be.kdg.controllers;

import be.kdg.beans.DragDropBean;
import be.kdg.beans.LobbyBean;
import be.kdg.model.Achievement;
import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.User;
import be.kdg.service.api.AchievementServiceApi;
import be.kdg.service.api.GameServiceApi;
import be.kdg.service.api.PlayerServiceApi;
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
    private DragDropBean bean;
    @Autowired
    private GameServiceApi gameService;
    @Autowired
    private PlayerServiceApi playerService;
    @Autowired
    private LobbyBean lobbyBean;

    // Declare this array here so when the second user pols the queue status, he also knows a player has been found!!
    //private int[]idArray;
    Player player;

    @Autowired
    private AchievementServiceApi achievementService;
    //todo behaalde achievemtns + alle achievements, getvriendenlijst

    @RequestMapping(value = "/api/verifyuser", method = RequestMethod.POST)
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
                Boolean userAndFriendAreFriends = userService.userAndFriendAreFriends(username, friend.getUsername());
                JSONObject arrayElement = new JSONObject();
                arrayElement.put("id", friend.getId());
                arrayElement.put("email", friend.geteMail());
                arrayElement.put("username", friend.getUsername());
                arrayElement.put("status",friend.getStatus());
                arrayElement.put("status",friend.getStatus());
                arrayElement.put("userAndFriendAreFriends",userAndFriendAreFriends);
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
    public String setStartPosition(@RequestParam("pieces")String pieces,@RequestParam("playerId")String playerId, @RequestParam("gameId")int gameId){

        gameService.setStartPosition(gameId, pieces);
        gameService.addStartPosition(gameId, pieces);
        playerService.setReady(Integer.parseInt(playerId));
        boolean ready = gameService.getReady(gameId);

        if(ready) {
            return "true";
        } else {
            return "false";
        }
    }


    /*@RequestMapping(value = "api/game/setstartposition", method = RequestMethod.GET)
    @ResponseBody
    public String setStartPosition(@RequestParam("gameid")int gameid, )

    @RequestMapping(value = "api/game/setStartPosition", method = RequestMethod.POST)
    @ResponseBody
    public String setStartPosition(@RequestParam("pieces")String pieces,@RequestParam("playerId")String playerId,@RequestParam("gameId")String gameId ) throws JSONException {
        JSONObject jSonPieces = new JSONObject();
        //gameService.setStartPosition(Integer.parseInt(gameId),pieces);
        gameService.addStartPosition(Integer.parseInt(gameId),pieces);
        playerService.setReady(Integer.parseInt(playerId));
        Boolean enemyReady = gameService.getReady(Integer.parseInt(gameId));
        if (pieces != null && !pieces.isEmpty()) {
            bean.putStartPieces(pieces);
        }
        if (enemyReady) {
            gameService.getStartingPositions(Integer.parseInt(gameId));
        }
        jSonPieces.put("0","b0");
        jSonPieces.put("1","b1");
        jSonPieces.put("2","b5");
        jSonPieces.put("3","b11");
        return jSonPieces.toString();
    }*/

    @RequestMapping(value = "api/game/getReady", method = RequestMethod.GET)
    @ResponseBody
    public String getReady(@RequestParam("gameId")int gameId) {
        boolean ready = gameService.getReady(gameId);
        JSONObject jSonResult = new JSONObject();
        try {
            jSonResult.put("isReady",ready);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSonResult.toString();
    }



    @RequestMapping(value = "api/game/setReady", method = RequestMethod.GET)
    @ResponseBody
    public String setReady(@RequestParam("playerId")int playerId){
        playerService.setReady(playerId);
        String pieces = "r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,";
        int gameId = 1;
        gameService.setStartPosition(gameId, pieces);
        gameService.addStartPosition(gameId,pieces);
        playerService.setReady(playerId);

        return "true";
    }

    @RequestMapping(value = "api/game/movePiece", method = RequestMethod.GET)
    @ResponseBody
    public String movePiece(@RequestParam("index")String index,@RequestParam("gameId")int gameId){
        int newIndex = Integer.parseInt(index.split(",")[0]);
        int oldIndex = Integer.parseInt(index.split(",")[1]);

        gameService.addMove(gameId,newIndex,oldIndex);
        return "true";
        //
    }

    /*@RequestMapping(value = "api/game/setstartposition", method = RequestMethod.GET)
    @ResponseBody
    public String setStartPosition(@RequestParam("gameid")int gameid, )
                                  */
    @RequestMapping(value = "api/game/setStartPosition", method = RequestMethod.POST)
    @ResponseBody
    public String setStartPosition(@RequestParam("pieces")String pieces,@RequestParam("playerId")String playerId,@RequestParam("gameId")String gameId ) throws JSONException {
        JSONObject jSonPieces = new JSONObject();
        gameService.setStartPosition(Integer.parseInt(gameId),pieces);
        gameService.addStartPosition(Integer.parseInt(gameId),pieces);
        playerService.setReady(Integer.parseInt(playerId));
        Boolean enemyReady = gameService.getReady(Integer.parseInt(gameId));
        if (pieces != null && !pieces.isEmpty()) {
            bean.putStartPieces(pieces);
        }
        if (enemyReady) {
            gameService.getStartingPositions(Integer.parseInt(gameId));
        }
        jSonPieces.put("0","b0");
        jSonPieces.put("1","b1");
        jSonPieces.put("2","b5");
        jSonPieces.put("3", "b11");
        return jSonPieces.toString();
    }


    @RequestMapping(value = "api/logout",method = RequestMethod.POST)
    @ResponseBody
    public String logout(@RequestParam("username")String username) {
        userService.userLogout(username);
        return "true";
    }

    @RequestMapping(value = "api/addFriend",method = RequestMethod.POST)
    @ResponseBody
    public String addFriend(@RequestParam("username")String username,@RequestParam("friend")String friendname) {
        User friend =  userService.insertFriend(username,friendname);
        Boolean userAndFriendAreFriends = userService.userAndFriendAreFriends(username, friendname);
        JSONObject resultObject = new JSONObject();
        JSONObject friendObject = new JSONObject();
        try {
            friendObject.put("id",friend.getId());
            friendObject.put("email", friend.geteMail());
            friendObject.put("username", friend.getUsername());
            friendObject.put("status", friend.getStatus());
            friendObject.put("userAndFriendAreFriends",userAndFriendAreFriends);
            resultObject.put("friend",friendObject);
            resultObject.put("username",username);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObject.toString();
    }

    @RequestMapping(value = "api/game/fight",method = RequestMethod.GET)
    @ResponseBody
    public String fight(@RequestParam("piecePlayer")String piecePlayer,@RequestParam("pieceEnemy")String pieceEnemy) {
        int result =  gameService.fight(piecePlayer,pieceEnemy);
        JSONObject resultObject = new JSONObject();
        try {
            resultObject.put("result",result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObject.toString();
    }

    @RequestMapping(value = "api/game/fightWeb",method = RequestMethod.GET)
    @ResponseBody
    public String fightWeb(@RequestParam("gameId")int gameId,@RequestParam("playerIndex")int playerIndex,@RequestParam("enemyIndex")int enemyIndex) {
        int result =  gameService.fight(gameId,playerIndex,enemyIndex);
        JSONObject resultObject = new JSONObject();
        try {
            resultObject.put("result",result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObject.toString();
    }

    /*
    @RequestMapping(value = "api/acceptInvite",method = RequestMethod.POST)
    @ResponseBody
    public String acceptInvite(@RequestParam("username")String username,@RequestParam("friend")String friendname){
        User friend = userService.acceptFriend(username,friendname);
    }*/

    @RequestMapping(value = "api/game/win", method = RequestMethod.GET)
    @ResponseBody
    public String win(@RequestParam("winnerId")String winnerId) {
        Game game = null;
        Player winner = playerService.getPlayerById(Integer.parseInt(winnerId));
        game = winner.getGame();
        Player loser = null;
        for(Player player : game.getPlayers()) {
            if(player != winner) {
                loser = player;
            }
        }
        if(loser != null && winner != null) {
            User winnerUser = winner.getUser();
            User loserUser = loser.getUser();
            int difference = winnerUser.getScore() - loserUser.getScore();
            if (difference > 50) {
                winnerUser.setScore(winnerUser.getScore() + 10);
                loserUser.setScore(loserUser.getScore() - 10);
            } else if(difference > 20) {
                winnerUser.setScore(winnerUser.getScore() + 11);
                loserUser.setScore(loserUser.getScore() - 11);
            } else if(difference > 0) {
                winnerUser.setScore(winnerUser.getScore() + 12);
                loserUser.setScore(loserUser.getScore() - 12);
            } else if(difference > -20) {
                winnerUser.setScore(winnerUser.getScore() + 13);
                loserUser.setScore(loserUser.getScore() - 13);
            } else {
                winnerUser.setScore(winnerUser.getScore() + 15);
                loserUser.setScore(loserUser.getScore() - 14);
            }

        }
        return "goed bezig";
    }

    @RequestMapping(value = "api/addUserToQueue",method = RequestMethod.POST)
    @ResponseBody
    public String addUserToQueue(@RequestParam("username")String username) {
        boolean secondPLayerPulled = false;
        //check if someone else already pulled the data
        if (player == null) {
            User user = userService.getUser(username);
            lobbyBean.addUser(user);
            player = lobbyBean.checkGames(user);

            // in case someone else already pulled --> get playerId of other user for second user !
        } else {
            Game game = gameService.getGame(player.getGame().getId());
            if (game.getPlayers().get(0).getId() == player.getId()) {
                player = game.getPlayers().get(1);
            } else {
                player = game.getPlayers().get(0);
            }
            secondPLayerPulled = true;
        }

        JSONObject jSonResult = new JSONObject();
        try {
            if (player != null) {
                jSonResult.put("playerId", player.getId());
                jSonResult.put("gameId", player.getGame().getId());
                jSonResult.put("color", player.getColor());
            } else {
                jSonResult = null;
            }
            if (secondPLayerPulled) {
                player = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSonResult.toString();
    }


}