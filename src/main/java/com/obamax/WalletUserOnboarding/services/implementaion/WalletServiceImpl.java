package com.obamax.WalletUserOnboarding.services.implementaion;

import com.obamax.WalletUserOnboarding.exceptions.BadRequestException;
import com.obamax.WalletUserOnboarding.exceptions.ResourceNotFoundException;
import com.obamax.WalletUserOnboarding.models.Transaction;
import com.obamax.WalletUserOnboarding.models.Wallet;
import com.obamax.WalletUserOnboarding.models.enums.TransactionMode;
import com.obamax.WalletUserOnboarding.models.enums.TransactionStatus;
import com.obamax.WalletUserOnboarding.models.enums.TransactionType;
import com.obamax.WalletUserOnboarding.payload.requests.WalletWithdrawalRequest;
import com.obamax.WalletUserOnboarding.payload.responses.WalletResponse;
import com.obamax.WalletUserOnboarding.payload.responses.WalletWithdrawalResponse;
import com.obamax.WalletUserOnboarding.repositories.TransactionRepository;
import com.obamax.WalletUserOnboarding.repositories.WalletRepository;
import com.obamax.WalletUserOnboarding.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private static final String NOT_APPLICABLE = "NOT_APPLICABLE";
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public WalletResponse fetchWalletBalance(Long id) {
        Wallet wallet = getWalletById(id);
        return getWalletResponse(wallet);
    }

    @Override
    public WalletResponse fundWalletByWalletId(Long id, Double amount) {
        Wallet wallet = getWalletById(id);

        if (amount <= 0) {
            saveTransactionDetails(amount, wallet, TransactionMode.CREDIT, TransactionStatus.FAILED,
                    TransactionType.FUND, NOT_APPLICABLE, NOT_APPLICABLE, NOT_APPLICABLE);
            throw new BadRequestException("Amount cannot be less than or equal to zero.", HttpStatus.BAD_REQUEST);
        }

        wallet.setBalance(wallet.getBalance() + amount);

        saveTransactionDetails(amount, wallet, TransactionMode.CREDIT, TransactionStatus.SUCCESSFUL,
                TransactionType.FUND, NOT_APPLICABLE, NOT_APPLICABLE, NOT_APPLICABLE);

        return getWalletResponse(walletRepository.save(wallet));
    }

    @Override
    public WalletWithdrawalResponse walletWithdrawal(WalletWithdrawalRequest walletWithdrawalRequest) {
        Wallet wallet = getWalletById(walletWithdrawalRequest.getWalletId());
        Double amount = walletWithdrawalRequest.getAmount();

        if (amount <= 0) {
            saveTransactionDetails(amount, wallet, TransactionMode.DEBIT, TransactionStatus.FAILED,
                    TransactionType.WITHDRAW, walletWithdrawalRequest.getBankName(),
                    walletWithdrawalRequest.getBeneficiaryAccountName(),
                    walletWithdrawalRequest.getBeneficiaryAccountNumber());
            throw new BadRequestException("Amount cannot be less than or equal to zero.", HttpStatus.BAD_REQUEST);
        }

        if (wallet.getBalance() < amount) {
            saveTransactionDetails(amount, wallet, TransactionMode.DEBIT, TransactionStatus.INSUFFICIENT_FUNDS,
                    TransactionType.WITHDRAW, walletWithdrawalRequest.getBankName(),
                    walletWithdrawalRequest.getBeneficiaryAccountName(),
                    walletWithdrawalRequest.getBeneficiaryAccountNumber());
            throw new BadRequestException("Insufficient funds.", HttpStatus.BAD_REQUEST);
        }

        wallet.setBalance(wallet.getBalance() - amount);

        Wallet savedWallet = walletRepository.save(wallet);

        saveTransactionDetails(amount, wallet, TransactionMode.DEBIT, TransactionStatus.SUCCESSFUL,
                TransactionType.WITHDRAW, walletWithdrawalRequest.getBankName(),
                walletWithdrawalRequest.getBeneficiaryAccountName(),
                walletWithdrawalRequest.getBeneficiaryAccountNumber());

        return WalletWithdrawalResponse.builder()
                .message("Withdrawal successful.")
                .walletId(savedWallet.getId())
                .bankName(walletWithdrawalRequest.getBankName())
                .beneficiaryAccountNumber(walletWithdrawalRequest.getBeneficiaryAccountNumber())
                .amount(amount)
                .build();
    }

    private Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("No such wallet.", HttpStatus.NOT_FOUND);
        });
    }

    private WalletResponse getWalletResponse(Wallet wallet) {
        return WalletResponse.builder()
                .walletId(wallet.getId())
                .accountId(wallet.getUser().getId())
                .accountName(wallet.getUser().getName())
                .balance(wallet.getBalance())
                .build();
    }

    private void saveTransactionDetails(Double amount, Wallet wallet, TransactionMode transactionMode,
                                        TransactionStatus transactionStatus, TransactionType transactionType,
                                        String bankName, String beneficiaryAccountName,
                                        String beneficiaryAccountNumber) {
        transactionRepository.save(Transaction.builder().wallet(wallet).transactionMode(transactionMode)
                .transactionStatus(transactionStatus).transactionType(transactionType).amount(amount)
                .bankName(bankName).beneficiaryAccountName(beneficiaryAccountName)
                .beneficiaryAccountNumber(beneficiaryAccountNumber)
                .build());
    }
}
