package ru.javaops.restaurant_voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.restaurant_voting.web.restaurant.RestaurantTestData.*;
import static ru.javaops.restaurant_voting.web.restaurant.RestaurantTestData.ADMIN_MAIL;

@Slf4j
class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurantTest1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception{
            perform(MockMvcRequestBuilders.get(REST_URL))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(RESTAURANT_MATCHER.contentJson(restaurantTest1, restaurantTest2, restaurantTest3));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(restaurantRepository.findById(RESTAURANT_ID_1).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception{
        Restaurant newRest = getNewRestaurant();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRestaurant(newRest)))
                .andExpect(status().isCreated());

        Restaurant createdRest = RESTAURANT_MATCHER.readFromJson(action);
        int newId = createdRest.id();
        newRest.setId(newId);
        RESTAURANT_MATCHER.assertMatch(createdRest, newRest);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getById(newId), newRest);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception{
        Restaurant updated = getUpdatedRestaurant();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRestaurant(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getById(RESTAURANT_ID_1), getUpdatedRestaurant());
    }
}