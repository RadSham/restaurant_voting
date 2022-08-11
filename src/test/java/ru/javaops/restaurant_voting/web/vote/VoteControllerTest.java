package ru.javaops.restaurant_voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.repository.VoteRepository;
import ru.javaops.restaurant_voting.to.UserTo;
import ru.javaops.restaurant_voting.to.VoteTo;
import ru.javaops.restaurant_voting.util.JsonUtil;
import ru.javaops.restaurant_voting.util.UserUtil;
import ru.javaops.restaurant_voting.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.restaurant_voting.util.VoteUtil.createNewVoteFromTo;
import static ru.javaops.restaurant_voting.web.user.UserTestData.USER_ID;
import static ru.javaops.restaurant_voting.web.user.UserTestData.USER_MAIL;
import static ru.javaops.restaurant_voting.web.user.UserTestData.user;
import static ru.javaops.restaurant_voting.web.vote.VoteTestData.*;

class VoteControllerTest extends AbstractControllerTest {

    public static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL+USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1User));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1User, vote2Admin, vote3User2, vote4User,
                        vote5Admin, vote6User2, vote7User, vote8Admin, vote9User2, vote10Admin));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "user?user=" + user))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1User, vote4User, vote7User));
    }


    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByUserAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "useranddate?date=" + vote1User.getDate()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1User));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        VoteTo updatedTo = new VoteTo(LocalDate.now(), ADMIN_ID, RESTAURANT_ID_3);
        perform(MockMvcRequestBuilders.put(VoteController.REST_URL + "?restaurantId=" +RESTAURANT_ID_2).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createNewVoteFromTo(updatedTo))))
                .andDo(print())
                .andExpect(status().isNoContent());
        VOTE_MATCHER.assertMatch(voteRepository.getById(10), createNewVoteFromTo(updatedTo));
    }
}