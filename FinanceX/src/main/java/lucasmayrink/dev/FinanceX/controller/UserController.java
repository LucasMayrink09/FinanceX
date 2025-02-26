package lucasmayrink.dev.FinanceX.controller;

import lucasmayrink.dev.FinanceX.controller.dto.AccountResponseDto;
import lucasmayrink.dev.FinanceX.controller.dto.CreateAccountDto;
import lucasmayrink.dev.FinanceX.controller.dto.CreateUserDto;
import lucasmayrink.dev.FinanceX.controller.dto.UpdateUserDto;
import lucasmayrink.dev.FinanceX.entity.User;
import lucasmayrink.dev.FinanceX.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();


    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId,
                                           @RequestBody CreateAccountDto createAccountDto) {


        userService.createAccount(userId, createAccountDto);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> listAccounts(@PathVariable("userId") String userId) {


        var accounts = userService.listAccounts(userId);

        return ResponseEntity.ok(accounts);

    }
}
