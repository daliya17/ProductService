package dev.daliya.productService.services;

import dev.daliya.productService.dtos.FakeStoreProductDto;
import dev.daliya.productService.dtos.GenericProductDto;
import dev.daliya.productService.exeptions.NotFoundException;
import dev.daliya.productService.thirdPartyClients.productService.fakeStore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.getProductById(id);
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }

        GenericProductDto product = convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
        return product;
    }

    private static GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        var response = fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : response) {
            GenericProductDto product = convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.deleteProductById(id);
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }


    @Override
    public GenericProductDto updateProductById(GenericProductDto product, Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.updateProductById(product, id);
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }
}
