// package com.example.accessingdatamysql.ongoinminigame;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;

// import com.example.accessingdatamysql.card.Card;
// import com.example.accessingdatamysql.card.CardService;
// import com.example.accessingdatamysql.game.GameService;
// import com.example.accessingdatamysql.ongoingminigame.OnGoinTorreInfernalService;
// import com.example.accessingdatamysql.ongoingminigame.OnGoingTorreInfernal;
// import com.example.accessingdatamysql.ongoingminigame.RequestNewCard;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.stereotype.Service;

// @DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class OnGoingTorreInfernalServiceTests {

//     private static OnGoingTorreInfernal game;
//     @Autowired
//     protected OnGoinTorreInfernalService onGoingTorreInfernalService;

//     @Autowired
//     protected CardService cardService;

//     @Autowired
//     protected GameService gameService;

//     @BeforeEach
//     void SetUp(){
//         Long gameId = 2L;
//         onGoingTorreInfernalService.creatGame(gameId, gameService.findGame(gameId).get(), 
//             cardService.findAllCards());
//         game = onGoingTorreInfernalService.getGame(2L);
//     }

//     @Test
//     void testGetById(){
//         OnGoingTorreInfernal game2 = onGoingTorreInfernalService.getGame(2L);
//         assertEquals(game2.getPlayers(), game.getPlayers());
//     }

//     @Test
//     void testcreateGame(){
//         Long gameId = 1L;
//         onGoingTorreInfernalService.creatGame(gameId, gameService.findGame(gameId).get(), 
//             cardService.findAllCards());
//         Integer i=0;
//         for(OnGoingTorreInfernal g: onGoingTorreInfernalService.getAll()){
//             i=i+1;
//         }
//         assertEquals(2, i);
//     }
//     @Test
//     void testgetPoints(){
//         Integer points = onGoingTorreInfernalService.getGame(2L).getPoints().get(2L);
//         assertEquals(points, onGoingTorreInfernalService.getPoints(2L, 2L));
//     }
//     @Test
//     void testgetPlayerCard(){
//         Card playerCard = onGoingTorreInfernalService.getGame(2L).getPlayerCard().get(2L);
//         assertEquals(playerCard, onGoingTorreInfernalService.getPlayerCard(2L, 2L));
//     }
//     @Test
//     void testgetCenterCard(){
//         Card centerCard = onGoingTorreInfernalService.getGame(2L).getCurrentCard();
//         assertEquals(centerCard, onGoingTorreInfernalService.getCenterCard(2L));
//     }
//     @Test
//     void testnewCenterCard(){
//         Card centerCard = onGoingTorreInfernalService.getGame(2L).getCurrentCard();
//         RequestNewCard request = new RequestNewCard();
//         request.setPlayerId(2L);
//         onGoingTorreInfernalService.newCenterCard(2L, request);
//         assertNotEquals(centerCard, onGoingTorreInfernalService.getCenterCard(2L));
//     }
//     @Test
//     void testAddPoints(){
//         onGoingTorreInfernalService.addPoints(2L, 2L, 10);
//         Integer points = onGoingTorreInfernalService.getGame(2L).getPoints().get(2L);
//         assertEquals(10, points);
//     }
//     @Test
//     void testDeleteGame(){
//         Integer size = 0;
//         for(OnGoingTorreInfernal g: onGoingTorreInfernalService.getAll()){
//             size=size+1;
//         }
//         onGoingTorreInfernalService.deleteGame(2L);
//         Integer i=0;
//         for(OnGoingTorreInfernal g: onGoingTorreInfernalService.getAll()){
//             i=i+1;
//         }
//         assertEquals(size-1, i);
//     }
// }
