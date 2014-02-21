package be.kdg.service.api;

import be.kdg.model.Achievement;

import java.util.List;

/**
 * Created by Glenn on 19/02/14.
 */
public interface AchievementServiceApi {
    public void addAchievement(Achievement achievement);

    public List getAllAchievements();

    public Achievement getAchievementByTitle(String title);

    public Achievement getAchievementById(int id);

}
