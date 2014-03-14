package be.kdg.controllers;


import be.kdg.beans.LobbyBean;
import be.kdg.model.*;
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
    private GameServiceApi gameService;
    @Autowired
    private PlayerServiceApi playerService;
    @Autowired
    private LobbyBean lobbyBean;

    // Declare this Player here so when the second user pols the queue status, he also knows a player has been found!!
    private Player player;
    

    @Autowired
    private AchievementServiceApi achievementService;
    //todo behaalde achievemtns + alle achievements, getvriendenlijst

    @RequestMapping(value = "/api/verifyuser", method = RequestMethod.POST)
    @ResponseBody
    public String showData(@RequestParam("username") String username, @RequestParam("password") String password) throws JSONException {
        JSONObject jSonVerified = new JSONObject();
        jSonVerified.put("isVerified", userService.userIsValid(username, password));
        return jSonVerified.toString();
    }

    @RequestMapping(value = "/api/user/getachievements", method = RequestMethod.GET)
    @ResponseBody
    public String getAchievements(@RequestParam("username") String username) {
        JSONObject resultObj = new JSONObject();
        try {
            List<Achievement> achievements = userService.getAchievementsByUsername(username);
            JSONArray array = new JSONArray();
            for (Achievement a : achievements) {
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

    @RequestMapping(value = "api/user/getStats", method = RequestMethod.GET)
    @ResponseBody
    public String getStats(@RequestParam("username") String username) {
        JSONObject obj = new JSONObject();
        User user = userService.getUser(username);
        try {
            obj.put("maxRank", userService.getMaxRank());
            obj.put("myRank", userService.getRank(user));
            obj.put("wins", user.getWins());
            obj.put("losses", user.getLosses());
            obj.put("gamesPlayer", user.getWins() + user.getLosses());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }

    @RequestMapping(value = "api/getFriends", method = RequestMethod.GET)
    @ResponseBody
    public String getFriends(@RequestParam("username") String username) {
        JSONObject resultObj = new JSONObject();
        try {
            List<User> friends = userService.getFriendsByUsername(username);
            JSONArray array = new JSONArray();
            for (User friend : friends) {
                Boolean userAndFriendAreFriends = userService.userAndFriendAreFriends(username, friend.getUsername());
                JSONObject arrayElement = new JSONObject();
                arrayElement.put("id", friend.getId());
                arrayElement.put("email", friend.geteMail());
                arrayElement.put("username", friend.getUsername());
                arrayElement.put("status", friend.getStatus());
                arrayElement.put("status", friend.getStatus());
                arrayElement.put("userAndFriendAreFriends", userAndFriendAreFriends);
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
    public String setStartPosition(@RequestParam("pieces") String pieces, @RequestParam("playerId") String playerId, @RequestParam("gameId") int gameId) {

        //gameService.setStartPosition(gameId, pieces);
        gameService.addStartPosition(gameId,pieces);
        playerService.setReady(Integer.parseInt(playerId));
        boolean ready = gameService.getReady(gameId);

        if(ready) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "api/game/getStartPosition", method = RequestMethod.GET)
    @ResponseBody
    public String getStartPosition(@RequestParam("gameId") String gameId, @RequestParam("color") String color) {
        JSONObject jSonPieces = new JSONObject();

        List<StartPosition> startPositions = gameService.getStartingPositions(Integer.parseInt(gameId));
        try {
            if (startPositions.get(0).getColor().equalsIgnoreCase(color)) {
                jSonPieces.put("pieces", startPositions.get(1).getPiece());

            } else jSonPieces.put("pieces", startPositions.get(0).getPiece());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSonPieces.toString();
    }

    @RequestMapping(value = "api/game/getReady", method = RequestMethod.GET)
    @ResponseBody
    public String getReady(@RequestParam("gameId") int gameId) {
        boolean ready = gameService.getReady(gameId);
        if (ready) {
            gameService.setStartingPlayer(gameId);
        }
        JSONObject jSonResult = new JSONObject();
        try {
            jSonResult.put("isReady", ready);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSonResult.toString();
    }


    @RequestMapping(value = "api/game/setReady", method = RequestMethod.GET)
    @ResponseBody
    public String setReady(@RequestParam("playerId") int playerId) {
        playerService.setReady(playerId);
        String pieces = "r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,";
        int gameId = 1;
        //gameService.setStartPosition(gameId, pieces);
        gameService.addStartPosition(gameId,pieces);
        playerService.setReady(playerId);

        return "true";
    }

    @RequestMapping(value = "api/game/movePiece", method = RequestMethod.POST)
    @ResponseBody
    public String movePiece(@RequestParam("index") String index, @RequestParam("playerId") int playerId) {
        int newIndex = Integer.parseInt(index.split(",")[0]);
        int oldIndex = Integer.parseInt(index.split(",")[1]);

        Player player1 = playerService.getPlayerById(playerId);
        player1.setReady(false);
        Player enemy = playerService.getEnemy(playerId);
        enemy.setReady(true);
        playerService.savePlayer(player1);
        playerService.savePlayer(enemy);

        gameService.addMove(playerId,newIndex,oldIndex);
        return "true";
    }

    /*@RequestMapping(value = "api/game/setstartposition", method = RequestMethod.GET)
    @ResponseBody
    public String setStartPosition(@RequestParam("gameid")int gameid, )
                                  */
    @RequestMapping(value = "api/game/setStartPosition", method = RequestMethod.POST)
    @ResponseBody
    public String setStartPosition(@RequestParam("pieces") String pieces, @RequestParam("playerId") String playerId, @RequestParam("gameId") String gameId) throws JSONException {
        JSONObject jSonPieces = new JSONObject();
        //gameService.setStartPosition(Integer.parseInt(gameId),pieces);
        gameService.addStartPosition(Integer.parseInt(gameId), pieces);
        playerService.setReady(Integer.parseInt(playerId));
        Boolean enemyReady = gameService.getReady(Integer.parseInt(gameId));
        Player player1 = playerService.getPlayerById(Integer.parseInt(playerId));
        Color color = player1.getColor();
        if (enemyReady) {
            List<StartPosition> startPositions = gameService.getStartingPositions(Integer.parseInt(gameId));
            if (startPositions.get(0).getColor().equalsIgnoreCase(color.toString())) {
                jSonPieces.put("pieces", startPositions.get(1).getPiece());
            } else jSonPieces.put("pieces", startPositions.get(0).getPiece());
        } else {
            jSonPieces.put("pieces", "-1");
        }
        return jSonPieces.toString();
    }

    @RequestMapping(value = "api/logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout(@RequestParam("username") String username) {
        userService.userLogout(username);
        return "true";
    }

    @RequestMapping(value = "api/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public String addFriend(@RequestParam("username") String username, @RequestParam("friend") String friendname) {
        User friend = userService.insertFriend(username, friendname);
        Boolean userAndFriendAreFriends = userService.userAndFriendAreFriends(username, friendname);
        JSONObject resultObject = new JSONObject();
        JSONObject friendObject = new JSONObject();
        try {
            friendObject.put("id", friend.getId());
            friendObject.put("email", friend.geteMail());
            friendObject.put("username", friend.getUsername());
            friendObject.put("status", friend.getStatus());
            friendObject.put("userAndFriendAreFriends", userAndFriendAreFriends);
            resultObject.put("friend", friendObject);
            resultObject.put("username", username);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObject.toString();
    }

    @RequestMapping(value = "api/game/fight", method = RequestMethod.GET)
    @ResponseBody
    public String fight(@RequestParam("piecePlayer") String piecePlayer, @RequestParam("pieceEnemy") String pieceEnemy) {
        int result = gameService.fight(piecePlayer, pieceEnemy);
        JSONObject resultObject = new JSONObject();
        try {
            resultObject.put("result", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultObject.toString();
    }

    @RequestMapping(value = "api/game/fightWeb", method = RequestMethod.GET)
    @ResponseBody
    public String fightWeb(@RequestParam("gameId")int gameId,@RequestParam("playerIndex")int playerIndex,@RequestParam("enemyIndex")int enemyIndex) {
        Game game = gameService.reconstructGame(gameId);

        int result =  gameService.fight(gameId,playerIndex,enemyIndex);
        JSONObject resultObject = new JSONObject();
        try {

            resultObject.put("result",result);
            resultObject.put("yourPiece",game.getBoard().getTile(playerIndex).getPiece().getName());
            resultObject.put("theirPiece",game.getBoard().getTile(enemyIndex).getPiece().getName());
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

    @RequestMapping(value = "api/game/win", method = RequestMethod.POST)
    @ResponseBody
    public String win(@RequestParam("winnerId") String winnerId) {
        Game game = null;
        Player winner = playerService.getPlayerById(Integer.parseInt(winnerId));
        game = winner.getGame();
        Player loser = null;
        for (Player player : game.getPlayers()) {
            if (player != winner) {
                loser = player;
            }
        }
        if (loser != null && winner != null) {
            User winnerUser = winner.getUser();
            User loserUser = loser.getUser();
            int difference = winnerUser.getScore() - loserUser.getScore();
            if (difference > 50) {
                winnerUser.setScore(winnerUser.getScore() + 10);
                loserUser.setScore(loserUser.getScore() - 10);
            } else if (difference > 20) {
                winnerUser.setScore(winnerUser.getScore() + 11);
                loserUser.setScore(loserUser.getScore() - 11);
            } else if (difference > 0) {
                winnerUser.setScore(winnerUser.getScore() + 12);
                loserUser.setScore(loserUser.getScore() - 12);
            } else if (difference > -20) {
                winnerUser.setScore(winnerUser.getScore() + 13);
                loserUser.setScore(loserUser.getScore() - 13);
            } else {
                winnerUser.setScore(winnerUser.getScore() + 15);
                loserUser.setScore(loserUser.getScore() - 14);
            }
            winnerUser.setWins(winnerUser.getWins() + 1);
            loserUser.setLosses(loserUser.getLosses() + 1);
            userService.updateUser(winnerUser);
            userService.updateUser(loserUser);
        }
        return "goed bezig";
    }

    @RequestMapping(value = "api/user/getGameHistory", method = RequestMethod.GET)
    @ResponseBody
    public String getGameHistory(@RequestParam("username") String username) {
        User user = userService.getUser(username);
        JSONObject obj = new JSONObject();
        try {
            List<Game> games = userService.getGamesByUsername(username);
            Move lastMove = null;
            JSONArray array = new JSONArray();
            for (Game game : games) {
                int max = 0;
                for (Move move : game.getMoves()) {
                    if (max < move.getNumber()) {
                        lastMove = move;
                    }
                }
                JSONObject arrayElement = new JSONObject();
                arrayElement.put("gameId", game.getId());
                arrayElement.put("timePerTurn", game.getTime());
                JSONArray innerArray = new JSONArray();
                for (Player player : game.getPlayers()) {
                    JSONObject innerArrayElement = new JSONObject();
                    innerArrayElement.put("userId", player.getUser().getId());
                    innerArrayElement.put("username", player.getUser().getUsername());
                    innerArrayElement.put("score", player.getUser().getScore());
                    innerArray.put(innerArrayElement);
                }
                arrayElement.put("players", innerArray);
                array.put(arrayElement);
            }
            obj.put("games", array);
            obj.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }

    @RequestMapping(value = "api/addUserToQueue", method = RequestMethod.POST)
    @ResponseBody
    public String addUserToQueue(@RequestParam("username") String username) {
        boolean secondPlayerPulled = false;//--> set player back to "null" so we can start over when we get 2 new players from the queue
        //check if someone else already pulled the data
        if (player == null) {
            User user = userService.getUser(username);
            lobbyBean.addUser(user);
            player = lobbyBean.checkGames(user);

            // in case someone else already pulled --> get playerId of other user to return second user !
        } else {
            Game game = player.getGame();
            if (game.getPlayers().get(0).getId() == player.getId()) {
                player = game.getPlayers().get(1);
            } else {
                player = game.getPlayers().get(0);
            }
            secondPlayerPulled = true;
        }

        JSONObject jSonResult = new JSONObject();
        try {
            if (player != null) {
                jSonResult.put("playerId", player.getId());
                jSonResult.put("gameId", player.getGame().getId());
                jSonResult.put("color", player.getColor());
            } else {
                jSonResult.put("playerId", -1);
            }
            if (secondPlayerPulled) {
                player = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSonResult.toString();
    }

    @RequestMapping(value = "api/game/getEnemyStatus", method = RequestMethod.GET)
    @ResponseBody
    public String getEnemyStatus(@RequestParam("playerId") int playerId) {
        boolean enemyStatus = playerService.getEnemy(playerId).getReady();
        JSONObject jSonResult = new JSONObject();
        try {
            if (!enemyStatus) {
                Move move = gameService.getMove(playerId);
                jSonResult.put("oldIndex", move.getOldIndex());
                jSonResult.put("newIndex", move.getNewIndex());
            }
            jSonResult.put("isReady", enemyStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSonResult.toString();
    }

    @RequestMapping(value = "api/game/reconstruction", method = RequestMethod.GET)
    @ResponseBody
    public String reconstruct(@RequestParam("gameId") int gameId) {
        Game game = gameService.reconstructGame(gameId);
        game.getBoard();
        return "";
    }
}