package lucasmayrink.dev.FinanceX.service;

import lucasmayrink.dev.FinanceX.client.BrapiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lucasmayrink.dev.FinanceX.controller.dto.AccountStockDto;
import lucasmayrink.dev.FinanceX.controller.dto.AccountStockResponseDto;
import lucasmayrink.dev.FinanceX.entity.AccountStock;
import lucasmayrink.dev.FinanceX.entity.AccountStockId;
import lucasmayrink.dev.FinanceX.repository.AccountRepository;
import lucasmayrink.dev.FinanceX.repository.AccountStockRepository;
import lucasmayrink.dev.FinanceX.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{enviroment.TOKEN}")
    private String TOKEN;

    private StockRepository stockRepository;
    private AccountRepository accountRepository;
    private AccountStockRepository accountStockRepository;
    private BrapiClient brapiClient;

    public AccountService(StockRepository stockRepository,
                          AccountRepository accountRepository,
                          AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.stockRepository = stockRepository;
        this.accountRepository = accountRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }


    public void associateStock(String accountId, AccountStockDto accountStockDto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account nao existe"));

        var stock = stockRepository.findById(accountStockDto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock nao existe"));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());

        var accountStockEntity = new AccountStock(id, account, stock, accountStockDto.quantity());

        accountStockRepository.save(accountStockEntity);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account nao existe"));

        return account.getAccountStocks()
                .stream()
                .map(as -> new AccountStockResponseDto(as.getStock().getStockId(), as.getQuantity(), getTotal(as.getQuantity(), as.getStock().getStockId())))
                .toList();

    }

    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;

    }
}

