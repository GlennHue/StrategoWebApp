/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.service.impl;

import be.kdg.model.Achievement;
import be.kdg.persistence.api.AchievementDAOApi;
import be.kdg.service.api.AchievementServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("achievementService")
public class AchievementServiceImpl implements AchievementServiceApi {

    @Autowired
    private AchievementDAOApi achievementDao;

    @Override
    public void addAchievement(Achievement achievement) {
        achievementDao.addAchievement(achievement);
    }

    @Override
    public List getAllAchievements() {
        return achievementDao.getAllAchievements();
    }

    @Override
    public Achievement getAchievementByTitle(String title) {
        return achievementDao.getAchievementByTitle(title);
    }

    @Override
    public Achievement getAchievementById(int id) {
        return achievementDao.getAchievementById(id);
    }


}
