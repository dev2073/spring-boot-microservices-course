package com.keelient.catalog_service.services;

import com.keelient.catalog_service.domains.PagedResult;
import com.keelient.catalog_service.domains.Product;
import com.keelient.catalog_service.domains.ProductMapper;
import com.keelient.catalog_service.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Restituisce una lista di prodotti paginata.
     *
     * @param pageNo numero della pagina (0 = prima pagina)
     *               La pagina contiene sempre 10 prodotti.
     */
    public PagedResult<Product> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, sort);
        Page<Product> productsPage = productRepository.findAll(pageable).map(ProductMapper::toProduct);
        return new PagedResult<Product>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }
}
