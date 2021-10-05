package concerttours.populators;

import concerttours.data.ConcertSummaryData;
import concerttours.data.TourData;
import concerttours.model.ConcertModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class TourConcertsPopulator implements Populator<ProductModel, TourData> {

    private Converter<ConcertModel, ConcertSummaryData> concertConverter;

    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        if (productModel.getVariants() != null) {
            List<ConcertModel> concertModels = productModel.getVariants()
                    .stream()
                    .map(variant -> (ConcertModel) variant)
                    .collect(Collectors.toList());

            List<ConcertSummaryData> concertSummaryDataList = Converters.convertAll(concertModels, concertConverter);
            tourData.setConcerts(concertSummaryDataList);
        }
    }

    public void setConcertConverter(Converter<ConcertModel, ConcertSummaryData> concertConverter) {
        this.concertConverter = concertConverter;
    }
}
