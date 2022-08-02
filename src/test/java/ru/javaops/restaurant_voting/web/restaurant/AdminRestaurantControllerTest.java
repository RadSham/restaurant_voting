package ru.javaops.restaurant_voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.restaurant_voting.web.restaurant.AdminTestData.*;
import static ru.javaops.restaurant_voting.web.restaurant.AdminTestData.ADMIN_MAIL;
import static ru.javaops.restaurant_voting.web.user.UserTestData.*;

class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';


    @Autowired
    private RestaurantRepository restaurantRepository;

    //TODO: make get()
    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + 1 /*RESTAURANT_ID_5*/))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurantTest1));
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void createWithLocation() {
    }

    @Test
    void update() {
    }
}