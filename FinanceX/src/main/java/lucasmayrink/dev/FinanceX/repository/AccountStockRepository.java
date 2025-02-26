package lucasmayrink.dev.FinanceX.repository;

import lucasmayrink.dev.FinanceX.entity.AccountStock;
import lucasmayrink.dev.FinanceX.entity.AccountStockId;
import lucasmayrink.dev.FinanceX.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
