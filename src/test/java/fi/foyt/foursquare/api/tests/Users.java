package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Badges;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.CompactUser;
import fi.foyt.foursquare.api.entities.CompleteUser;
import fi.foyt.foursquare.api.entities.LeaderboardItemGroup;
import fi.foyt.foursquare.api.entities.TipGroup;
import fi.foyt.foursquare.api.entities.TodoGroup;
import fi.foyt.foursquare.api.entities.UserGroup;
import fi.foyt.foursquare.api.entities.UserGroups;
import fi.foyt.foursquare.api.entities.VenueHistoryGroup;

public class Users {

  @Test
  public final void testUser() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompleteUser user = foursquareApi.user("self").getResult();
    assertEquals("7613255", user.getId());
    assertEquals("Foyt", user.getFirstName());
    assertEquals("Development", user.getLastName());
    assertEquals("none", user.getGender());
    assertEquals("Mikkeli, Suomi", user.getHomeCity());
    assertEquals("self", user.getRelationship());
    assertEquals("user", user.getType());
    assertEquals(false, user.getPings());
    assertEquals("development@foyt.fi", user.getContact().getEmail());
    assertEquals("100002112406948", user.getContact().getFacebook());
    assertEquals(Long.valueOf(1), user.getBadges().getCount());
    assertEquals(Long.valueOf(0), user.getMayorships().getCount());
    assertEquals(Long.valueOf(9), user.getCheckins().getCount());
    assertEquals(Long.valueOf(0), user.getFollowing().getCount());
    assertEquals(Long.valueOf(0), user.getRequests().getCount());
    assertEquals(Long.valueOf(0), user.getTips().getCount());
    assertEquals(Long.valueOf(0), user.getTodos().getCount());
    assertEquals(Long.valueOf(35), user.getScores().getRecent());
    assertEquals(Long.valueOf(35), user.getScores().getMax());
    assertEquals(Long.valueOf(50), user.getScores().getGoal());
    assertEquals(Long.valueOf(3), user.getScores().getCheckinsCount());

    UserGroups friends = user.getFriends();

    assertEquals(Long.valueOf(2), friends.getCount());
    UserGroup friendsGroup = friends.getGroups()[0];

    assertEquals(Long.valueOf(2), friendsGroup.getCount());
    assertEquals("others", friendsGroup.getType());
    assertEquals("other friends", friendsGroup.getName());
    
    user = foursquareApi.user(null).getResult();
    assertEquals("7613255", user.getId());
  }

  @Test
  public final void testUserBrand() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompleteUser user = foursquareApi.user("1504602").getResult();
    assertEquals("1504602", user.getId());
    assertEquals("Mashable", user.getFirstName());
    assertEquals("none", user.getGender());
    assertEquals("New York City, NY", user.getHomeCity());
    assertEquals("followingThem", user.getRelationship());
    assertEquals("brand", user.getType());
    assertEquals(true, user.getPings());
    assertEquals("mashable", user.getContact().getTwitter());
    assertEquals(Long.valueOf(0), user.getBadges().getCount());
    assertEquals(Long.valueOf(0), user.getMayorships().getCount());
    assertEquals(Long.valueOf(71), user.getCheckins().getCount());
    assertEquals(Long.valueOf(0), user.getFollowing().getCount());
    assertEquals(Long.valueOf(93702), user.getFollowers().getCount());
    assertEquals(Long.valueOf(110), user.getTips().getCount());
    assertEquals(Long.valueOf(0), user.getTodos().getCount());
    assertEquals(Long.valueOf(0), user.getScores().getRecent());
    assertEquals(Long.valueOf(0), user.getScores().getMax());
    assertEquals(Long.valueOf(50), user.getScores().getGoal());
    assertEquals(Long.valueOf(0), user.getScores().getCheckinsCount());

    UserGroups friends = user.getFriends();
    UserGroup mutualFriendGroup = friends.getGroups()[0];
    UserGroup otherFriendGroup = friends.getGroups()[1];

    assertEquals(Long.valueOf(93702), otherFriendGroup.getCount());
    assertEquals("others", otherFriendGroup.getType());
    assertEquals("other friends", otherFriendGroup.getName());

    assertEquals(Long.valueOf(0), mutualFriendGroup.getCount());
    assertEquals("friends", mutualFriendGroup.getType());
    assertEquals("mutual friends", mutualFriendGroup.getName());
  }

  @Test
  public final void testUsersCheckins() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CheckinGroup checkins = foursquareApi.usersCheckins("self", null, null, null, null).getResult();
    assertEquals(Long.valueOf(6), checkins.getCount());
    Checkin checkin = checkins.getItems()[0];
    assertEquals("4de3212d2271bfb844acdf5d", checkin.getId());
    assertEquals(Long.valueOf(1306730797), checkin.getCreatedAt());
    assertEquals(true, checkin.isPrivate());
    assertEquals("Europe/Helsinki", checkin.getTimeZone());
    assertEquals("4c6bbfafa48420a1b09a0a0b", checkin.getVenue().getId());
    
    checkins = foursquareApi.usersCheckins(null, null, null, null, null).getResult();
    assertEquals(Long.valueOf(6), checkins.getCount());
    checkin = checkins.getItems()[0];
    assertEquals("4de3212d2271bfb844acdf5d", checkin.getId());
    assertEquals(Long.valueOf(1306730797), checkin.getCreatedAt());
    assertEquals(true, checkin.isPrivate());
    assertEquals("Europe/Helsinki", checkin.getTimeZone());
    assertEquals("4c6bbfafa48420a1b09a0a0b", checkin.getVenue().getId());
  }

  @Test
  public final void testUsersSearch() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompactUser[] users = foursquareApi.usersSearch(null, null, "naveen", null, null, null).getResult();
    assertEquals(1, users.length);
    assertEquals("33", users[0].getId());
    assertEquals("Naveen", users[0].getFirstName());
    assertEquals("male", users[0].getGender());
    assertEquals("New York, NY", users[0].getHomeCity());
  }

  @Test
  public final void testUsersFriends() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    UserGroup users = foursquareApi.usersFriends("self").getResult();
    assertEquals(Long.valueOf(2), users.getCount());
    CompactUser user1 = users.getItems()[0];

    assertEquals("7613255", user1.getId());
    assertEquals("Foyt", user1.getFirstName());
    assertEquals("Development", user1.getLastName());
    assertEquals("none", user1.getGender());
    assertEquals("Mikkeli, Suomi", user1.getHomeCity());
    assertEquals("friend", user1.getRelationship());

    CompactUser user2 = users.getItems()[1];

    assertEquals("1504602", user2.getId());
    assertEquals("Mashable", user2.getFirstName());
    assertEquals("none", user2.getGender());
    assertEquals("New York City, NY", user2.getHomeCity());
    assertEquals("followingThem", user2.getRelationship());
    
    users = foursquareApi.usersFriends(null).getResult();
    assertEquals(Long.valueOf(2), users.getCount());
    user1 = users.getItems()[0];

    assertEquals("7613255", user1.getId());
    assertEquals("Foyt", user1.getFirstName());
    assertEquals("Development", user1.getLastName());
    assertEquals("none", user1.getGender());
    assertEquals("Mikkeli, Suomi", user1.getHomeCity());
    assertEquals("friend", user1.getRelationship());

    user2 = users.getItems()[1];

    assertEquals("1504602", user2.getId());
    assertEquals("Mashable", user2.getFirstName());
    assertEquals("none", user2.getGender());
    assertEquals("New York City, NY", user2.getHomeCity());
    assertEquals("followingThem", user2.getRelationship());
  }

  @Test
  public final void testUsersRequest() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompleteUser user = foursquareApi.usersRequest("7613255").getResult();
    assertEquals("7613255", user.getId());
  }

  @Test
  public final void testUsersApprove() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompleteUser user = foursquareApi.usersApprove("10078668").getResult();
    assertEquals("10078668", user.getId());
  }

  @Test
  public final void testUsersDeny() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompleteUser user = foursquareApi.usersDeny("10078668").getResult();
    assertEquals("10078668", user.getId());
  }

  @Test
  public final void testUsersUnfriend() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    CompleteUser user = foursquareApi.usersUnfriend("7613255").getResult();
    assertEquals("7613255", user.getId());
  }
  
  @Test
  public final void testUsersRequests() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<CompactUser[]> result = foursquareApi.usersRequests();
    
    assertEquals("7613255", result.getResult()[0].getId());
    assertEquals("Foyt", result.getResult()[0].getFirstName());
    assertEquals("Development", result.getResult()[0].getLastName());
    assertEquals("none", result.getResult()[0].getGender());
    assertEquals("Mikkeli, Suomi", result.getResult()[0].getHomeCity());
    assertEquals("pendingMe", result.getResult()[0].getRelationship());   
  }
  
  @Test
  public final void testUsersLeaderboard() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<LeaderboardItemGroup> result = foursquareApi.usersLeaderboard(null);
    assertEquals(Integer.valueOf(200), result.getMeta().getCode());
    assertEquals(Long.valueOf(1), result.getResult().getCount());
    assertEquals("10078668", result.getResult().getItems()[0].getUser().getId());
    assertEquals(Long.valueOf(0), result.getResult().getItems()[0].getScores().getRecent());
    assertEquals(Long.valueOf(11), result.getResult().getItems()[0].getScores().getMax());
    assertEquals(Long.valueOf(50), result.getResult().getItems()[0].getScores().getGoal());
    assertEquals(Long.valueOf(0), result.getResult().getItems()[0].getScores().getCheckinsCount());
    assertEquals(Integer.valueOf(1), result.getResult().getItems()[0].getRank());
  }
  
  @Test
  public final void testUsersBadges() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Badges> result = foursquareApi.usersBadges("self");
    
    assertEquals(Integer.valueOf(200), result.getMeta().getCode());
    assertEquals("all", result.getResult().getSets().getGroups()[0].getType());
    assertEquals("all badges", result.getResult().getSets().getGroups()[0].getName());
    assertEquals("https://foursquare.com/img/badge/", result.getResult().getSets().getGroups()[0].getImage().getPrefix());
    assertArrayEquals(new Integer[] {24, 32, 48, 64}, result.getResult().getSets().getGroups()[0].getImage().getSizes());
    assertEquals("/allbadges.png", result.getResult().getSets().getGroups()[0].getImage().getName());
    assertArrayEquals(new String[] {"4de4762d52b1d38d299e6008"}, result.getResult().getSets().getGroups()[0].getItems());
    assertEquals(0, result.getResult().getSets().getGroups()[0].getGroups().length);
    assertEquals("4c4f08667a0803bbb1202ab7", result.getResult().getBadges()[0].getId());
    assertEquals("4c4f08667a0803bbb1202ab7", result.getResult().getBadges()[0].getBadgeId());
    assertEquals("Super User", result.getResult().getBadges()[0].getName());
    assertEquals("All in a month's work!", result.getResult().getBadges()[0].getHint());
    assertEquals("https://playfoursquare.s3.amazonaws.com/badge/", result.getResult().getBadges()[0].getImage().getPrefix());
    assertArrayEquals(new Integer[] {57, 114, 200, 300, 400}, result.getResult().getBadges()[0].getImage().getSizes());
    assertEquals("/default_off.png", result.getResult().getBadges()[0].getImage().getName());
    assertEquals(0, result.getResult().getBadges()[0].getUnlocks().length);
    assertEquals("4sq", result.getResult().getDefaultSetType());
    
    Result<Badges> result2 = foursquareApi.usersBadges(null);
    assertEquals(result.getResult().getBadges()[0].getId(), result2.getResult().getBadges()[0].getId());
  }
  
  @Test
  public final void testUsersTips() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<TipGroup> result = foursquareApi.usersTips(null, "recent", null, null, null);
    
    assertEquals(Integer.valueOf(200), result.getMeta().getCode());
    assertEquals("4df1ec5045dd4e269339e96f", result.getResult().getItems()[0].getId());
    assertEquals(Long.valueOf(1307700304), result.getResult().getItems()[0].getCreatedAt());
    assertEquals("Wonderful festival called Beautiful Days here at 19, 20 & 21 of August 2011", result.getResult().getItems()[0].getText());
    assertEquals("http://www.beautifuldays.org", result.getResult().getItems()[0].getUrl());
    assertEquals("done", result.getResult().getItems()[0].getStatus());
    assertEquals(Long.valueOf(0), result.getResult().getItems()[0].getTodo().getCount());
    assertEquals(Long.valueOf(1), result.getResult().getItems()[0].getDone().getCount());
    assertEquals("4bb73a402ea19521b1a6ac2f", result.getResult().getItems()[0].getVenue().getId());
  }
  
  @Test
  public final void testUsersTodos() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<TodoGroup> result = foursquareApi.usersTodos(null, "recent", null);
    
    assertEquals(Integer.valueOf(200), result.getMeta().getCode());
    assertEquals("4df203b045dd4e26933a50ed", result.getResult().getItems()[0].getId());
    assertEquals(Long.valueOf(1307706288), result.getResult().getItems()[0].getCreatedAt());
    assertEquals("4bb8f41970c603bb64bf96b4", result.getResult().getItems()[0].getTip().getId());
  }
  
  @Test
  public final void testUsersVenueHistory() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<VenueHistoryGroup> result = foursquareApi.usersVenueHistory(null, null, null);
    
    assertEquals(Integer.valueOf(200), result.getMeta().getCode());
    assertEquals("4c6bbfafa48420a1b09a0a0b", result.getResult().getItems()[0].getVenue().getId());
    assertEquals(Integer.valueOf(1), result.getResult().getItems()[0].getBeenHere());
  }
  
  @Test
  public final void testUsersSetPings() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<CompleteUser> result = foursquareApi.usersSetPings("10078668", "true");
    
    assertEquals(Integer.valueOf(200), result.getMeta().getCode());
    assertEquals("10078668", result.getResult().getId());
  }
  
  
}