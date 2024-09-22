package org.example.sivillage.purchase.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.infrastructure.CartRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;
import org.example.sivillage.purchase.infrastructure.PurchaseProductRepository;
import org.example.sivillage.purchase.infrastructure.PurchaseRepository;
import org.example.sivillage.vendor.infrastructure.ProductByVendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
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
    public void addPurchase(String memberUuid, AddPurchaseRequestDto addPurchaseRequestDto) {
        Product product = productRepository.findByProductCode(addPurchaseRequestDto.getProductCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));

        int totalPriceBeforeDiscount = product.getPrice() * addPurchaseRequestDto.getAmount();
        int totalPriceAfterDiscount = calculateDiscountedPrice(totalPriceBeforeDiscount, product.getProductCode());

        purchaseRepository.save(addPurchaseRequestDto.toEntity(memberUuid, totalPriceBeforeDiscount, totalPriceAfterDiscount));
        purchaseProductRepository.save(addPurchaseRequestDto.toEntity());
    }

    private int calculateDiscountedPrice(int price, String productCode) {
        return productByVendorRepository.findDiscountRateByProductCode(productCode)
                .map(discountRate -> (int) (price * (1 - discountRate)))
                .orElse(price);
    }

    @Override
    public void addPurchaseFromCart(String memberUuid, AddPurchaseFromCartRequestDto addPurchaseFromCartRequestDto) {

        AtomicInteger totalPriceBeforeDiscount = new AtomicInteger();
        AtomicInteger totalPriceAfterDiscount = new AtomicInteger();

        log.debug("cartIdList: {}", addPurchaseFromCartRequestDto.getCartIdList());

        List<Cart> carts = cartRepository.findAllById(addPurchaseFromCartRequestDto.getCartIdList());
        carts.stream()
                .filter(Cart::isSelected)
                .forEach(cart -> saveEachPurchaseProduct(cart, totalPriceBeforeDiscount, totalPriceAfterDiscount));

        purchaseRepository.save(addPurchaseFromCartRequestDto.toEntity(memberUuid, totalPriceBeforeDiscount.get(),
                totalPriceAfterDiscount.get()));
    }

    private void saveEachPurchaseProduct(Cart cart, AtomicInteger totalPriceBeforeDiscount, AtomicInteger totalPriceAfterDiscount) {
        Product product = productRepository.findByProductCode(cart.getProductCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));

        int calculatedPrice = product.getPrice() * cart.getAmount();
        totalPriceBeforeDiscount.addAndGet(calculatedPrice);

        int discountedPrice = calculateDiscountedPrice(calculatedPrice, product.getProductCode());
        totalPriceAfterDiscount.addAndGet(discountedPrice);

        purchaseProductRepository.save(AddPurchaseFromCartRequestDto.toEntity(cart));
    }
}
