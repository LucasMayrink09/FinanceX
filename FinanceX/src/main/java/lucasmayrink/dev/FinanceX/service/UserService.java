package lucasmayrink.dev.FinanceX.service;

import lucasmayrink.dev.FinanceX.controller.dto.AccountResponseDto;
import lucasmayrink.dev.FinanceX.controller.dto.CreateAccountDto;
import lucasmayrink.dev.FinanceX.controller.dto.CreateUserDto;
import lucasmayrink.dev.FinanceX.controller.dto.UpdateUserDto;
import lucasmayrink.dev.FinanceX.entity.Account;
import lucasmayrink.dev.FinanceX.entity.BillingAddress;
import lucasmayrink.dev.FinanceX.entity.User;
import lucasmayrink.dev.FinanceX.repository.AccountRepository;
import lucasmayrink.dev.FinanceX.repository.BillingAddressRepository;
import lucasmayrink.dev.FinanceX.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class UserService {

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    private BillingAddressRepository billingAddressRepository;

    public UserService(AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        // DTO -> ENTITY
        var entity = new User(UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();

    }
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());

            }
            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }

    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(UUID.fromString(userId));

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY
        var account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDto.description(),
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var BillingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(BillingAddress);
    }

    public UserService() {
    }

    public List<AccountResponseDto> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
    }
}
