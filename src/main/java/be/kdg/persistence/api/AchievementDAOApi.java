package be.kdg.persistence.api;

import be.kdg.model.Achievement;

import java.util.List;

/**
 * Created by Glenn on 19/02/14.
 */
public interface AchievementDAOApi {

    public void addAchievement(Achievement achievement);

    public List getAllAchievements();

    public Achievement getAchievementByTitle(String title);

    public Achievement getAchievementById(int id);


}
