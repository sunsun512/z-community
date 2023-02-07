package zaritalk.zaritalkcommunity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zaritalk.zaritalkcommunity.service.LikeService;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    public void isAccountIdNull(String accountId){
        if(accountId == null){
            throw new IllegalStateException("회원이 아닙니다.");
        }
    }

    public String getAccountId(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @PostMapping("/post/{postId}/like")
    public ResponseEntity addLike(@PathVariable Long postId, Authentication authentication){
        String accountId = getAccountId(authentication);
        isAccountIdNull(accountId);
        boolean result = false;

        result = likeService.addLike(accountId, postId);

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("like - member "+ accountId + "likes " + "post no: " + postId);

        return result ?
                new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
