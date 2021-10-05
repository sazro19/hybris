package concerttours.populators;

import concerttours.data.ConcertSummaryData;
import concerttours.enums.ConcertType;
import concerttours.model.ConcertModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ConcertSummaryPopulator implements Populator<ConcertModel, ConcertSummaryData> {

    @Override
    public void populate(ConcertModel concertModel, ConcertSummaryData concertSummaryData) throws ConversionException {
        concertSummaryData.setId(concertModel.getCode());
        concertSummaryData.setDate(concertModel.getDate());
        concertSummaryData.setVenue(concertModel.getVenue());
        concertSummaryData.setType(concertModel.getConcertType() == ConcertType.OPENAIR ? "Outdoors" : "Indoors");
        concertSummaryData.setCountDown(concertModel.getDaysUntil());
    }
}
