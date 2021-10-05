package concerttours.facades.impl;

import concerttours.data.TourData;
import concerttours.facades.TourFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultTourFacade implements TourFacade {

    private ProductService productService;

    private Converter<ProductModel, TourData> tourConverter;

    @Override
    public TourData getTourDetails(final String tourId) {
        if (tourId == null) {
            throw new IllegalArgumentException("Tour id cannot be null");
        }
        final ProductModel product = productService.getProductForCode(tourId);
        if (product == null) {
            return null;
        }

        return tourConverter.convert(product);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setTourConverter(Converter<ProductModel, TourData> tourConverter) {
        this.tourConverter = tourConverter;
    }
}
