package org.example.sivillage.purchase.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.infrastructure.CartRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;
import org.example.sivillage.purchase.infrastructure.PurchaseProductRepository;
import org.example.sivillage.purchase.infrastructure.PurchaseRepository;
import org.example.sivillage.vendor.infrastructure.ProductByVendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final ProductByVendorRepository productByVendorRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final CartRepository cartRepository;

    /**
     * 주문할 당시의 할인가를 반영하기 위해 주문 생성시 가격을 계산하여 저장함.
     *
     */

    @Override
    public void addPurchase(AddPurchaseRequestDto addPurchaseRequestDto) {
        String productCode = addPurchaseRequestDto.getProductCode();
        Integer priceBeforeDiscount = productRepository.findPriceByProductCode(productCode);

        int totalPriceBeforeDiscount = priceBeforeDiscount * addPurchaseRequestDto.getAmount();
        int totalPriceAfterDiscount = totalPriceBeforeDiscount;

        Optional<Double> discountRate = productByVendorRepository.findDiscountRateByProductCode(productCode);

        if (discountRate.isPresent()) {
            totalPriceAfterDiscount = (int) (totalPriceBeforeDiscount * (1 - discountRate.get()));
        }

        purchaseRepository.save(addPurchaseRequestDto.toEntity(totalPriceBeforeDiscount, totalPriceAfterDiscount));
        purchaseProductRepository.save(addPurchaseRequestDto.toEntity());
    }

    @Override
    public void addPurchaseFromCart(AddPurchaseFromCartRequestDto addPurchaseFromCartRequestDto) {

        AtomicInteger totalPriceBeforeDiscount = new AtomicInteger();
        AtomicInteger totalPriceAfterDiscount = new AtomicInteger();

        addPurchaseFromCartRequestDto.getCartIdList().forEach(cartId -> {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));

            if (cart.isSelected()) {
                Integer price = productRepository.findByProductCode(cart.getProductCode())
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT))
                        .getPrice();

                int calculatedPrice = price * cart.getAmount();
                totalPriceBeforeDiscount.addAndGet(calculatedPrice);

                Optional<Double> discountRate = productByVendorRepository.findDiscountRateByProductCode(cart.getProductCode());

                if (discountRate.isPresent()) {
                    calculatedPrice = (int) (calculatedPrice * (1 - discountRate.get()));
                }

                totalPriceAfterDiscount.addAndGet(calculatedPrice);

                purchaseProductRepository.save(addPurchaseFromCartRequestDto.toEntity(cart));
            }
        });

        addPurchaseFromCartRequestDto.toEntity(totalPriceBeforeDiscount.get(), totalPriceAfterDiscount.get());

    }
}
