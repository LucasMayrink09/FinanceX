package lucasmayrink.dev.FinanceX.controller;

import lucasmayrink.dev.FinanceX.controller.dto.AccountStockDto;
import lucasmayrink.dev.FinanceX.controller.dto.AccountStockResponseDto;
import lucasmayrink.dev.FinanceX.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                               @RequestBody AccountStockDto dto) {


        accountService.associateStock(accountId, dto);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> associateStock(@PathVariable("accountId") String accountId) {


        var stocks = accountService.listStocks(accountId);
        return ResponseEntity.ok(stocks);

    }
}