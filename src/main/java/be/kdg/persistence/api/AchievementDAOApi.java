/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.persistence.api;

import be.kdg.model.Achievement;

import java.util.List;

public interface AchievementDAOApi {

    public void addAchievement(Achievement achievement);

    public List getAllAchievements();

    public Achievement getAchievementByTitle(String title);

    public Achievement getAchievementById(int id);
}
