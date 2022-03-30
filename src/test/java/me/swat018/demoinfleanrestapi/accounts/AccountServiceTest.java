package me.swat018.demoinfleanrestapi.accounts;

import me.swat018.demoinfleanrestapi.common.BaseConrollerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class AccountServiceTest extends BaseConrollerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findByUsername() {
        // Given
        String password = "jinwoo";
        String username = "swat018@gmail.com";
        Account account = Account.builder()
                .email("swat018@gmail.com")
                .password("jinwoo")
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        this.accountRepository.save(account);

        // When
        UserDetailsService userDetailsService = accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Then
        assertThat(userDetails.getPassword()).isEqualTo(password);
    }

    @Test
    public void findByUsernameFail() {
//        String username = "random@email.com";

//        try{
//            accountService.loadUserByUsername(username);
//            fail("supposed to be failed");
//        } catch (UsernameNotFoundException e) {
//          assertThat(e.getMessage()).containsSequence(username);
//        }

        assertThrows(UsernameNotFoundException.class, () -> accountService.loadUserByUsername("random@email.com"));

    }
}