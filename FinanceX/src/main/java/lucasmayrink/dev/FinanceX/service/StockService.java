package lucasmayrink.dev.FinanceX.service;

import lucasmayrink.dev.FinanceX.controller.dto.CreateStockDto;
import lucasmayrink.dev.FinanceX.entity.Stock;
import lucasmayrink.dev.FinanceX.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public void createStock(CreateStockDto createStockDto) {

        // DTO -> ENTITY
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
