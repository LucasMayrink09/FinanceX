package lucasmayrink.dev.FinanceX.repository;

import lucasmayrink.dev.FinanceX.entity.Account;
import lucasmayrink.dev.FinanceX.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
