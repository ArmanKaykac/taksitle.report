package io.ngss.test.repository;

import io.ngss.taksitle.report.bank.database.entity.PreApplicationRequest;
import io.ngss.taksitle.report.bank.ononay.model.PreApplicationResponseCode;
import io.ngss.taksitle.report.bank.ononay.model.ProblemReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreApplicationRequestRepositoryNewDB extends JpaRepository<PreApplicationRequest, Long> {

    List<PreApplicationRequest> findAllByTransactionToken(Integer token);
    List<PreApplicationRequest> findAllByTransactionTokenAndPreApplicationResponseCode(Integer token, PreApplicationResponseCode preApplicationResponseCode);
    List<PreApplicationRequest> findAllByTransactionTokenAndPreApplicationResponseCodeAndBankIdAndProblemReason(Integer token, PreApplicationResponseCode preApplicationResponseCode, Long bankId, ProblemReason problemReason);
}
