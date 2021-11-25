// package com.example.accessingdatamysql.ongoinminigame;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;

// import com.example.accessingdatamysql.card.Card;
// import com.example.accessingdatamysql.card.CardService;
// import com.example.accessingdatamysql.game.GameService;
// import com.example.accessingdatamysql.ongoingfoso.OnGoinFosoService;
// import com.example.accessingdatamysql.ongoingfoso.OnGoingFoso;

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
// public class OnGoingFosoServiceTests{

//     private static OnGoingFoso game;
//     @Autowired
//     protected OnGoinFosoService onGoingFosoService;

//     @Autowired
//     protected CardService cardService;

//     @Autowired
//     protected GameService gameService;

//     @BeforeEach
//     void SetUp(){
//         Long gameId = 2L;
//         OnGoinFosoService.creatGame(gameId, gameService.findGame(gameId).get(), 
//             cardService.findAllCards());
//         game = onGoingFosoService.getGame(2L);
//     }

//     @Test
//     void testGetById(){
//         OnGoingFoso game2 = onGoingFosoService.getGame(2L);
//         assertEquals(game2.getPlayers(), game.getPlayers());
//     }

//     @Test
//     void testcreateGame(){
//         Long gameId = 1L;
//         OnGoinFosoService.creatGame(gameId, gameService.findGame(gameId).get(), 
//             cardService.findAllCards());
//         Integer i=0;
//         for(OnGoingFoso g: onGoingFosoService.getAll()){
//             i=i+1;
//         }
//         assertEquals(2, i);
//     }
//     @Test
//     void testgetPoints(){
//         Integer points = onGoingFosoService.getGame(2L).getPoints().get(2L);
//         assertEquals(points, onGoingFosoService.getPoints(2L, 2L));
//     }
//     @Test
//     void testgetPlayerCard(){
//         Card playerCard = onGoingFosoService.getGame(2L).getPlayerCard().get(2L);
//         assertEquals(playerCard, onGoingFosoService.getPlayerCard(2L, 2L));
//     }
//     @Test
//     void testgetCenterCard(){
//         Card centerCard = onGoingFosoService.getGame(2L).getCurrentCard();
//         assertEquals(centerCard, onGoingFosoService.getCenterCard(2L));
//     }
//     @Test
//     void testnewCenterCard(){
//         Card centerCard = onGoingFosoService.getGame(2L).getCurrentCard();
//         RequestNewCard request = new RequestNewCard();
//         request.setPlayerId(2L);
//         //onGoingFosoService.changeCards(centerCard, request);
//         assertNotEquals(centerCard, onGoingFosoService.getCenterCard(2L));
//     }
//     @Test
//     void testAddPoints(){
//         onGoingFosoService.addPoints(2L, 2L, 10);
//         Integer points = onGoingFosoService.getGame(2L).getPoints().get(2L);
//         assertEquals(10, points);
//     }
//     @Test
//     void testDeleteGame(){
//         Integer size = 0;
//         for(OnGoingFoso g: onGoingFosoService.getAll()){
//             size=size+1;
//         }
//         onGoingFosoService.deleteGame(2L);
//         Integer i=0;
//         for(OnGoingFoso g: onGoingFosoService.getAll()){
//             i=i+1;
//         }
//         assertEquals(size-1, i);
//     }
// }
