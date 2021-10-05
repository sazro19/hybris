package concerttours.populators;

import concerttours.data.ProducerData;
import concerttours.data.TourData;
import concerttours.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class TourPopulator implements Populator<ProductModel, TourData> {

    private Converter<ProducerModel, ProducerData> producerConverter;

    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());
        tourData.setProducer(producerConverter.convert(productModel.getProducer()));
    }

    public void setProducerConverter(Converter<ProducerModel, ProducerData> producerConverter) {
        this.producerConverter = producerConverter;
    }
}
