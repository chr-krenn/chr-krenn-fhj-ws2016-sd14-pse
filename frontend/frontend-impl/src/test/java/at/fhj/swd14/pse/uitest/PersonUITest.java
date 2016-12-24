package at.fhj.swd14.pse.uitest;

import at.fhj.swd14.pse.pageobjects.ProfilePage;
import at.fhj.swd14.pse.pageobjects.UsersPage;
import at.fhj.swd14.pse.pageobjects.WelcomePage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonUITest extends BaseUITest {

    @BeforeClass
    public static void setup() {
        login();
    }

    @Test
    public void testProfileLink() {
        WelcomePage welcomePage = gotoStartPage();
        welcomePage.changeToOwnProfilePage();
        boolean isProfilePage = webdriver.getTitle().toLowerCase().contains("my profile");
        Assert.assertTrue(isProfilePage);
    }

    @Test
    public void checkUserList() {
        UsersPage usersPage = changeToUsersPage();
        int userCount = usersPage.retrieveUserListCount();
        Assert.assertTrue(userCount > 0);
    }

    @Test
    public void testOtherUserAsContact() {
        UsersPage usersPage = changeToUsersPage();
        boolean isFriend = usersPage.addUserAsFriend();
        Assert.assertTrue(isFriend);
        boolean isNotFriend = usersPage.removeUserAsFriend();
        Assert.assertTrue(isNotFriend);
    }

    @Test
    public void testOtherUsersSiteLink() {
        UsersPage usersPage = changeToUsersPage();
        ProfilePage otherUserPage = usersPage.clickUserLink();
        Assert.assertNotNull(otherUserPage);
    }

    @Test
    public void testContactInfo() {
        WelcomePage welcomePage = gotoStartPage();
        ProfilePage profilePage = welcomePage.changeToOwnProfilePage();
        testSection(profilePage);
    }

    private void testSection(ProfilePage profilePage) {
        testMailSection(profilePage);
        testHobbySection(profilePage);
        testSkillSection(profilePage);
        testTelNrSection(profilePage);
    }

    private void testMailSection(ProfilePage profilePage) {
        String mail = "bernhard@gmail.com";
        Assert.assertTrue(profilePage.addEmail(mail));
        Assert.assertTrue(profilePage.removeEmail(mail));
    }

    private void testHobbySection(ProfilePage profilePage) {
        String hobby = "Surfen";
        Assert.assertTrue(profilePage.addHobby(hobby));
        Assert.assertTrue(profilePage.removeHobby(hobby));
    }

    private void testSkillSection(ProfilePage profilePage) {
        String skill = "Java";
        Assert.assertTrue(profilePage.addSkill(skill));
        Assert.assertTrue(profilePage.removeSkill(skill));
    }

    private void testTelNrSection(ProfilePage profilePage) {
        String telnr = "06640000001";
        Assert.assertTrue(profilePage.addTelNr(telnr));
        Assert.assertTrue(profilePage.removeTelNr(telnr));
    }

    private UsersPage changeToUsersPage() {
        WelcomePage welcomePage = gotoStartPage();
        return welcomePage.changeToUsersPage();
    }
}
