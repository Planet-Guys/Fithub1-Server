package fithub.app.service;

import fithub.app.domain.*;
import fithub.app.utils.OAuthResult;
import fithub.app.web.dto.requestDto.UserRequestDto;
import fithub.app.web.dto.responseDto.UserResponseDto;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    OAuthResult.OAuthResultDto kakaoOAuth(String socialId);

    OAuthResult.OAuthResultDto appleOAuth(String socialId);
    Optional<User> checkExistNickname(String nickname);

    List<ExerciseCategory> getExerciseList();

    User signUpPhoneNum(UserRequestDto.UserInfo request) throws IOException;

    User findByPhoneNum(String phoneNum);

    User updatePassword(String phoneNum,String password);

    public void findByPhoneNumJoin(String phoNum);

    public String login(User user, String password);

    User socialInfoComplete(UserRequestDto.UserOAuthInfo request, User user) throws IOException;

    Page<Article> getMyArticlesNoCategory(Long last, User user);
    Page<Article> getMyArticles(Long last, User user, Integer categoryId);

    Page<Record> getMyRecordsNoCategory(Long last, User user);
    Page<Record> getMyRecords(Long last, User user, Integer categoryId);

    List<UserExercise> getMyExercises(User user);

    UserExercise patchMainExercise(User user, Integer categoryId);
}
