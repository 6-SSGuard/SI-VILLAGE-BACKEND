package org.example.sivillage.purchase.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.admin.infrastructure.ColorRepository;
import org.example.sivillage.admin.infrastructure.SizeRepository;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.infrastructure.CartRepository;
import org.example.sivillage.global.common.UuidGenerator;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;
import org.example.sivillage.purchase.dto.out.GetPurchaseCodeListResponseDto;
import org.example.sivillage.purchase.dto.out.GetPurchaseDetailResponseDto;
import org.example.sivillage.purchase.infrastructure.PurchaseProductRepository;
import org.example.sivillage.purchase.infrastructure.PurchaseRepository;
import org.example.sivillage.vendor.domain.ProductOption;
import org.example.sivillage.vendor.infrastructure.ProductByVendorRepository;
import org.example.sivillage.vendor.infrastructure.ProductOptionRepository;
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
    private final ProductOptionRepository productOptionRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

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

        String purchaseCode = UuidGenerator.generatePurchaseCode();

        purchaseRepository.save(addPurchaseRequestDto.toEntity(purchaseCode, memberUuid, totalPriceBeforeDiscount, totalPriceAfterDiscount));
        purchaseProductRepository.save(addPurchaseRequestDto.toEntity(purchaseCode, totalPriceAfterDiscount));
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

        String purchaseCode = UuidGenerator.generatePurchaseCode();

        List<Cart> carts = cartRepository.findAllById(addPurchaseFromCartRequestDto.getCartIdList());
        carts.stream()
                .filter(Cart::isSelected)
                .forEach(cart -> saveEachPurchaseProduct(purchaseCode, cart, totalPriceBeforeDiscount, totalPriceAfterDiscount));

        purchaseRepository.save(addPurchaseFromCartRequestDto.toEntity(
                purchaseCode, memberUuid, totalPriceBeforeDiscount.get(), totalPriceAfterDiscount.get()));
    }

    @Override
    public List<GetPurchaseCodeListResponseDto> getPurchaseCodeList(String memberUuid) {
        return purchaseRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(purchase -> GetPurchaseCodeListResponseDto.builder()
                        .purchaseCode(purchase.getPurchaseCode())
                        .build()
                )
                .toList();
    }

    @Override
    public List<GetPurchaseDetailResponseDto> getPurchaseDetail(String memberUuid, String purchaseCode) {

        return purchaseProductRepository.findAllByPurchaseCode(purchaseCode)
                .stream()
                .map(purchaseProduct -> {
                    int quantity = purchaseProduct.getAmount();
                    String productCode = purchaseProduct.getProductCode();
                    Long productOptionId = purchaseProduct.getProductOptionId();

                    Product product = productRepository.findByProductCode(productCode)
                            .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));

                    ProductOption productOption = productOptionRepository.findById(productOptionId)
                            .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT_OPTION));

                    String colorName = colorRepository.findById(product.getColorId())
                            .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_COLOR))
                            .getColorName();

                    String size = sizeRepository.findById(productOption.getSizeId())
                            .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SIZE))
                            .getSizeName();

                    return GetPurchaseDetailResponseDto.builder()
                                    .productName(product.getProductName())
                                    .quantity(quantity)
                                    .chargedPrice(purchaseProduct.getChargedPrice())
                                    .volume(productOption.getVolume())
                                    .color(colorName)
                                    .size(size)
                                    .build();
                        }
                )
                .toList();
    }

    private void saveEachPurchaseProduct(String purchaseCode, Cart cart, AtomicInteger totalPriceBeforeDiscount, AtomicInteger totalPriceAfterDiscount) {
        Product product = productRepository.findByProductCode(cart.getProductCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));

        int calculatedPrice = product.getPrice() * cart.getAmount();
        totalPriceBeforeDiscount.addAndGet(calculatedPrice);

        int discountedPrice = calculateDiscountedPrice(calculatedPrice, product.getProductCode());
        totalPriceAfterDiscount.addAndGet(discountedPrice);

        purchaseProductRepository.save(AddPurchaseFromCartRequestDto.toEntity(purchaseCode, cart, discountedPrice));
    }
}
