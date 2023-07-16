package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.payments.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, String > {
    Payout getPayoutById(String  id);
}
